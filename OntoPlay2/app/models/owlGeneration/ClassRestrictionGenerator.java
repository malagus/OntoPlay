package models.owlGeneration;

import java.util.HashSet;
import java.util.Set;

import models.ClassCondition;
import models.ConfigurationException;
import models.PropertyValueCondition;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;

public class ClassRestrictionGenerator{
	
	private final OWLDataFactory factory;

	public ClassRestrictionGenerator(OWLDataFactory factory){
		this.factory = factory;
	}
	
	public OWLClassExpression convertToOntClass(String classUri, ClassCondition condition) throws ConfigurationException {
		OWLClass conditionClass = factory.getOWLClass(IRI.create(condition.getClassUri()));
		
		Set<OWLClassExpression> intersectionElements = new HashSet<OWLClassExpression>();
		intersectionElements.add(conditionClass);
		
		for (PropertyValueCondition cond : condition.getPropertyConditions()) {
			RestrictionFactory restrictionFactory = RestrictionFactory.getRestrictionFactory(cond);
			OWLClassExpression restriction = restrictionFactory.createRestriction(cond);
			intersectionElements.add(restriction);
		}
		
		OWLClassExpression intersection = factory.getOWLObjectIntersectionOf(intersectionElements);

		return intersection;
	}
}