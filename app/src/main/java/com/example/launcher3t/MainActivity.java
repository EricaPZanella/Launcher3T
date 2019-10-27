package com.example.launcher3t;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        carregarFragment(new FragmentTelaInicial());



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment;


        switch (menuItem.getItemId()) {
            case R.id.nav_setaesquerda:
               //bottomNavigationView.setItemBackgroundResource(R.color.colorNave);
                fragment = new FragmentTelaVoltar();
                carregarFragment(fragment);
                return true;
            case R.id.nav_telainicial:
                //bottomNavigationView.setItemBackgroundResource(colorPrimary);
                fragment = new FragmentTelaInicial();
                carregarFragment(fragment);
                return true;
            case R.id.nav_setadireita:
                //bottomNavigationView.setItemBackgroundResource(R.color.colorRed);
                fragment = new FragmentTelaVoltar();
                carregarFragment(fragment);
                return true;

        }

        return false;
    }

    private void carregarFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


}
