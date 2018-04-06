package com.ljx.test.model;

import com.lijianxun.multilevellist.model.MultiLevelModel;

/**
 * Created by windows on 2017/12/28.
 */

public class ClassA extends MultiLevelModel<ClassB> {
    private int id;
    private String name;

    public ClassA(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
