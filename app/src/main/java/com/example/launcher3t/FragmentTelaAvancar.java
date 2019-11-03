package com.example.launcher3t;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
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
        view = inflater.inflate(R.layout.fragment_fragment_tela_avancar, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        init();
    }

    private void init(){
        recyclerView = view.findViewById(R.id.rcycler1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        refreshList("");
        //appInfoArrayList=loadAppInf("");
        //mAdapter  = new AppInfoArrayAdapter(getContext(),R.layout.item_list_app, appInfoArrayList);
        //recyclerView.setAdapter(mAdapter);


        //configurando click do recyclerview.
        recyclerView.addOnItemTouchListener( new RecyclerItemClickListener(
                getActivity().getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        AppInfo appInfo=(AppInfo)  mAdapter.mDataset.get(position);
                        Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage ( appInfo.pname  );
                        Toast.makeText(getActivity().getApplicationContext(),appInfo.appname,Toast.LENGTH_LONG).show();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));


}


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

        //aplicativos tela inicial
        //String [] listaPacoteAppTela = {"com.google.android.dialer","com.google.android.apps.messaging","com.android.contacts", "com.google.android.deskclock", "com.android.calculator2", "com.google.android.music"};
        //String [] listaRenaimeTela = {"Chamadas","Mensagens","Contatos", "Despertador", "Câmera", "Reprodutor de música"};

        //aplicativos tela2
        String [] listaPacoteAppTela = {"com.google.android.apps.maps", "com.google.android.youtube", "com.google.android.apps.photos", "com.google.android.videos", "com.android.settings"};
        String [] listaRenaimeTela ={"Mapas - GPS", "Youtube - Vídeos", "Galeria fotos", "Reprodutor de vídeos","Configurações do celular"};


        int [] listaRecurosIcones = {R.drawable.waze,R.drawable.youtube,R.drawable.fotos,R.drawable.reprodutor_video,R.drawable.configuracao};
        Drawable[] icons = {null,null,null,null,null};
        int i =0;
        ImageView v;
        for (i=0;i<listaRecurosIcones.length;i++){
            v=new ImageView(getActivity().getApplicationContext());
            v.setImageResource(listaRecurosIcones[i]);
            icons [i] =  v.getDrawable() ;
        }

        for ( i=0; i<listaPacoteAppTela.length ; i++){
            for ( AppInfo appinfo:appInfoArrayList) {
                if (appinfo.pname.equals( listaPacoteAppTela[i]) ) {
                    appinfo.appname = listaRenaimeTela[i];
                    appinfo.icon = icons[i];
                    appInfoArrayListResult.add(appinfo);

                }
            }
        }
        return  appInfoArrayListResult;

    };


    public void refreshList(String key ){
        aplicativosList=loadAppInf(key);
        mAdapter  = new AppInfoArrayAdapter(getContext(),
                R.layout.item_list_app,
                aplicativosList );
        recyclerView.setAdapter(mAdapter);
    }

}
