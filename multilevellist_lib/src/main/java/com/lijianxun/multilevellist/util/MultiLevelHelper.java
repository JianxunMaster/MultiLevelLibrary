package com.lijianxun.multilevellist.util;

import com.lijianxun.multilevellist.model.MultiLevelModel;

import java.util.List;

/**
 * 多级列表工具类
 * Created by windows on 2017/12/28.
 */

public class MultiLevelHelper {

    /**
     * 递归算法，求所有子对象的总和
     *
     * @param list
     * @return
     */
    public static <T extends MultiLevelModel> int getCount(List<T> list) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            count++;
            if (t.isExpand()) {
                count += getCount(t.getChildren());
            }
        }
        return count;
    }


    /**
     * 递归算法，求第n个位置的对象
     *
     * @param list
     * @param curPosition 起始位置
     * @param tarPosition 所求位置
     * @param <T>
     * @return position相等时返回对象，否则返回数量
     */
    public static <T extends MultiLevelModel> Object getItem(List<T> list, int curPosition
            , int tarPosition) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            if (count + curPosition == tarPosition) {//位置相同，返回
                return t;
            }
            count++;
            if (t.isExpand()) {//可展开,执行下一个循环
                Object result = getItem(t.getChildren(), count + curPosition, tarPosition);//递归
                if (result instanceof Integer) {
                    count += (int) result;
                } else {
                    return result;
                }
            }
        }
        return count;//所有循环结束，如果还没有position相等，返回空给上一级。
    }

    /**
     * 递归算法，求第n个位置最外层的对象
     *
     * @param list
     * @param curPosition 起始位置
     * @param tarPositon  所求位置
     * @param <T>
     * @return
     */
    public static <T extends MultiLevelModel> Object getItemOutside(List<T> list, int curPosition
            , int tarPositon) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            if (count + curPosition == tarPositon) {//位置相同，返回
                return t;
            }
            count++;
            if (t.isExpand()) {//可展开,执行下一个循环
                Object result = getItem(t.getChildren(), count + curPosition, tarPositon);//递归
                if (result instanceof Integer) {
                    count += (int) result;
                } else {
                    return t;
                }
            }
        }
        return count;//所有循环结束，如果还没有position相等，返回空给上一级。
    }

    /**
     * 展开层级
     *
     * @param list
     * @param expandLevel
     * @param <T>
     */
    public static <T extends MultiLevelModel> void setExpandableByLevel(List<T> list, int curLevel
            , int expandLevel) {
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            t.setExpand(curLevel < expandLevel);
            setExpandableByLevel(t.getChildren(), curLevel + 1, expandLevel);
        }
    }

    /**
     * 展开所有层级
     *
     * @param list
     * @param <T>
     */
    public static <T extends MultiLevelModel> void setExpandAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            t.setExpand(true);
            setExpandAll(t.getChildren());
        }
    }

    /**
     * 设置层级
     *
     * @param list
     * @param curLevel
     * @param <T>
     */
    public static <T extends MultiLevelModel> void setLevel(List<T> list, int curLevel) {
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            t.setLevel(curLevel);
            setLevel(t.getChildren(), curLevel + 1);
        }
    }
}
