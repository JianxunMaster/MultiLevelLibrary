package com.ljx.test.model;

import com.lijianxun.multilevellist.model.MultiLevelModel;

/**
 * Created by windows on 2017/12/28.
 */

public class ClassC extends MultiLevelModel<ClassD> {
    private String name;

    public ClassC(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
