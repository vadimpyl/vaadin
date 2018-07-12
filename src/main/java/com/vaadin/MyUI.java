package com.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
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

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        new Navigator(this, this);
        getNavigator().addView(LoginPage.NAME, LoginPage.class);
        getNavigator().setErrorView(LoginPage.class);

        Page.getCurrent().addUriFragmentChangedListener(new Page.UriFragmentChangedListener() {

            @Override
            public void uriFragmentChanged(Page.UriFragmentChangedEvent event)
            {
                router(event.getUriFragment());
            }
        });


    }
    private void router(String route){
        Notification.show(route);

        if(route.equals("!WelcomePage"))
        {
            getNavigator().navigateTo(WelcomePage.NAME);
        }
        else if(route.equals("!RegisterPage"))
        {
            getNavigator().navigateTo(RegisterPage.NAME);
        }
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
