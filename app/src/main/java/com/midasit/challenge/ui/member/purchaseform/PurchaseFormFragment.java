package com.midasit.challenge.ui.member.purchaseform;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midasit.challenge.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PurchaseFormFragment extends Fragment {


    public PurchaseFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View)inflater.inflate(R.layout.fragment_purchase_form, container, false);
        return view;
    }

}
