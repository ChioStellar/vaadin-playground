package de.digitalpenguin.web.vaadin.playground.app.components;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import de.digitalpenguin.web.vaadin.playground.app.model.Booking;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class BookingRow extends HorizontalLayout {

    public BookingRow(Booking b) {
        addClassNames("booking-row");

        Anchor linkId = new Anchor("#", Long.toString(b.getId()));

        Span date = new Span(DateTimeFormatter.ISO_INSTANT.format(b.getCreationTime()));
        Span amount = new Span(NumberFormat.getCurrencyInstance(Locale.GERMANY).format(b.getAmount()));
        Span contractNo = new Span(b.getContractNo());

        add(
                linkId,
                date,
                contractNo,
                amount
        );
    }
}
