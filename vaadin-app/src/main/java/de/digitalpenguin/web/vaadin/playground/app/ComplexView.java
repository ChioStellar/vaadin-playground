package de.digitalpenguin.web.vaadin.playground.app;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import de.digitalpenguin.web.vaadin.playground.app.components.BookingRow;
import de.digitalpenguin.web.vaadin.playground.app.model.Booking;
import jakarta.inject.Inject;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Route(value="", layout = AppLayout.class)
@PageTitle("Complex List View | Vaadin CRM")
public class ComplexView extends VerticalLayout {

    private final BookingDAO dao;
    private AtomicInteger currentPageIdx = new AtomicInteger(0);
    private AtomicInteger selectedPageSize = new AtomicInteger(10);
    private AtomicInteger maxPageIndex = new AtomicInteger(0);
    private final List<Component> rows = new LinkedList<>();

    private final Span pageStatusLabel;

    @Inject
    public ComplexView(BookingDAO dao) {
        this.dao = dao;

        maxPageIndex.set((int) Math.ceil((double) dao.allBookings().size() / (double) selectedPageSize.get()));

        //PagingDataProvider<Booking> dataProvider = new PagingDataProvider<>(dao.allBookings(), initPageSize);
//        DataProvider<Booking, Void> dataProvider =  DataProvider.fromCallbacks(
//                query -> dao.streamBookings(new BookingDAO.FilterParams(query.getOffset(), query.getPageSize())),
//                query -> dao.allBookings().size());
//        Grid<Booking> grid = new Grid<>(dataProvider);
//        grid.setPageSize(initPageSize);
//
//        grid.addColumn(Booking::getId).setHeader("LFDNR").setSortable(true);
//        grid.addColumn(b -> DateTimeFormatter.ISO_INSTANT.format(b.getCreationTime())).setHeader("Erstellungszeit").setSortable(true);
//        grid.addColumn(b -> DecimalFormat.getCurrencyInstance(Locale.GERMANY).format(b.getAmount())).setHeader("Betrag").setSortable(true);
//        grid.addColumn(Booking::getContractNo).setHeader("Auftrags-Nr.").setSortable(true);

        //Pagination<Booking> pagination = new Pagination<>(dataProvider, grid);

//        add(grid);

        VerticalLayout rowHolder = new VerticalLayout();
        rowHolder.addClassNames("no-padding");

        updateRows(rowHolder, currentPageIdx.get(), selectedPageSize.get());

        HorizontalLayout pagination = new HorizontalLayout();
        pagination.addClassNames("pagination");

        pageStatusLabel = new Span("Page 0000 / 0000");
        pageStatusLabel.addClassNames("self-start");
        pagination.add(pageStatusLabel);

        HorizontalLayout numericPageButtons = new HorizontalLayout();
        numericPageButtons.addClassNames(LumoUtility.Width.FULL);
        pagination.add(numericPageButtons);

        Button buttonPrevious = new Button();
        buttonPrevious.setText("Previous %d Rows".formatted(selectedPageSize.get()));
        pagination.add(buttonPrevious);

        Button buttonNext = new Button();
        buttonNext.setText("Next %d Rows".formatted(selectedPageSize.get()));
        pagination.add(buttonNext);

        add(rowHolder, pagination);

        buttonPrevious.addClickListener(e -> {
            updateRows(rowHolder, currentPageIdx.decrementAndGet(), selectedPageSize.get());
            updatePaginationButtons(buttonPrevious, buttonNext);
        });
        buttonNext.addClickListener(e -> {
            updateRows(rowHolder, currentPageIdx.incrementAndGet(), selectedPageSize.get());
            updatePaginationButtons(buttonPrevious, buttonNext);
        });

        updatePaginationButtons(buttonPrevious, buttonNext);
    }

    private void updatePaginationButtons(Button buttonPrevious, Button buttonNext) {
        buttonPrevious.setEnabled(currentPageIdx.get() > 0);
        buttonNext.setEnabled(currentPageIdx.get() < maxPageIndex.get());

        pageStatusLabel.setText("Page %04d / %04d".formatted(currentPageIdx.get() + 1, maxPageIndex.get()));
    }

    private void updateRows(HasComponents componentHolder, int fromPage, int initPageSize) {
        componentHolder.removeAll();
        rows.clear();

        for (Booking b : dao.listBookings(new BookingDAO.FilterParams(fromPage, initPageSize))) {
            BookingRow row = new BookingRow(b);
            componentHolder.add(row);
            rows.add(row);
        }
    }
}