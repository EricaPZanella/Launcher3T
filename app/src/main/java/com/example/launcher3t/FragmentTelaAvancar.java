package com.example.launcher3t;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTelaAvancar extends Fragment {

    View view;
    RecyclerView recyclerView;
    ArrayList<AppInfo> appInfoArrayList;
    ArrayList<AppInfo>  appInfoArrayListResult;
    List<ApplicationInfo> applicationInfoList;
    ArrayList<AppInfo> aplicativosList;
    AppInfoArrayAdapter mAdapter;
    Context context;
    GridLayoutManager layoutManager;


    public FragmentTelaAvancar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_tela_avancar, container, false);
    }

}
