package models.ontologyModel;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;

import com.hp.hpl.jena.ontology.OntClass;

public class OntoClass implements OwlElement {

	private String localName;
	private String namespace;
	private OntoClass superClass;
	private List<OntoProperty> properties;
	private String label;

	public OntoClass(String namespace, String name, List<OntoProperty> properties) {
		this.namespace = namespace;
		this.localName = name;
		this.properties = properties;
	}

	public OntoClass(OntClass ontClass) {
		this(ontClass.getNameSpace(), ontClass.getLocalName(), new ArrayList<OntoProperty>());
		this.label = ontClass.getLabel(null);
		if(label != null && label.isEmpty())
			label = null;
	}
	
	public OntoClass(OntClass ontClass, OntClass ontSuperClass) {
		this(ontClass.getNameSpace(), ontClass.getLocalName(), new ArrayList<OntoProperty>());
		this.superClass = new OntoClass(ontSuperClass);
	}

	public OntoClass(IRI iri, List<OntoProperty> properties) {
		this(iri.getScheme(), iri.getFragment(), properties);
	}

	public String getLocalName() {
		return localName;
	}

	public String getNamespace() {
		return namespace;
	}

	public List<OntoProperty> getProperties() {
		return properties;
	}

	public OntoProperty findProperty(String propertyLocalName) {
		for (OntoProperty prop : getProperties()) {
			if (prop.getLocalName().equals(propertyLocalName)) {
				return prop;
			}
		}
		return null;
	}

	public String getUri() {
		return String.format("%s%s", namespace, localName);
	}
	
	public String getDisplayName() {
		String localDisplayName = label;
		if(localDisplayName == null)
			localDisplayName = localName;
		if(superClass != null)
			return String.format("%s\\%s", superClass.getDisplayName(), localDisplayName);
		else 			
			return localDisplayName; 
	}

	public OntoClass getSuperClass() {
		return superClass;
	}

	public String getLabel() {
		return label;
	}

}
