package de.digitalpenguin.web.vaadin.playground.base;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(value = "starter-theme", themeClass = Lumo.class)
public class AppConfig implements AppShellConfigurator {
}
