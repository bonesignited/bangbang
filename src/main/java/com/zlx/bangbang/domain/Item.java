package com.zlx.bangbang.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Item {
    private String name;

    private String sex;

    public Item() {
    }

    public Item(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }
}
