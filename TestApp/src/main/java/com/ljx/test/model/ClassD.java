package com.ljx.test.model;

import com.lijianxun.multilevellist.model.MultiLevelModel;

/**
 * Created by windows on 2017/12/28.
 */

public class ClassD extends MultiLevelModel {
    private String name;
    private String label;

    public ClassD(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
