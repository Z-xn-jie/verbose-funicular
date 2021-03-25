package com.sprout.view;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.sprout.R;
import com.sprout.bean.TagBean;


public class TagsActivity extends AppCompatActivity {

    private ListView mListView;
    private String data[] = {"氛围感美女","2020回忆录","今天吃什么","学习挑战","今日份手机",
            "感受大自然","最美工作照","美颜大赏","我生活的城市","健身","穿搭","扫街随拍","大长腿","逃离城市计划","无滤镜风景","每日一笑"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);
        initView();
    }

    private void initView(){
        mListView = findViewById(R.id.recycleview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TagBean tagBean = new TagBean(1, data[position], "", -1, -1, -1);
                Intent intent = new Intent();
                intent.putExtra("tag_bean", tagBean);
                setResult(110, intent);
                finish();
            }
        });
    }

}
