package com.bwie.android.zhangyaozhongview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchView extends LinearLayout implements View.OnClickListener {
    private int searchColor;
    private int searchSize;
    private ImageView iv_back;
    private EditText et_search;
    private TextView tv_search;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initAttrs(context, attrs);
        initVeiw();
    }

    /**
     * 初始化组合控件的子控件
     */
    private void initVeiw() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.search_layout, this, true);
        iv_back = view.findViewById(R.id.iv_back);
        et_search = view.findViewById(R.id.et_search);
        tv_search = view.findViewById(R.id.tv_search);
//        设置字体样式
        tv_search.setTextSize(searchSize);
        tv_search.setTextColor(searchColor);//动态设置颜色
        iv_back.setOnClickListener(this);
        tv_search.setOnClickListener(this);
    }

    /**
     * 初始化自定义属性;
     *
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchView);
        searchSize = typedArray.getDimensionPixelSize(R.styleable.SearchView_searchSize, 20);
        searchColor = typedArray.getColor(R.styleable.SearchView_searchColor, Color.GREEN);
        if (typedArray != null) {
//            回收资源
            typedArray.recycle();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                if (searchCallback != null) {
                    searchCallback.backOnClick();
                }
                break;
            case R.id.tv_search:
                String content = et_search.getText().toString().trim();
//                判断搜索内容是否为空
                if (TextUtils.isEmpty(content)) {
                    if (searchCallback != null) {
                        searchCallback.judgeSearchContent();
                    }
                    return;
                } else {
                    if (searchCallback != null) {
//                        不为空 执行搜索的方法
                        searchCallback.search(content);
                    }
                }
                break;
        }
    }

    //    定义接口属性
    private SearchCallback searchCallback;
//    定义set方法供外部调用   调用者调用

    public void setSearchCallback(SearchCallback searchCallback) {
        this.searchCallback = searchCallback;
    }

    public interface SearchCallback {
        //判断输入框是否为空
        void judgeSearchContent();

        //返回按钮回调方法
        void backOnClick();

        //搜索回调方法
        void search(String keywords);
    }
}
