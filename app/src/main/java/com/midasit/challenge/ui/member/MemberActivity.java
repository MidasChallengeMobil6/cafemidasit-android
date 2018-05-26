package com.midasit.challenge.ui.member;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.midasit.challenge.R;
import com.midasit.challenge.ui.admin.BottomNavigationViewHelper;
import com.midasit.challenge.ui.admin.cafemenu.MenuFragment;
import com.midasit.challenge.ui.admin.managemember.ManageMemberFragment;

public class MemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_member);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigationview);
        BottomNavigationViewHelper bottomNavigationViewHelper = new BottomNavigationViewHelper();
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        MenuFragment menuFragment = new MenuFragment();
        fragmentTransaction.replace(R.id.container, menuFragment);
        fragmentTransaction.commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {

            case R.id.order:
                Fragment orderFragment = new Fragment();
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.container, orderFragment);
                fragmentTransaction1.commit();
                return true;
            case R.id.search:
                MenuFragment menuFragment = new MenuFragment();
                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.container, menuFragment);
                fragmentTransaction2.commit();
                return true;
            case R.id.manage:
                ManageMemberFragment manageFragement = new ManageMemberFragment();
                FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.container, manageFragement);
                fragmentTransaction3.commit();
                return true;
        }
        return false;
    };



}


