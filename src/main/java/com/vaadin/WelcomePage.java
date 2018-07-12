package com.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class WelcomePage extends VerticalLayout implements View
{
    public static final String NAME = "WelcomePage";

    public WelcomePage()
    {
        FormLayout form = new FormLayout();

        TextField titleField = new TextField("Welcome " + VaadinSession.getCurrent().getAttribute("user").toString());
        form.addComponent(titleField);

        addComponents(form);
    }


}
