package models.ontologyReading.owlApi.propertyFactories;



import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLProperty;

import models.ontologyModel.OntoProperty;
import models.properties.IntegerProperty;
import models.properties.StringProperty;

public class IntegerPropertyFactory extends SimpleDatatypePropertyFactory {
	public IntegerPropertyFactory(){
		super("http://www.w3.org/2001/XMLSchema#integer", "http://www.w3.org/2001/XMLSchema#int");
	}

	@Override
	public OntoProperty createProperty(OWLOntology onto, OWLProperty property) {
		return new IntegerProperty(property.getIRI().getStart(), property.getIRI().getFragment());
	}
}
