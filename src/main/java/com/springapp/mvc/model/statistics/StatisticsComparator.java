package com.springapp.mvc.model.statistics;

import java.util.Comparator;

/**
 * Created by eirikskogland on 28.12.14.
 */
public class StatisticsComparator implements Comparator<Statistics> {
    @Override
    public int compare(Statistics o1, Statistics o2) {
        return -o1.getRating() + o2.getRating();
    }
}
