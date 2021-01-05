package com.example.jiyi.messagefrag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.example.jiyi.R;
import com.example.jiyi.adapter.LikeFragPaperAdapter;
import com.example.jiyi.messagefrag.FavorFragment;
import com.example.jiyi.messagefrag.LikeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Like extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);

        tabLayout = findViewById(R.id.like_tabs);
        viewPager = findViewById(R.id.like_vp);
        view = findViewById(R.id.like_v);
        initPaper();
    }

    private void initPaper() {
        List<Fragment>fragmentList = new ArrayList<>();

        LikeFragment likeFrag = new LikeFragment();
        FavorFragment favorFrag = new FavorFragment();
        fragmentList.add(likeFrag);
        fragmentList.add(favorFrag);

        LikeFragPaperAdapter paperAdapter = new LikeFragPaperAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(paperAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.like_iv_back:
                finish();
                break;
        }
    }
}
