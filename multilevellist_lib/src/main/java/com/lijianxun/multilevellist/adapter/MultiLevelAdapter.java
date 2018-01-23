package com.lijianxun.multilevellist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

import com.lijianxun.multilevellist.model.MultiLevelModel;
import com.lijianxun.multilevellist.util.MultiLevelHelper;

import java.util.List;

/**
 * 安卓多级列表适配器
 * 1级列表、2级列表、3级列表、4级列表、5级列表、多级列表
 * 一级列表、二级列表、三级列表、四级列表、五级列表、多级列表
 * android multilevel adapter
 * 可伸缩可展开
 * Created by windows on 2017/12/28.
 */

public abstract class MultiLevelAdapter<T extends MultiLevelModel> extends BaseAdapter
        implements OnItemClickListener {
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mList;
    protected boolean mExpandable;//是否能伸展
    protected boolean mExpandAll;//是否全部展开
    protected int mExpandLevel;
    protected OnMultiLevelListener mListener;
/**
 *@param context 上下文
 *@param isExpandable 子列表能否伸展、伸缩
 *@param isExpandAll 是否展开所有
 *@param expandLevel 展开层级数
 */
    public MultiLevelAdapter(Context context, boolean isExpandable, boolean isExpandAll
            , int expandLevel) {
        mContext = context;
        mExpandable = isExpandable;
        mExpandAll = isExpandAll;
        mExpandLevel = expandLevel < 0 ? 0 : expandLevel;
        mInflater = LayoutInflater.from(context);
    }

    public void setList(List<T> list) {
        if (list == null) return;
        mList = list;
        MultiLevelHelper.setLevel(mList, 0);
        if (!mExpandable || mExpandAll) {//如果不能伸展，展开全部
            MultiLevelHelper.setExpandAll(mList);
        } else {
            MultiLevelHelper.setExpandableByLevel(mList, 0, mExpandLevel);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : MultiLevelHelper.getCount(mList);
    }


    @Override
    public Object getItem(int position) {
        return MultiLevelHelper.getItem(mList, 0, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return onCreateView(position, convertView, parent);
    }

    public abstract View onCreateView(int position, View convertView, ViewGroup parent);


    /**
     * ListView实现此监听才能伸展。eg:listView.setOnItemClickListener(adapter);
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        T item = (T) getItem(position);
        if (mListener != null) {
            T outside = (T) MultiLevelHelper.getItemOutside(mList, 0, position);
            mListener.onItemClick(parent, view, position, id, item, outside);
        }
        if (!mExpandable) {//如果不能伸展，不需要处理
            return;
        }
        item.setExpand(!item.isExpand());
        notifyDataSetChanged();
    }

    public void setOnMultiLevelListener(OnMultiLevelListener listener) {
        mListener = listener;
    }

    public interface OnMultiLevelListener {
        void onItemClick(AdapterView<?> parent, View view, int position, long id
                , MultiLevelModel current, MultiLevelModel outside);
    }
}
