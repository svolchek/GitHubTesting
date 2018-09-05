package com.epam.tat.task5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class YoWhatsUp {
    String name;
    int age;
    YoWhatsUp(String name, int age){
        this.name=name;
        this.age=age;
        Predicate.isEqual(this);

    }
}
