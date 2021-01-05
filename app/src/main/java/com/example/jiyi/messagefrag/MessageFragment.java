package com.example.jiyi.messagefrag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jiyi.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        return  view;
    }

//    public void onClick(View view){
//        switch (view.getId()){
//            case R.id.msg_mid_iv_like:
//                Intent it1 = new Intent(getActivity(), LikeActivity.class);
//                startActivity(it1);
//                break;
//            case R.id.msg_mid_iv_fans:
//
//                break;
//            case R.id.msg_mid_iv_comment:
//
//                break;
//        }
//    }

}
