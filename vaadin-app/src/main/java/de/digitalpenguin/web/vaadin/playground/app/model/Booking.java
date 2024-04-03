package de.digitalpenguin.web.vaadin.playground.app.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.Objects;

public class Booking {

    private long id;

    public Instant creationTime;

    private Instant recordTime;

    private int postingKeyId;

    private BigDecimal amount;

    private Currency currency;

    private int contractOwnerId;

    private String contractNo;

    private long contractPositionId;

    private String invoiceNo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public Instant getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Instant recordTime) {
        this.recordTime = recordTime;
    }

    public int getPostingKeyId() {
        return postingKeyId;
    }

    public void setPostingKeyId(int postingKeyId) {
        this.postingKeyId = postingKeyId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getContractOwnerId() {
        return contractOwnerId;
    }

    public void setContractOwnerId(int contractOwnerId) {
        this.contractOwnerId = contractOwnerId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public long getContractPositionId() {
        return contractPositionId;
    }

    public void setContractPositionId(long contractPositionId) {
        this.contractPositionId = contractPositionId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
