package com.example.launcher3t;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTelaInicial extends Fragment {

    View view;
    RecyclerView recyclerView;
    ArrayList<AppInfo>  appInfoArrayList;
    ArrayList<AppInfo>  appInfoArrayListResult;
    List<ApplicationInfo> applicationInfoList;
    ArrayList<AppInfo> aplicativosList;
    AppInfoArrayAdapter mAdapter;

    Context context;
    GridLayoutManager layoutManager;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        init();
    }

    private void init(){
        recyclerView = view.findViewById(R.id.rcycler1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        aplicativosList=loadAppInf("");
        mAdapter  = new AppInfoArrayAdapter(getContext(),R.layout.item_list_app, aplicativosList);
        recyclerView.setAdapter(mAdapter);

    }

    public FragmentTelaInicial() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_tela_inicial, container, false);

        //layoutManager = new GridLayoutManager(getContext(), 2);

        //appInfoArrayList = obterContexto(getContext());

        return view;
    }


//    public void obterContexto(Context context){
//        applicationInfoList = mContext.getPackageManager().getInstalledApplications(0);
//        appInfoArrayList = new ArrayList<AppInfo>();
//
//    }


    public ArrayList<AppInfo> loadAppInf(final String key) {
        //applicationInfoList = mContext.getPackageManager().getInstalledApplications(0);
        applicationInfoList = getActivity().getPackageManager().getInstalledApplications(0);
        appInfoArrayList = new ArrayList<AppInfo>();

        appInfoArrayListResult = new ArrayList<AppInfo>();

        // LoadApps using Intent Query and filter intent category Launcher.
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        //List<ResolveInfo> pkgAppsList = mContext.getPackageManager().queryIntentActivities(mainIntent, 0);
        List<ResolveInfo> pkgAppsList = getActivity().getPackageManager().queryIntentActivities(mainIntent, 0);
        Iterator<ResolveInfo> resolveInfoIterator = pkgAppsList.iterator();

        //Create arrayListAppInfo, (Easily optimized, but not done)
        while (resolveInfoIterator.hasNext()) {
            ResolveInfo resolveInfo = resolveInfoIterator.next();
            //appInfoArrayList.add(new AppInfo(resolveInfo.activityInfo.applicationInfo, mContext.getPackageManager()));
            appInfoArrayList.add(new AppInfo(resolveInfo.activityInfo.applicationInfo, getActivity().getPackageManager()));
        }

        String [] listaPacoteAppTela = {"com.google.android.dialer","com.google.android.apps.messaging","com.android.contacts", "com.google.android.deskclock", "com.android.calculator2", "com.google.android.music"};
        String [] listaRenaimeTela = {"Chamadas","Mensagens","Contatos", "Despertador", "Câmera", "Reprodutor de música"};

        int i=0;
        for ( i=0; i<listaPacoteAppTela.length ; i++){
            for ( AppInfo appinfo:appInfoArrayList) {
                if (appinfo.pname.equals( listaRenaimeTela[i]) ) {
                    appinfo.appname = listaRenaimeTela[i];
                    //String NovoCaminho = "@drawable'\'mensagem.bmp";
                    //appinfo.appname.equals("Chamadas");
                    //appinfo.icon = Drawable.createFromPath(NovoCaminho);
                    appInfoArrayListResult.add(appinfo);

                }
            }
        }
        return  appInfoArrayListResult;

    };


//    public void refreshList(String key ){
//        aplicativosList=loadAppInf(key);
//        mAdapter  = new AppInfoArrayAdapter(getContext(),
//                R.layout.item_list_app,
//                aplicativosList );
//        recyclerView.setAdapter(mAdapter);
//    }

}
