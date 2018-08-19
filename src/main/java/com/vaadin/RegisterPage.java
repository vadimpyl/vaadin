package com.vaadin;

import com.vaadin.helper.UserHelper;
import com.vaadin.model.User;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        DateField date = new DateField("Date of birth");
        date.setDateFormat("yyyy-MM-dd");
        form.addComponent(date);

        TextField postalCode = new TextField("Postal code");
        form.addComponent(postalCode);

        TextField city = new TextField("Birth City");
        form.addComponent(city);

        TextField country = new TextField("Birth Country");
        form.addComponent(country);

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
                UserHelper.addToList(new User(login.getValue(), firstName.getValue(), lastName.getValue(), parseDate(date.getValue().toString()),
                                     city.getValue(), country.getValue(), password.getValue()));
                VaadinSession.getCurrent().setAttribute("user", login.getValue());

                getUI().getNavigator().addView(WelcomePage.NAME, new WelcomePage());
                Page.getCurrent().setUriFragment("!"+WelcomePage.NAME);

            }
        });
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
