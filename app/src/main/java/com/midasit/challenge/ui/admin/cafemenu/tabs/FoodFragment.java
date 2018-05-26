package com.midasit.challenge.ui.admin.cafemenu.tabs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.midasit.challenge.R;
import com.midasit.challenge.model.Item;

import java.util.ArrayList;

/**
 * Created by ichaeeun on 2018. 5. 26..
 */

public class FoodFragment extends Fragment {

    RecyclerView recyclerView;
    ItemAdapter itemAdapter;

    public FoodFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerview);
        itemAdapter = new ItemAdapter(getDummyList(), getActivity());
        recyclerView.setAdapter(itemAdapter);
        Toast.makeText(getActivity(), "onViewCreated", Toast.LENGTH_LONG).show();

    }

    private ArrayList<Item> getDummyList(){
        ArrayList<Item> dataList = new ArrayList<>();
        for(int i=0;i<50;i++){
            Item item = new Item();
            item.name = "치즈케이크";
            item.price = 3500;
            dataList.add(item);
        }

        return dataList;
    }

}
