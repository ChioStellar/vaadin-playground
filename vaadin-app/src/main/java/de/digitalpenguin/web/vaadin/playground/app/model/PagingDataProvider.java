package de.digitalpenguin.web.vaadin.playground.app.model;

import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;

import java.util.List;
import java.util.stream.Stream;

public class PagingDataProvider<T> extends AbstractBackEndDataProvider<T, Void> {

    private final List<T> data;
    private int offset = 0;

    private int pageSize;

    public PagingDataProvider(List<T> data, int initialPageSize) {
        this.data = data;
        this.pageSize = initialPageSize;
    }

    @Override
    public boolean isInMemory() {
        return true;
    }

    @Override
    public int size(Query<T, Void> query) {
        return data.size();
    }

    @Override
    protected Stream<T> fetchFromBackEnd(Query<T, Void> query) {
//        try {
//            Thread.sleep(RandomUtils.nextLong(1000L, 3000L));
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        int qLimit = query.getLimit();
        int qPageSize = query.getPageSize();
        int qPageOffset = query.getOffset();
        System.out.printf("Limit: %d, Size: %d, OffseT: %d%n", qLimit, qPageSize, qPageOffset);

        return data.subList(qPageOffset * qPageSize, qPageOffset * qPageSize + pageSize).stream();
    }

    @Override
    protected int sizeInBackEnd(Query<T, Void> query) {
        return getCount();
    }

    public int getOffset() {
        return offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCount() {
        return data.size();
    }

    public void setOffset(int nextOffset) {
        this.offset = nextOffset;
    }

    public void setPageSize(int value) {
        this.pageSize = value;
    }
}
