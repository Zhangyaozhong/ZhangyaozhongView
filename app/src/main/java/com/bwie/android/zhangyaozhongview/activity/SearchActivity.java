package com.bwie.android.zhangyaozhongview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bwie.android.zhangyaozhongview.R;
import com.bwie.android.zhangyaozhongview.SearchView;

public class SearchActivity extends AppCompatActivity implements SearchView.SearchCallback {
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.searchView);
        searchView.setSearchCallback(this);
    }

    @Override
    public void judgeSearchContent() {
        Toast.makeText(this, "关键词不能为空", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void backOnClick() {
        this.finish();
    }

    @Override
    public void search(String keywords) {
        Toast.makeText(this, keywords, Toast.LENGTH_SHORT).show();
    }
}
