package de.digitalpenguin.web.vaadin.playground.base;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

import java.util.Collection;
import java.util.List;

public class DemoLayout extends MainLayout {

    @Override
    protected String headerTitleText() {
        return "Vaadin Components Demo";
    }

    @Override
    protected Collection<Component> createDrawerComponents() {
        RouterLink listLink = new RouterLink("My List", ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());
        return List.of(listLink);
    }
}