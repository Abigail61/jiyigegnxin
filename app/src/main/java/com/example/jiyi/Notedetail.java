package com.example.jiyi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity ;
import androidx.fragment.app.Fragment;

import com.example.jiyi.homefrag.HomeFragment;


public class Notedetail extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notedetails);
    }

    public void onClick(View view) {
        Intent intent1 = new Intent(this, Login.class);
        startActivity(intent1);
        finish();

    }
}