package com.restaurant.domain;

import DDD.framework.SimpleValueObject;
import io.vavr.collection.List;

@DDD.VO
public class Capacity extends SimpleValueObject<Integer> {

    public static List<Integer> CAPACITY_LIST = List.of(2, 4, 6);

    public Capacity(int capacity) {
        super(requiredInList(capacity, CAPACITY_LIST));
    }

    private static Integer requiredInList(int capacity, List<Integer> capacityList) {
        if (!capacityList.contains(capacity)) throw new IllegalArgumentException("bad capacity");
        return capacity;
    }
}
