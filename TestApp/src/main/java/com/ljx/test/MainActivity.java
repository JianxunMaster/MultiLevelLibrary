package com.ljx.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lijianxun.multilevellist.adapter.MultiLevelAdapter;
import com.lijianxun.multilevellist.model.MultiLevelModel;
import com.ljx.test.adapter.MultiLevelTestAdapter;
import com.ljx.test.model.ClassA;
import com.ljx.test.model.ClassB;
import com.ljx.test.model.ClassC;
import com.ljx.test.model.ClassD;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<ClassA> list = new ArrayList<>();
    MultiLevelTestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        int count = 0;
        for (int i = 0; i < 3; i++) {
            ClassA a = new ClassA(i, " A" + i);
            a.setChildren(new ArrayList());
            list.add(a);
            count += 1;
            Log.e("TAG 1", a.getName());
            for (int j = 0; j < 3; j++) {
                ClassB b = new ClassB(j, " A" + i + " B" + j);
                a.getChildren().add(b);
                count += 1;
                Log.e("TAG 2", b.getLabel());
                for (int k = 0; k < 3; k++) {
                    ClassC c = new ClassC(" A" + i + " B" + j + " C" + k);
                    b.getChildren().add(c);
                    count += 1;
                    Log.e("TAG 3", c.getName());
                    for (int l = 0; l < 3; l++) {
                        ClassD d = new ClassD(" A" + i + " B" + j + " C" + k + " D" + l
                                , "D");
                        c.getChildren().add(d);
                        count += 1;
                        Log.e("TAG 4", d.getName());
                    }
                }
            }
        }
        Log.e("count", "= " + count);
        adapter = new MultiLevelTestAdapter(this, true, false
                , 1);
        adapter.setOnMultiLevelListener(new MultiLevelAdapter.OnMultiLevelListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id
                    , MultiLevelModel current, MultiLevelModel max) {
                Toast.makeText(MainActivity.this, "position = " + position + "" +
                                " , current level = " + current.getLevel() + " , outside level = "
                                + max.getLevel()
                        , Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemClickListener(adapter);
        listView.setAdapter(adapter);
        adapter.setList(list);
    }
}
