package com.villaalegre.asada.Utilities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageItem {
    private int number;
    private boolean current;

    public PageItem(int number, boolean current) {
        this.number = number;
        this.current = current;
    }

}
