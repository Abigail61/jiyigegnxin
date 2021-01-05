package com.example.jiyi;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseTag extends AppCompatActivity {
    private ImageView back;
    private EditText shuruTag;
    private ImageButton Tagok;
    private String tag;
    private FlowLayout alltags;
    private TextView exist1,exist2,exist3,exist4,exist5,exist6,exist7,exist8,exist9,exist10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosetag);

        back = (ImageView)findViewById(R.id.backtoNewNote);
        shuruTag= (EditText)findViewById(R.id.shuruTag);
        Tagok= (ImageButton)findViewById(R.id.Tagok);
        alltags = (FlowLayout) findViewById(R.id.alltags);//流式布局的标签池
        exist1 = (TextView) findViewById(R.id.exist1);
        exist2 = (TextView) findViewById(R.id.exist2);
        exist3 = (TextView) findViewById(R.id.exist3);
        exist4 = (TextView) findViewById(R.id.exist4);
        exist5 = (TextView) findViewById(R.id.exist5);
        exist6 = (TextView) findViewById(R.id.exist6);
        exist7 = (TextView) findViewById(R.id.exist7);
        exist8 = (TextView) findViewById(R.id.exist8);
        exist9 = (TextView) findViewById(R.id.exist9);
        exist10 = (TextView) findViewById(R.id.exist10);

        back.setOnClickListener(new View.OnClickListener() {//返回监听器
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseTag.this, NewNote.class);
                setResult(1002,intent);
                ChooseTag.this.finish();
            }
        });
        shuruTag.addTextChangedListener(new TextWatcher() {//输入框监听器
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                tag = shuruTag.getText().toString();
            }
        });
        Tagok.setOnClickListener(new View.OnClickListener() {//确定按钮监听器
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseTag.this, NewNote.class);
                intent.putExtra("tag1", tag);
                setResult(1002,intent);
                ChooseTag.this.finish();
            }
        });
        exist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuruTag.setHint(exist1.getText().toString());
                tag = exist1.getText().toString();
            }
        });
        exist2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuruTag.setHint(exist2.getText().toString());
                tag = exist2.getText().toString();
            }
        });
        exist3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuruTag.setHint(exist3.getText().toString());
                tag = exist3.getText().toString();
            }
        });
        exist4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuruTag.setHint(exist4.getText().toString());
                tag = exist4.getText().toString();
            }
        });
        exist5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuruTag.setHint(exist5.getText().toString());
                tag = exist5.getText().toString();
            }
        });
        exist6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuruTag.setHint(exist6.getText().toString());
                tag = exist6.getText().toString();
            }
        });
        exist7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuruTag.setHint(exist7.getText().toString());
                tag = exist7.getText().toString();
            }
        });
        exist8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuruTag.setHint(exist8.getText().toString());
                tag = exist8.getText().toString();
            }
        });
        exist9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuruTag.setHint(exist9.getText().toString());
                tag = exist9.getText().toString();
            }
        });
        exist10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuruTag.setHint(exist10.getText().toString());
                tag = exist10.getText().toString();
            }
        });
    }
}
