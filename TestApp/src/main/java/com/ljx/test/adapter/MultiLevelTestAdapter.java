package com.ljx.test.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lijianxun.multilevellist.adapter.MultiLevelAdapter;
import com.lijianxun.multilevellist.model.MultiLevelModel;
import com.ljx.test.R;
import com.ljx.test.model.ClassA;
import com.ljx.test.model.ClassB;
import com.ljx.test.model.ClassC;
import com.ljx.test.model.ClassD;

import java.util.List;

/**
 * Created by windows on 2017/12/29.
 */

public class MultiLevelTestAdapter extends MultiLevelAdapter {


    public MultiLevelTestAdapter(Context context, boolean isExpandable, boolean isExpandAll
            , int expandLevel) {
        super(context, isExpandable, isExpandAll, expandLevel);
    }

    @Override
    public View onCreateView(int position, View convertView, ViewGroup parent) {
        Holder v = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_multilevel1, null);
            v = new Holder(convertView);
            convertView.setTag(v);
        } else {
            v = (Holder) convertView.getTag();
        }
        MultiLevelModel model = (MultiLevelModel) getItem(position);
        switch (model.getLevel()) {
            case 0:
                ClassA a = (ClassA) model;
                v.tv.setText(position + a.getName());
                break;
            case 1:
                ClassB b = (ClassB) model;
                v.tv.setText(position + b.getLabel());
                break;
            case 2:
                ClassC c = (ClassC) model;
                v.tv.setText(position + c.getName());
                break;
            case 3:
                ClassD d = (ClassD) model;
                v.tv.setText(position + d.getName());
                break;
        }
        convertView.setPadding(10 + model.getLevel() * 50, 10, 10, 10);
        return convertView;
    }

    class Holder {
        TextView tv;

        public Holder(View view) {
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }
}
