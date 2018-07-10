package com.vaadin;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LoginPage extends VerticalLayout implements View
{
    private static final long serialVersionUID = 1L;
    public static final String NAME = "";

    public LoginPage(){
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

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.addComponents(loginButton, registerButton);
        buttonsLayout.setSpacing(true);

        Button send = new Button("Enter");
        send.addClickListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
           /*     if(VaadinloginUI.AUTH.authenticate(username.getValue(), password.getValue())){
                    VaadinSession.getCurrent().setAttribute("user", username.getValue());
                    getUI().getNavigator().addView(SecurePage.NAME, SecurePage.class);
                    getUI().getNavigator().addView(OtherSecurePage.NAME, OtherSecurePage.class);
                    Page.getCurrent().setUriFragment("!"+SecurePage.NAME);
                }else{
                    Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
                }*/
            }

        });
        content.addComponent(buttonsLayout);
        content.setSizeFull();

        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        content.setMargin(true);
        panel.setContent(content);
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }
}
