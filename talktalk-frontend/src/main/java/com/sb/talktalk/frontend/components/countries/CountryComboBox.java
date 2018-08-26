package com.sb.talktalk.frontend.components.countries;

import com.sb.talktalk.frontend.domain.Country;
import com.vaadin.flow.component.combobox.ComboBox;

public class CountryComboBox extends ComboBox<Country> {

    public CountryComboBox() {
        setItems(CountriesLoader.getCountries());
    }
}
