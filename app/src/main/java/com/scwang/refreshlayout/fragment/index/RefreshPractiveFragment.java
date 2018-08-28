package com.scwang.refreshlayout.fragment.index;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.activity.FragmentActivity;
import com.scwang.refreshlayout.activity.practice.BannerPracticeActivity;
import com.scwang.refreshlayout.activity.practice.FeedlistPracticeActivity;
import com.scwang.refreshlayout.activity.practice.ProfilePracticeActivity;
import com.scwang.refreshlayout.activity.practice.QQBrowserPracticeActivity;
import com.scwang.refreshlayout.activity.practice.RepastPracticeActivity;
import com.scwang.refreshlayout.activity.practice.WebviewPracticeActivity;
import com.scwang.refreshlayout.activity.practice.WeiboPracticeActivity;
import com.scwang.refreshlayout.adapter.BaseRecyclerAdapter;
import com.scwang.refreshlayout.adapter.SmartViewHolder;
import com.scwang.refreshlayout.fragment.practice.SecondFloorPracticeFragment;
import com.scwang.refreshlayout.util.StatusBarUtil;

import java.util.Arrays;

import static android.R.layout.simple_list_item_2;
import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

/**
 * 实战演示
 * A simple {@link Fragment} subclass.
 */
public class RefreshPractiveFragment extends Fragment implements AdapterView.OnItemClickListener {

    private enum Item {
        Repast("餐饮美食-简单自定义Header-外边距magin", RepastPracticeActivity.class),
        Profile("个人中心-PureScrollMode-纯滚动模式", ProfilePracticeActivity.class),
        Webview("网页引用-WebView", WebviewPracticeActivity.class),
        FeedList("微博列表-智能识别", FeedlistPracticeActivity.class),
        Weibo("微博主页-MultiPurposeListener", WeiboPracticeActivity.class),
        Banner("滚动广告-Banner", BannerPracticeActivity.class),
        QQBrowser("QQ浏览器-模拟QQ浏览器内核提示", QQBrowserPracticeActivity.class),
        TwoLevel("二级刷新", SecondFloorPracticeFragment.class),
        SecondFloor("二级刷新-仿淘宝二楼", SecondFloorPracticeFragment.class),
        ;
        public String name;
        public Class<?> clazz;
        Item(String name, Class<?> clazz) {
            this.name = name;
            this.clazz = clazz;
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_refresh_practive, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        StatusBarUtil.setPaddingSmart(getContext(), root.findViewById(R.id.toolbar));

        View view = root.findViewById(R.id.recyclerView);
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
            recyclerView.setAdapter(new BaseRecyclerAdapter<Item>(Arrays.asList(Item.values()), simple_list_item_2,this) {
                @Override
                protected void onBindViewHolder(SmartViewHolder holder, Item model, int position) {
                    holder.text(android.R.id.text1, model.name());
                    holder.text(android.R.id.text2, model.name);
                    holder.textColorId(android.R.id.text2, R.color.colorTextAssistant);
                }
            });
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Item item = Item.values()[position];
        if (Activity.class.isAssignableFrom(item.clazz)) {
            startActivity(new Intent(getContext(), item.clazz));
        } else if (Fragment.class.isAssignableFrom(item.clazz)) {
            FragmentActivity.start(this, item.clazz);
        }
    }
}
