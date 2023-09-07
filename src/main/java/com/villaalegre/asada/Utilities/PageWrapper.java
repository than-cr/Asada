package com.villaalegre.asada.Utilities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PageWrapper<T> {
    public static final int MAX_PAGE_ITEM_DISPLAY = 5;
    private final Page<T> page;
    private final List<PageItem> items;
    private final int currentNumber;
    private final String url;

    public PageWrapper(Page<T> page, String url) {
        this.page = page;
        this.url = url;
        items = new ArrayList<PageItem>();

        currentNumber = page.getNumber() + 1;

        int start = 1;
        int size = 1;

        if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY) {
            size = page.getTotalPages();
        } else {
            if (currentNumber <= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY/2) {
                size = MAX_PAGE_ITEM_DISPLAY;
            } else if (currentNumber >= page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY/2) {
                start = page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY + 1;
                size = MAX_PAGE_ITEM_DISPLAY;
            } else {
                start = currentNumber - MAX_PAGE_ITEM_DISPLAY/2;
                size = MAX_PAGE_ITEM_DISPLAY;
            }
        }

        for (int index = 0; size > index; ++index) {
            items.add(new PageItem(start + index, (start + index) == currentNumber));
        }
    }

    public List<T> getContent() {
        return page.getContent();
    }

    public int getSize() {
        return page.getSize();
    }

    public int getTotalPages() {
        return page.getTotalPages();
    }

    public boolean isFirstPage() {
        return page.isFirst();
    }

    public boolean isLastPage() {
        return page.isLast();
    }

    public boolean hasPrevious() {
        return page.hasPrevious();
    }

    public boolean hasNext() {
        return page.hasNext();
    }

    public int getPreviousPage() {
        // CurrentNumber is one based
        return currentNumber - 2;
    }
}
