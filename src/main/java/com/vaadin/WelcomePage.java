package com.vaadin;

import com.vaadin.helper.UserHelper;
import com.vaadin.model.User;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

import java.util.List;

public class WelcomePage extends VerticalLayout implements View
{
    public static final String NAME = "WelcomePage";
    private Button logout;

    public WelcomePage()
    {
        logout = new Button("Logout");
        logout.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;
            @Override
            public void buttonClick(Button.ClickEvent event) {
                //getUI().getNavigator().removeView(SecurePage.NAME);
                //getUI().getNavigator().removeView(OtherSecurePage.NAME);
                VaadinSession.getCurrent().setAttribute("user", null);
                Page.getCurrent().setUriFragment("");
            }
        });
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Label titleField = new Label("Welcome, " + VaadinSession.getCurrent().getAttribute("user").toString() + "!");
        horizontalLayout.addComponents(titleField, logout);



        List<User> userList = UserHelper.listOfUsers();
        Grid<User> grid = new Grid<>();
        grid.getEditor().setEnabled(true);

        TextField tf = new TextField();
        TextField tf1 = new TextField();

        grid.setItems(userList);
        grid.addColumn(User::getFirstName).setEditorComponent(tf, User::setFirstName).setCaption("First name").setExpandRatio(2);
        grid.addColumn(User::getLastName).setEditorComponent(tf1, User::setLastName).setCaption("Last name").setExpandRatio(2);

        grid.addColumn(deleteEvent -> "DELETE",
                new ButtonRenderer<>(click -> {
                    UserHelper.listOfUsers().remove(click.getItem());
                    grid.setItems(UserHelper.listOfUsers());
                })
        );

        //multiselect
        addComponents(horizontalLayout, grid);
    }
}
