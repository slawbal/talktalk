package com.sb.talktalk.frontend.components.registration;

import com.sb.talktalk.frontend.components.DataAccess;
import com.sb.talktalk.frontend.components.listeners.RegistrationFormEventListener;
import com.sb.talktalk.frontend.domain.User;
import com.vaadin.flow.component.dialog.Dialog;


public class RegistrationDialog extends Dialog implements DataAccess<User> {
	private final RegistrationForm form = new RegistrationForm();

	public RegistrationDialog() {
		add(this.form);
		this.form.addCloseAction(e -> close());
	}

	public void addOnSaveAndCloseAction(final RegistrationFormEventListener listener){
		this.form.addSaveAction(() -> {
			listener.onComponentEvent();
			close();
		});
	}
	@Override
	public void open(){
		this.form.clean();
		super.open();
	}

	@Override
	public User getData() {
		return this.form.getData();
	}

	@Override
	public void setData(final User data) {
		this.form.setData(data);
	}
}
