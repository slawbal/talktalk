package com.sb.talktalk.frontend.components.validators;

import com.sb.talktalk.frontend.domain.Country;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.validator.AbstractValidator;

public class CountryFieldValidator extends AbstractValidator<Country> {

    public CountryFieldValidator() {
        super("Please select your country");
    }

    @Override
    public ValidationResult apply(final Country value, final ValueContext context) {
        return toResult(value, value != null);
    }
}
