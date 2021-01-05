package com.example.jiyi;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiyi.R;
import com.example.jiyi.db.DBOpenHelper;
import com.example.jiyi.db.Sticker;
import com.example.jiyi.db.User;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyNoteFragment extends Fragment {
    ArrayList<Mynote> mynotes = new ArrayList<>();
    MyListAdapter myListAdapter;
    View contentView;
    String username;

    public MyNoteFragment(String username) {
        // Required empty public constructor
        this.username = username;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getdata();
    }

    @Override
    public void onStart() {
        getdata();
        //getdatachanged();
        myListAdapter.notifyDataSetChanged();
        super.onStart();
    }

    private void getdata() {
        Context context = getActivity().getApplicationContext();
        DBOpenHelper mDBOpenHelper = new DBOpenHelper(context);
        ArrayList<Sticker> data = mDBOpenHelper.getPersonalStickerData(username);
        mynotes = new ArrayList<>();
        if (data.size() != 0) {
            for (int i = 0; i < data.size(); i++) {
                Sticker sticker = data.get(i);
                mynotes.add(new Mynote(sticker.getSdate(), sticker.getImagepath(), sticker.getStickerText()));
            }
        }
    }

    private void getdatachanged() {
        Context context = getActivity().getApplicationContext();
        DBOpenHelper mDBOpenHelper = new DBOpenHelper(context);
        ArrayList<Sticker> data = mDBOpenHelper.getPersonalStickerData(username);

        if (data.size() != 0) {
            for (int i = data.size() - 1; i < data.size(); i++) {
                Sticker sticker = data.get(i);
                //Mynote mynote = mynotes.get(mynotes.size()-1);
                //if(sticker.getSdate().equals(mynote.timemark)){break;}
                //else{
                mynotes.add(new Mynote(sticker.getSdate(), sticker.getImagepath(), sticker.getStickerText()));
                //}
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //创建界面MsgFragment
        contentView = inflater.inflate(R.layout.fragment_my_mynotelist, container, false);
        //界面初始化
        myListAdapter = new MyListAdapter();
        ListView listView = (ListView) contentView.findViewById(R.id.mynote_lv);
        listView.setAdapter(myListAdapter);
        return contentView;
    }

    public class Mynote {
        public String timemark;
        public String notepic;
        public String text;

        public Mynote(String timemark, String notepic, String text) {
            this.timemark = timemark;
            this.notepic = notepic;
            this.text = text;
        }
    }

    public class MyListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mynotes.size();
        }

        @Override
        public Object getItem(int position) {
            return mynotes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View converView, ViewGroup parent) {
            if (converView == null) {
                converView = getLayoutInflater().inflate(R.layout.item_my_mynote, parent, false);
            }
            Mynote c = (Mynote) getItem(position);
            TextView textView = (TextView) converView.findViewById(R.id.timemark);
            TextView text = (TextView) converView.findViewById(R.id.mynotetext);
            CustomRoundAngleImageView image = (CustomRoundAngleImageView) converView.findViewById(R.id.notepic);
            textView.setText(c.timemark);
            text.setText(c.text);
            if (c.notepic != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(c.notepic);
                image.setImageBitmap(bitmap);
            }
            return converView;
        }
    }
}