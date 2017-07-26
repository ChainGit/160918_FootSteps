package com.tgweb.struts2.day14;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class IDCardValidator extends FieldValidatorSupport {

	@Override
	public void validate(Object object) throws ValidationException {
		String fieldName = this.getFieldName();
		Object fieldValue = this.getFieldValue(fieldName, object);
		if (fieldValue == null) {
			addFieldError(fieldName, object);
			return;
		}

		IDCard card = new IDCard();
		boolean result = card.verify((String) fieldValue);
		if (!result) {
			addFieldError(fieldName, object);
		}
	}

}
