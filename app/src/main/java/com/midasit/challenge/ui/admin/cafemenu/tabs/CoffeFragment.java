package com.midasit.challenge.ui.admin.cafemenu.tabs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.midasit.challenge.R;
import com.midasit.challenge.application.ApplicationController;
import com.midasit.challenge.model.Item;
import com.midasit.challenge.model.ItemResponseObject;
import com.midasit.challenge.model.User;
import com.midasit.challenge.ui.admin.managemember.UserItemAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ichaeeun on 2018. 5. 26..
 */

public class CoffeFragment extends Fragment {

    RecyclerView recyclerView;
    ItemAdapter itemAdapter;

    public CoffeFragment() {
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

        recyclerView.setAdapter(itemAdapter);




    }

    @Override
    public void onResume() {
        super.onResume();
        Call<ItemResponseObject> users = ApplicationController.getInstance().getNetworkService().getItems("1");
        users.enqueue(new Callback<ItemResponseObject>() {
            @Override
            public void onResponse(Call<ItemResponseObject> call, Response<ItemResponseObject> response) {
                if(response.isSuccessful()){ // 응답코드 200

                    ArrayList<Item> items  = response.body().data;
                    Log.d("AAB", items.toString());
                    itemAdapter = new ItemAdapter(items, getActivity());
                    recyclerView.setAdapter(itemAdapter);
                }
                else {
                    Log.i("myTag", "2 들어가고싶다..");
                }
            }

            @Override
            public void onFailure(Call<ItemResponseObject> call, Throwable t) {

            }
        });

        Toast.makeText(getActivity(), "Coffee - onResume", Toast.LENGTH_LONG).show();

    }

    private ArrayList<Item> getDummyList(){
        ArrayList<Item> dataList = new ArrayList<>();
        for(int i=0;i<50;i++){
            Item item = new Item();
            item.name = "아이스 아메리카노";
            item.price = 2000;
            dataList.add(item);
        }

        return dataList;
    }

}
