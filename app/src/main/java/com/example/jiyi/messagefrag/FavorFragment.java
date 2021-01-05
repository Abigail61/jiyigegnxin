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
public class FavorFragment extends Fragment {


    public FavorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favor, container, false);
    }

}
