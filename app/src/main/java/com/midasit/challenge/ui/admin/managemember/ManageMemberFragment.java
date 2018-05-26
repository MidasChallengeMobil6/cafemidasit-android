package com.midasit.challenge.ui.admin.managemember;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.midasit.challenge.R;
import com.midasit.challenge.model.Item;
import com.midasit.challenge.model.User;
import com.midasit.challenge.ui.admin.cafemenu.tabs.ItemAdapter;

import java.util.ArrayList;

/**
 * Created by ichaeeun on 2018. 5. 26..
 */

public class ManageMemberFragment extends Fragment {

    RecyclerView recyclerView;
    ItemAdapter itemAdapter;

    public ManageMemberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_member, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fb = view.findViewById(R.id.addmember_fab);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegisterActivity.class));
            }
        });

        recyclerView = view.findViewById(R.id.recyclerview);
        UserItemAdapter userItemAdapter= new UserItemAdapter(getDummyList());
        recyclerView.setAdapter(userItemAdapter);

        Toast.makeText(getActivity(), "onViewCreated", Toast.LENGTH_LONG).show();
    }

    private ArrayList<User> getDummyList(){
        ArrayList<User> dataList = new ArrayList<>();
        for(int i=0;i<50;i++){
            User user = new User(0, "김가나", "abcds2@midasit.com", "010-1234-5678", "1995.02.18", "201203938", null,"20180526");
            dataList.add(user);
        }

        return dataList;
    }
}
