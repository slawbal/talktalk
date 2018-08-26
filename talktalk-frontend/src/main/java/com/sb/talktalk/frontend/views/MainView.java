package com.sb.talktalk.frontend.views;

import com.sb.talktalk.frontend.boundary.UserService;
import com.sb.talktalk.frontend.components.registration.RegistrationDialog;
import com.sb.talktalk.frontend.components.users.UsersGrid;
import com.sb.talktalk.frontend.domain.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Route("Main")
@Theme(Lumo.class)
@UIScope
@Push
public class MainView extends VerticalLayout {
    private final RegistrationDialog registrationDialog = new RegistrationDialog();
    private final UsersGrid grid = new UsersGrid();
    private Long lastUpdate = 0L;

    @Autowired
    UserService service;

    @PostConstruct
    public void init() {
        createComponents();
        setLayout();
        service.getUserLastUpdate().subscribe(e -> {
            Long update = e.getLastUpdate();
            if(update > this.lastUpdate){
                this.loadData();
                this.lastUpdate = update;
            }
        });

    }

    private void createComponents() {
        this.registrationDialog.addOnSaveAndCloseAction(() -> addEntryToGrid());
        final Button addUser = new Button("Add me",
                event -> this.registrationDialog.open());
        final Button deleteUser = new Button("Logout", event -> logoutUser());
        add(new HorizontalLayout(addUser,deleteUser), this.grid);
    }

    private void logoutUser() {
        String sessionID = VaadinSession.getCurrent().getSession().getId();
        this.service.deleteUserBySessionID(sessionID).subscribe(b -> loadData());
    }

    private void setLayout() {
        setSizeFull();
        setClassName("main-layout");
    }

    private void addEntryToGrid() {
        User user = this.registrationDialog.getData();
        String sessionID = VaadinSession.getCurrent().getSession().getId();
        user.setSessionID(sessionID);
        this.service.addUser(user).subscribe(u -> this.loadData());
    }

    private void loadData(){
        this.grid.cleanData();
        this.service.getAllUsers()
                .switchIfEmpty(u -> grid.cleanGrid())
                .subscribe(u -> grid.setData(Arrays.asList(u)));
    }

}
