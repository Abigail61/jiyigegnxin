package com.example.jiyi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.jiyi.homefrag.HomeFragment;
import com.example.jiyi.messagefrag.Comment;
import com.example.jiyi.messagefrag.Follwer;
import com.example.jiyi.messagefrag.Like;
import com.example.jiyi.messagefrag.MessageFragment;
import com.example.jiyi.myfrag.MyFragment;
import com.example.jiyi.trailfrag.TrailFragment;

public class Navigation extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    RadioGroup mainRg;
    Fragment homeFrag,msgFrag,trailFrag,myFrag;
    private FragmentManager manager;
    private String trueusername="chushi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Bundle bundle = getIntent().getExtras();
        trueusername = bundle.getString("trueusername");

        mainRg = findViewById(R.id.main_rg);
        mainRg.setOnCheckedChangeListener(this);

        homeFrag = new HomeFragment();
        msgFrag = new MessageFragment();
        trailFrag = new TrailFragment();
        myFrag = new MyFragment(trueusername);

        addFragmentPage();
    }

    private void addFragmentPage() {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction= manager.beginTransaction();
        transaction.add(R.id.main_layout_center,homeFrag);
        transaction.add(R.id.main_layout_center,msgFrag);
        transaction.add(R.id.main_layout_center,trailFrag);
        transaction.add(R.id.main_layout_center,myFrag);

        transaction.hide(msgFrag);
        transaction.hide(trailFrag);
        transaction.hide(myFrag);

        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction= manager.beginTransaction();
        switch (checkedId){
            case R.id.main_rb1:
                transaction.hide(msgFrag);
                transaction.hide(trailFrag);
                transaction.hide(myFrag);
                transaction.show(homeFrag);
                break;
            case R.id.main_rb2:
                transaction.hide(homeFrag);
                transaction.hide(trailFrag);
                transaction.hide(myFrag);
                transaction.show(msgFrag);
                break;
            case R.id.main_rb3:
                transaction.hide(homeFrag);
                transaction.hide(msgFrag);
                transaction.hide(myFrag);
                transaction.show(trailFrag);
                break;
            case R.id.main_rb4:
                transaction.hide(homeFrag);
                transaction.hide(trailFrag);
                transaction.hide(msgFrag);
                transaction.show(myFrag);
                break;
        }
        transaction.commit();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.msg_mid_iv_like:
                Intent it1 = new Intent(this, Like.class);
                startActivity(it1);
                break;
            case R.id.msg_mid_iv_fans:
                Intent it2 = new Intent(this, Follwer.class);
                startActivity(it2);
                break;
            case R.id.msg_mid_iv_comment:
                Intent it3 = new Intent(this, Comment.class);
                startActivity(it3);
                break;
            case R.id.main_btn_more:
                Intent itadd = new Intent(this, NewNote.class);
                itadd.putExtra("trueusername",trueusername);
                startActivity(itadd);
                break;

        }
    }
    public void onClick1(View view){
        Intent detail = new Intent(this,Notedetail.class);
        startActivity(detail);
    }

}
