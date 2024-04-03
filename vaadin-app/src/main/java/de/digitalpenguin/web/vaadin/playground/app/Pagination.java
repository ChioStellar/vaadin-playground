package de.digitalpenguin.web.vaadin.playground.app;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import de.digitalpenguin.web.vaadin.playground.app.model.PagingDataProvider;

import java.util.Arrays;

public class Pagination<T> extends HorizontalLayout {

    public Pagination(PagingDataProvider<T> dataProvider, Grid<T> grid) {
        Button previousButton = new Button("Previous");
        previousButton.addClickListener(click -> {
            if (dataProvider.getOffset() > 0) {
                dataProvider.setOffset(dataProvider.getOffset() - dataProvider.getPageSize());
                grid.setDataProvider(dataProvider);
                previousButton.setEnabled(dataProvider.getOffset() > 0);
            }
        });

        Button nextButton = new Button("Next");
        nextButton.addClickListener(click -> {
            int total = dataProvider.getCount();
            int nextOffset = dataProvider.getOffset() + dataProvider.getPageSize();
            if (nextOffset < total) {
                dataProvider.setOffset(nextOffset);
                grid.setDataProvider(dataProvider);
                nextButton.setEnabled((nextOffset + dataProvider.getPageSize()) < total);
            }
        });

        ComboBox<Integer> pageSizeDropdown = new ComboBox<>("Page Size");
        pageSizeDropdown.setItems(Arrays.asList(10, 25, 50));
        pageSizeDropdown.setValue(dataProvider.getPageSize());
        pageSizeDropdown.addValueChangeListener(event -> {
            dataProvider.setPageSize(event.getValue());
            grid.setDataProvider(dataProvider);
        });

        add(previousButton, nextButton, pageSizeDropdown);
    }
}
