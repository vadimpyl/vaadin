package com.vaadin;

import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.helper.UserHelper;
import com.vaadin.model.User;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.ButtonRenderer;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WelcomePage extends VerticalLayout implements View
{
    public static final String NAME = "WelcomePage";
    private Button logout;

    public WelcomePage()
    {
        TextField tf = new TextField();
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        TextField tf3 = new TextField();


        logout = new Button("Logout");
        logout.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;
            @Override
            public void buttonClick(Button.ClickEvent event) {
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
        ListDataProvider<User> dataProvider = new ListDataProvider<>(
                userList);
        grid.setDataProvider(dataProvider);
        List<ValueProvider<User, String>> valueProviders = new ArrayList<>();
        valueProviders.add(User::getLogin);
        valueProviders.add(User::getFirstName);
        valueProviders.add(User::getLastName);
        valueProviders.add(User::getBirthDate);
        valueProviders.add(User::getBirthCity);
        valueProviders.add(User::getBirthCountry);
        Iterator<ValueProvider<User, String>> iterator = valueProviders.iterator();
        grid.addColumn(iterator.next()).setCaption("Login");
        grid.addColumn(iterator.next()).setEditorComponent(tf, User::setFirstName).setCaption("First Name");
        grid.addColumn(iterator.next()).setEditorComponent(tf1, User::setLastName).setCaption("Last Name");
        grid.addColumn(iterator.next()).setCaption("Birth Date");
        grid.addColumn(iterator.next()).setEditorComponent(tf2, User::setBirthCity).setCaption("Birth City");
        grid.addColumn(iterator.next()).setEditorComponent(tf3, User::setBirthCountry).setCaption("Birth Country");
        HeaderRow filterRow = grid.appendHeaderRow();
        Iterator<ValueProvider<User, String>> iterator2 = valueProviders.iterator();
        grid.getColumns().forEach(column -> {
            TextField field = new TextField();
            ValueProvider<User, String> valueProvider = iterator2.next();

            field.addValueChangeListener(event -> dataProvider.addFilter(person -> StringUtils.containsIgnoreCase(
                            valueProvider.apply(person), field.getValue())));

            field.setValueChangeMode(ValueChangeMode.EAGER);

            filterRow.getCell(column).setComponent(field);
            field.setWidth("100px");
            field.setHeight("30px");
            field.setPlaceholder("Filter");
        });
        grid.addColumn(deleteEvent -> "DELETE",
                new ButtonRenderer<>(click -> {
                    UserHelper.listOfUsers().remove(click.getItem());
                    grid.setItems(UserHelper.listOfUsers());
                })
        );

        grid.setWidth("1300px");
        addComponents(horizontalLayout, grid);
    }
}
