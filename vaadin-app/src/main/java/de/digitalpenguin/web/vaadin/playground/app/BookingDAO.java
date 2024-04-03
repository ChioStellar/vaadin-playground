package de.digitalpenguin.web.vaadin.playground.app;

import de.digitalpenguin.web.vaadin.playground.app.model.Booking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;

@ApplicationScoped
public class BookingDAO {

    private static final List<Booking> basis = new ArrayList<>(10000);

    static {
        for (int i = 0; i < 10000; i++) {
            basis.add(generateRandomBooking());
        }
        basis.sort(Comparator.comparing(Booking::getId));
    }

    public List<Booking> allBookings() {
        return Collections.unmodifiableList(basis);
    }

    public static record FilterParams (int pageIndex, int pageSize) {}

    public List<Booking> listBookings(FilterParams params) {
        int fromIdx = params.pageIndex * params.pageSize;
        int untiIdx = fromIdx + params.pageSize;
        return basis.subList(fromIdx, untiIdx);
    }

    public Stream<Booking> streamBookings(FilterParams filterParams) {
        try {
            Thread.sleep(RandomUtils.nextLong(1000L, 3000L));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return listBookings(filterParams).stream();
    }

    public static Booking generateRandomBooking() {
        Booking booking = new Booking();
        booking.setId(Math.abs(RandomUtils.nextLong(10000, 99999))); // Random long using RandomUtils
        booking.setCreationTime(Instant.now().minusMillis(RandomUtils.nextLong(1, 10000))); // Past creation time
        booking.setRecordTime(Instant.now()); // Current record time
        booking.setPostingKeyId(RandomUtils.nextInt()); // Random int using RandomUtils
        booking.setAmount(BigDecimal.valueOf(RandomUtils.nextDouble(0, 100))); // Random amount up to 10000
        booking.setCurrency(Currency.getInstance(Locale.GERMANY)); // Random currency based on country
        booking.setContractOwnerId(RandomUtils.nextInt()); // Random int using RandomUtils
        booking.setContractNo("2" + RandomStringUtils.randomNumeric(11)); // Random 10 character string
        booking.setContractPositionId(Math.abs(RandomUtils.nextLong())); // Random long using RandomUtils
        booking.setInvoiceNo("08" + RandomStringUtils.randomNumeric(8)); // Random 15 character string
        return booking;
    }
}
