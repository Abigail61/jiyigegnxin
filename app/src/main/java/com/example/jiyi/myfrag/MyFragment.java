package com.example.jiyi.myfrag;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.jiyi.MyFavorFragment;
import com.example.jiyi.MyLikeFragment;
import com.example.jiyi.MyNoteFragment;
import com.example.jiyi.R;
import com.example.jiyi.adapter.LikeFragPaperAdapter;
import com.example.jiyi.adapter.MyFragPaperAdapter;
import com.example.jiyi.messagefrag.FavorFragment;
import com.example.jiyi.messagefrag.LikeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {
    TextView username;
    TabLayout tabLayout;
    ViewPager viewPager;
    View view;
    String trueusername;

    public MyFragment(String trueusername) {
        // Required empty public constructor
        this.trueusername=trueusername;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_my, container, false);
        username = v.findViewById(R.id.mine_header_tv_username);
        username.setText(trueusername);
        tabLayout = v.findViewById(R.id.my_tabs);
        viewPager = v.findViewById(R.id.my_vp);
        view = v.findViewById(R.id.my_v);//a line
        initPaper();

        return v;
    }

    private void initPaper() {
        List<Fragment> fragmentList = new ArrayList<>();

        MyNoteFragment mynoteFrag = new MyNoteFragment(trueusername);
        MyFavorFragment myfavorFrag = new MyFavorFragment();
        MyLikeFragment mylikeFrag = new MyLikeFragment();
        fragmentList.add(mynoteFrag);
        fragmentList.add(myfavorFrag);
        fragmentList.add(mylikeFrag);

        MyFragPaperAdapter paperAdapter = new MyFragPaperAdapter(getFragmentManager(), fragmentList);
        viewPager.setAdapter(paperAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
