package de.digitalpenguin.web.vaadin.playground.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

import java.util.Collection;
import java.util.Collections;

public abstract class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1(headerTitleText());
        logo.addClassNames("text-l", "m-m");

        HorizontalLayout header = new HorizontalLayout(
                new DrawerToggle(),
                logo
        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }

    protected abstract String headerTitleText();

    protected void createDrawer() {
        Collection<Component> drawerComponents = createDrawerComponents();
        if (!drawerComponents.isEmpty()) {
            addToDrawer(new VerticalLayout(drawerComponents.toArray(Component[]::new)));
        }
    }

    protected Collection<Component> createDrawerComponents() {
        return Collections.emptyList();
    }
}