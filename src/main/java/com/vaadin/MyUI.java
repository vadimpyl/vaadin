package com.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    //private TextField txtUsername = new TextField("Username");

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        new Navigator(this, this);
        getNavigator().addView(LoginPage.NAME, LoginPage.class);
        getNavigator().setErrorView(LoginPage.class);

       /* final VerticalLayout layout = new VerticalLayout();

        Panel panel = new Panel("Login");
        panel.setSizeUndefined();
        setContent(panel);

        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");
        layout.addComponents(username, password);


        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.addComponents(loginButton, registerButton);
        buttonsLayout.setSpacing(true);

        layout.addComponent(buttonsLayout);
        //setContent(layout);
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        panel.setContent(layout);*/
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
