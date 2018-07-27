package com.vaadin;

import com.vaadin.helper.UserHelper;
import com.vaadin.model.User;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class RegisterPage extends VerticalLayout implements View
{
    private static final long serialVersionUID = 1L;
    public static final String NAME = "RegisterPage";


    public RegisterPage()
    {
        FormLayout form = new FormLayout();

        Label titleField = new Label("Registration Form");
        form.addComponent(titleField);

        TextField login = new TextField("Login");
        login.setRequiredIndicatorVisible(true);
        form.addComponent(login);

        TextField firstName = new TextField("First Name");
        firstName.setRequiredIndicatorVisible(true);
        form.addComponent(firstName);

        TextField lastName = new TextField("Last Name");
        form.addComponent(lastName);

        TextField streetAddress = new TextField("Street address");
        form.addComponent(streetAddress);

        TextField postalCode = new TextField("Postal code");
        form.addComponent(postalCode);

        TextField city = new TextField("City");
        form.addComponent(city);

        PasswordField password = new PasswordField("Password");
        PasswordField confirmPassword = new PasswordField("Confirm Password");
        form.addComponents(password, confirmPassword);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button registerButton = new Button("Register");
        horizontalLayout.addComponent(registerButton);

        addComponents(form, horizontalLayout);

        registerButton.addClickListener(new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent)
            {
                UserHelper.addToList(new User(login.getValue(), firstName.getValue(), lastName.getValue(), streetAddress.getValue(),
                        password.getValue()));
                VaadinSession.getCurrent().setAttribute("user", login.getValue());

                getUI().getNavigator().addView(WelcomePage.NAME, new WelcomePage());
                Page.getCurrent().setUriFragment("!"+WelcomePage.NAME);

            }
        });
    }



    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
