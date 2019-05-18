package com.vaadin;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinSession;
import com.vaadin.service.LoginService;
import com.vaadin.service.LoginServiceImpl;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginPage extends VerticalLayout implements View
{
    private static final long serialVersionUID = 1L;
    public static final String NAME = "";
    public LoginPage()
    {
        LoginService loginService = new LoginServiceImpl();

        Panel panel = new Panel("Login");
        panel.setSizeUndefined();
        addComponent(panel);
        setHeight("100%");
        FormLayout content = new FormLayout();
        TextField username = new TextField("Username");
        content.addComponent(username);
        PasswordField password = new PasswordField("Password");
        content.addComponent(password);
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        loginButton.addClickListener(new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent)
            {
                if(loginService.login(username.getValue(), password.getValue()) != null)
                {
                    VaadinSession.getCurrent().setAttribute("user", username.getValue());
                    getUI().getNavigator().addView(WelcomePage.NAME, new WelcomePage());
                    Page.getCurrent().setUriFragment("!"+WelcomePage.NAME);
                }
                else
                {
                    username.setComponentError( new UserError("Wrong UserName"));
                    password.setComponentError( new UserError("Wrong Password"));
                }
            }
        });
        registerButton.addClickListener(new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent)
            {
                getUI().getNavigator().addView(RegisterPage.NAME, new RegisterPage());
                getUI().getNavigator().setErrorView(RegisterPage.class);
                Page.getCurrent().setUriFragment("!"+RegisterPage.NAME);
            }
        });

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.addComponents(loginButton, registerButton);
        buttonsLayout.setSpacing(true);
        content.addComponent(buttonsLayout);
        content.setSizeUndefined();
        content.setMargin(true);
        panel.setContent(content);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }
}
