package com.sb.talktalk.frontend.components.users;

import java.util.*;

import com.sb.talktalk.frontend.components.DataAccess;
import com.sb.talktalk.frontend.domain.User;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.VaadinSession;

public class UsersGrid extends HorizontalLayout implements DataAccess<Collection<User>> {
    private final Grid<User> grid = new Grid<>();
    private final Set<User> users = new HashSet<>();

    public UsersGrid() {
        init();
    }

    private void init() {
        setLayout();
        setHeaders();

        this.grid.setItems(this.users);
        add(this.grid);
    }

    private void setLayout() {
        this.grid.setSizeFull();
        setSizeFull();
    }

    private void setHeaders() {
        this.grid.addColumn(User::getName).setHeader("Name");
        this.grid.addColumn(User::getAge).setHeader("Age");
        this.grid.addColumn(User::getLogin).setHeader("Skype");
        this.grid.addColumn(User::getCountry).setHeader("Country");
    }

    @Override
    public Collection<User> getData() {
        return this.users;
    }

    @Override
    public void setData(final Collection<User> data) {
        this.users.addAll(data);
        pushToGUI();

    }

    private void pushToGUI() {
        getUI().get().access(()->{
            this.grid.getDataProvider().refreshAll();
        });
    }

    public void cleanData(){
        this.users.clear();
    }

    public void cleanGrid(){
        this.cleanData();
        this.pushToGUI();
    }

}
