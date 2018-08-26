package com.sb.talktalk.frontend.components.registration;

import com.sb.talktalk.frontend.components.countries.CountryComboBox;
import com.sb.talktalk.frontend.components.DataAccess;
import com.sb.talktalk.frontend.components.buttons.ButtonArea;
import com.sb.talktalk.frontend.components.listeners.RegistrationFormEventListener;
import com.sb.talktalk.frontend.components.validators.CountryFieldValidator;
import com.sb.talktalk.frontend.domain.User;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.validator.StringLengthValidator;

public class RegistrationForm extends VerticalLayout implements DataAccess<User> {
    private final FormLayout form = new FormLayout();

    private final TextField name = new TextField();
    private final TextField age = new TextField();
    private final TextField login = new TextField();
    private final CountryComboBox nationality = new CountryComboBox();
    private final ButtonArea buttons = new ButtonArea();
    private final Label infoLabel = new Label();

    private final Binder<User> binder = new Binder<>();
    private User user = new User();

    public RegistrationForm() {
        super();
        init();
        bindData();
    }

    private void init() {
        this.form.addFormItem(this.name, "Name");
        this.form.addFormItem(this.age, "Age");
        this.form.addFormItem(this.login, "Skype login");
        this.form.addFormItem(this.nationality, "Country");

        this.form.setResponsiveSteps(
                new ResponsiveStep("0em", 1),
                new ResponsiveStep("60em", 2),
                new ResponsiveStep("60em", 3));
        add(this.form, this.infoLabel, this.buttons);
    }

    private void bindData() {
        this.binder.forField(this.name).withValidator(new StringLengthValidator(
                "Please add the first name", 1, null)).bind(User::getName, User::setName);
        this.binder.forField(this.age)
                .withConverter(new StringToIntegerConverter("This is not integer value"))
                .bind(User::getAge, User::setAge);
        this.binder.forField(this.login).withValidator(new StringLengthValidator(
                "Please add the skype login", 1, null)).bind(User::getLogin, User::setLogin);
        this.binder.forField(this.nationality).withValidator(new CountryFieldValidator()).bind(User::getCountry, User::setCountry);
        this.binder.setBean(this.user);
    }

    public void addSaveAction(final RegistrationFormEventListener listener) {
        this.buttons.setSaveActionListener(e -> save(listener));
    }

    private void save(final RegistrationFormEventListener listener) {
        final BinderValidationStatus<User> validate = this.binder.validate();
        if (validate.isOk()) {
            listener.onComponentEvent();
            clean();
        } else {
            prompt();
        }
    }

    public void addCloseAction(final ComponentEventListener listener) {
        this.buttons.setCancelActionListener(listener);
    }

    private void prompt() {
        this.infoLabel.setText("Correct all errors before save");
    }

    @Override
    public User getData() {
        return this.user;
    }

    @Override
    public void setData(final User data) {
        this.binder.setBean(data);
    }

    public void clean() {
        this.user = new User();
        this.binder.setBean(this.user);
    }
}
