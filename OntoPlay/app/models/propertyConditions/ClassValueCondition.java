package models.propertyConditions;

import models.ClassCondition;
import models.PropertyValueCondition;

public class ClassValueCondition extends PropertyValueCondition {
	private ClassCondition classConstraintValue;

	public ClassValueCondition(){
		
	}
	
	public ClassValueCondition(String propertyUri, ClassCondition classConstraintValue) {
		super(propertyUri);
		this.classConstraintValue = classConstraintValue;
	}

	public void setClassConstraintValue(ClassCondition classConstraintValue) {
		this.classConstraintValue = classConstraintValue;
	}

	public ClassCondition getClassConstraintValue() {
		return classConstraintValue;
	}
}
