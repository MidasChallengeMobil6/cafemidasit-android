package com.midasit.challenge.ui.admin.reserve;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.midasit.challenge.R;
import com.midasit.challenge.application.ApplicationController;
import com.midasit.challenge.model.Reserve;
import com.midasit.challenge.model.ReserveResponseObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminReserveFragment extends Fragment {


    public AdminReserveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_reserve, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.reserve_recycler);

        Call<ReserveResponseObject> ReserveResponseObjectCall   = ApplicationController.getInstance().getNetworkService().getPurchases2();
        ReserveResponseObjectCall.enqueue(new Callback<ReserveResponseObject>() {
            @Override
            public void onResponse(Call<ReserveResponseObject> call, Response<ReserveResponseObject> response) {
                ReserveResponseObject a = response.body();
                ArrayList<Reserve> list = response.body().data;

                if (a.err == 0) { // 성공
                    RecyclerReserveAdapter adapter;
                    adapter = new RecyclerReserveAdapter(list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } else {    // 실패
                    Toast.makeText(getContext(), "예약이 하나도 없습니다.",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ReserveResponseObject> call, Throwable t) {

            }
        });








        RecyclerReserveAdapter adapter = new RecyclerReserveAdapter(new String[]{"아메리카노", "홍차"}, new String[]{"2", "1"}
        , new String[]{"2000", "3000"}, new String[]{"홍길동(21546)", "김철수(5153)"}, new String[]{"2018.03.06", "2018.05.27"});
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        return view;
    }

}
