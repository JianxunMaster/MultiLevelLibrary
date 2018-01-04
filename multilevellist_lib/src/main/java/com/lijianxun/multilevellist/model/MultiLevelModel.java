package com.lijianxun.multilevellist.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 多级列表模型
 * Created by windows on 2017/12/28.
 */

public class MultiLevelModel<T extends MultiLevelModel> {
    private boolean isExpand;//是否展开
    private List<T> children;//子类
    private int level;//层级
private String label;
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public List<T> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

}
