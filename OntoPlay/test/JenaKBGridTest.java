import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jobs.JenaOwlReaderConfiguration;
import jobs.PropertyTypeConfiguration;
import jobs.PropertyTypeConfiguration;
import junit.framework.Assert;

import models.ClassCondition;
import models.InvalidConfigurationException;
import models.PropertyValueCondition;
import models.ontologyModel.OntoClass;
import models.ontologyModel.OntoProperty;
import models.ontologyModel.OwlElement;
import models.ontologyModel.OwlIndividual;
import models.ontologyReading.OntologyReader;
import models.ontologyReading.jena.JenaOwlReader;
import models.ontologyReading.jena.JenaOwlReaderConfig;
import models.properties.OwlObjectProperty;
import models.propertyConditions.ClassValueCondition;
import models.propertyConditions.DatatypePropertyCondition;
import models.propertyConditions.IndividualValueCondition;

import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class JenaKBGridTest {
	private OntologyReader kb;

	@BeforeClass
	public static void classSetup(){
		XMLUnit.setControlParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		XMLUnit.setTestParser("org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		XMLUnit.setSAXParserFactory("org.apache.xerces.jaxp.SAXParserFactoryImpl");
		//XMLUnit.setTransformerFactory("org.apache.xalan.processor.TransformerFactoryImpl");
		XMLUnit.setIgnoreWhitespace(true);
	}
	
	@Before
	public void setup() {
		try {
			new JenaOwlReaderConfiguration().doJob();
			new PropertyTypeConfiguration().doJob();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kb = JenaOwlReader.getGlobalInstance();
	}

	@Test
	public void getClass_ReturnsClassFromImportedOntology() {
		OntoClass wnClass = kb.getOwlClass("http://www.owl-ontologies.com/unnamed.owl#WorkerNode");
		assertThat(wnClass).isNotNull();
		Assert.assertEquals("WorkerNode", wnClass.getLocalName());
		Assert.assertEquals("http://www.owl-ontologies.com/unnamed.owl#", wnClass.getNamespace());
	}

	@Test
	public void getClass_ReturnsClassWithPropertiesFromImportedOntology() throws Exception {
		OntoClass memoryClass = kb.getOwlClass("http://gridagents.sourceforge.net/AiGGridOntology#PhysicalMemory");

		assertThat(memoryClass).isNotNull();
		assertThat(selectLocalNames(memoryClass.getProperties())).containsOnly(
				"hasName", "hasID", "belongToVO", "hasTotalSize", "hasAvailableSize");
	}
	
	@Test
	public void forComputingElement_getClass_ReturnsClassWithStorageSpaceProperty() throws Exception {
		OntoClass memoryClass = kb.getOwlClass("http://www.owl-ontologies.com/unnamed.owl#ComputingElement");

		assertThat(selectLocalNames(memoryClass.getProperties())).contains(
				"hasStorageSpace");
	}
	
	@Test
	public void forInstalledSoftware_getIndividualsInRange_ReturnsCorrectResults() throws Exception {
		OntoClass owlClass = new OntoClass("http://www.owl-ontologies.com/unnamed.owl#", "WorkerNode", new ArrayList<OntoProperty>());
		OwlObjectProperty property = new OwlObjectProperty("http://gridagents.sourceforge.net/AiGGridOntology#", "hasInstalledSoftware"); 

		assertThat(selectLocalNames(kb.getIndividualsInRange(owlClass, property))).containsExactly("vista_sp2", "debian_5.0", "Scientific_Linux_303");
	}
	
		
	@Test
	public void forHasNameProperty_getProperty_works(){
		OntoProperty prop = kb.getProperty("http://www.owl-ontologies.com/unnamed.owl#hasName");
		assertThat(prop).isNotNull();
	}

	private List<String> selectLocalNames(List<? extends OwlElement> properties) {
		List<String> names = new ArrayList<String>(properties.size());

		for (OwlElement prop : properties) {
			names.add(prop.getLocalName());
		}

		return names;
	}	

	private OwlIndividual getByUri(String uri, List<OwlIndividual> individuals) {
		for (OwlIndividual i : individuals) {
			if(i.getUri().equals(uri)){
				return i;
			}
		}
		return null;
	}
	
	private DatatypePropertyCondition createEqualToDatatypeCondition(String propertyUri, String propertyValue) {
		return new DatatypePropertyCondition(propertyUri, "equalTo", propertyValue);
	}	
}
