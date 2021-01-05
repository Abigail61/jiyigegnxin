package com.example.jiyi.messagefrag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.jiyi.R;

public class Comment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.comm_iv_back:
                finish();
                break;
        }
    }
}
