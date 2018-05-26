package com.midasit.challenge.ui.member.purchaseform;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.midasit.challenge.R;
import com.midasit.challenge.ui.member.checkreserve.CheckReserveFragment;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.testContainer, new CheckReserveFragment());
        fragmentTransaction.commit();
    }
}
