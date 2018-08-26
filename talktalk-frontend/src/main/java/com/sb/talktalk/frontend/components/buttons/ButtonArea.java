package com.sb.talktalk.frontend.components.buttons;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ButtonArea extends HorizontalLayout {
    private final Button save = new Button("Save");
    private final Button cancel = new Button("Cancel");

    public ButtonArea() {
        add(this.save, this.cancel);
    }

    public void setSaveActionListener(final ComponentEventListener listener){
        this.save.addClickListener(listener);
    }

    public void setCancelActionListener(final ComponentEventListener listener){
        this.cancel.addClickListener(listener);
    }
}
