package models.ontologyReading.jena.propertyFactories;


import models.ontologyModel.OntoProperty;
import models.ontologyReading.jena.OwlPropertyFactory;
import models.properties.StringProperty;

import com.hp.hpl.jena.ontology.OntProperty;

public class StringPropertyFactory extends OwlPropertyFactory {

	@Override
	public boolean canCreateProperty(OntProperty ontProperty) {
		if(!ontProperty.isDatatypeProperty())
			return false;
		if(ontProperty.getRange() == null)
			return false;
		if(ontProperty.getRange().getURI() == null)
			return false;
		
		String rangeUri = ontProperty.getRange().getURI();
		return rangeUri.equalsIgnoreCase("http://www.w3.org/2001/XMLSchema#string") || rangeUri.equalsIgnoreCase("http://www.w3.org/2001/XMLSchema#boolean") || rangeUri.equalsIgnoreCase("http://www.w3.org/2001/XMLSchema#anyURI") || rangeUri.equalsIgnoreCase("http://www.w3.org/2000/01/rdf-schema#Literal");
	}

	@Override
	public OntoProperty createProperty(OntProperty ontProperty) {
		return new StringProperty(ontProperty.getNameSpace(), ontProperty.getLocalName(), ontProperty.getRange().getURI());
	}

}
