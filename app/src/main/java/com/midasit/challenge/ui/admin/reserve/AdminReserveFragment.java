package com.midasit.challenge.ui.admin.reserve;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midasit.challenge.R;

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
        RecyclerReserveAdapter adapter = new RecyclerReserveAdapter(new String[]{"아메리카노", "홍차"}, new String[]{"2", "1"}
        , new String[]{"2000", "3000"}, new String[]{"홍길동(21546)", "김철수(5153)"});
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        return view;
    }

}
