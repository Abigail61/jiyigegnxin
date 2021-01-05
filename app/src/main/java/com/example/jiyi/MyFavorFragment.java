package com.example.jiyi;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import com.example.jiyi.R;
import com.example.jiyi.db.DBOpenHelper;

import java.util.ArrayList;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFavorFragment extends Fragment {
    ArrayList<Mynote> mynotes = new ArrayList<>();
    MyListAdapter myListAdapter;
    View contentView;

    public MyFavorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        demo();
        super.onStart();
    }

    private void demo() {
        mynotes.add(new Mynote("1月2日", "null"));
        mynotes.add(new Mynote("1月2日", "null"));
        mynotes.add(new Mynote("1月2日", "null"));
        mynotes.add(new Mynote("1月2日", "null"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //创建界面MsgFragment
        contentView = inflater.inflate(R.layout.fragment_my_mylikelist, container, false);
        //界面初始化
        myListAdapter = new MyListAdapter();
        ListView listView = (ListView) contentView.findViewById(R.id.mylike_lv);
        listView.setAdapter(myListAdapter);
        return contentView;
    }

    public class Mynote {
        public String timemark;
        public String notepic;

        public Mynote(String timemark, String notepic) {
            this.timemark = timemark;
            this.notepic = notepic;
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
                converView = getLayoutInflater().inflate(R.layout.item_my_myfavor, parent, false);
            }
            Mynote c = (Mynote) getItem(position);
            TextView textView = (TextView) converView.findViewById(R.id.favor_time);
            //ImageView没写
            textView.setText(c.timemark);

            return converView;
        }
    }
}