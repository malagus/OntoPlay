package models.propertyConditions;

import java.math.BigDecimal;

import models.PropertyValueCondition;
import models.properties.OwlDatatypeProperty;

public class DatatypePropertyCondition extends PropertyValueCondition<OwlDatatypeProperty>{
	private String datatypeValue;
	private String operator;
	
	public DatatypePropertyCondition(){
		
	}

	public DatatypePropertyCondition(String propertyUri, String operator, String datatypeValue) {
		super(propertyUri);
		this.operator = operator;
		this.datatypeValue = datatypeValue;
	}

	public void setDatatypeValue(String datatypeValue) {
		this.datatypeValue = datatypeValue;
	}

	public String getDatatypeValue() {
		return datatypeValue;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public String getOperator() {
		return operator;
	} 
	
	@Override
	public void setPropertyUri(String propertyUri) {
		super.setPropertyUri(propertyUri);
	}

	@Override
	public String getPropertyUri() {
		return super.getPropertyUri();
	}
}
