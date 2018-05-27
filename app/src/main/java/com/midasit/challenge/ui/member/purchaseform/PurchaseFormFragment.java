package com.midasit.challenge.ui.member.purchaseform;


import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.midasit.challenge.R;
import com.midasit.challenge.application.ApplicationController;
import com.midasit.challenge.model.Purchase;
import com.midasit.challenge.model.PurchasesResponseObject;
import com.midasit.challenge.model.User;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class PurchaseFormFragment extends Fragment implements View.OnClickListener{

    TextView yearText;
    TextView monthText;

    RecyclerView recyclerView;
    int userid;


    public PurchaseFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View)inflater.inflate(R.layout.fragment_purchase_form, container, false);
        LinearLayout monthClick= (LinearLayout)view.findViewById(R.id.select_month_ll);
        recyclerView = view.findViewById(R.id.monthlyrecycler);

        // 월년일 다이얼로그 띄우기
        monthClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                /*
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
                        try {
                            Date d = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        } catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();
                        }
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                datePickerDialog.show();
                */
                DialogDatePickerOnlyYear datePickerOnlyYear = new DialogDatePickerOnlyYear(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

                datePickerOnlyYear.show();
            }
        });

        ImageView forwardButton = (ImageView)view.findViewById(R.id.monthlyforward);
        ImageView backwardButton = (ImageView)view.findViewById(R.id.monthlybackward);
        yearText = (TextView)view.findViewById(R.id.year);
        monthText = (TextView)view.findViewById(R.id.month);
        forwardButton.setOnClickListener(this);
        backwardButton.setOnClickListener(this);

        SharedPreferences sf = getActivity().getSharedPreferences("token", MODE_PRIVATE);

        userid = sf.getInt("userId", 0);




        Call<PurchasesResponseObject> purchasesResponseObjectCall   = ApplicationController.getInstance().getNetworkService().getPurchases(String.valueOf(userid), String.valueOf(yearText.getText()),String.valueOf(monthText.getText()));
        purchasesResponseObjectCall.enqueue(new Callback<PurchasesResponseObject>() {
            @Override
            public void onResponse(Call<PurchasesResponseObject> call, Response<PurchasesResponseObject> response) {
                PurchasesResponseObject a = response.body();
                ArrayList<Purchase> list = response.body().data;

                if (a.err == 0) { // 성공
                    PurchaseItemAdapter adapter;
                    adapter = new PurchaseItemAdapter(list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } else {    // 실패
                    Toast.makeText(getContext(), "예약이 하나도 없습니다.",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<PurchasesResponseObject> call, Throwable t) {

            }
        });




        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.monthlyforward:
                changeMonths(1);
                Call<PurchasesResponseObject> purchasesResponseObjectCall   = ApplicationController.getInstance().getNetworkService().getPurchases(String.valueOf(userid), String.valueOf(yearText.getText()),String.valueOf(monthText.getText()));
                purchasesResponseObjectCall.enqueue(new Callback<PurchasesResponseObject>() {
                    @Override
                    public void onResponse(Call<PurchasesResponseObject> call, Response<PurchasesResponseObject> response) {
                        PurchasesResponseObject a = response.body();
                        ArrayList<Purchase> list = response.body().data;

                        if (a.err == 0) { // 성공
                            PurchaseItemAdapter adapter;
                            adapter = new PurchaseItemAdapter(list);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        } else {    // 실패
                            Toast.makeText(getContext(), "예약이 하나도 없습니다.",Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<PurchasesResponseObject> call, Throwable t) {

                    }
                });
                break;
            case R.id.monthlybackward:
                changeMonths(-1);
                Call<PurchasesResponseObject> purchasesResponseObjectCall2   = ApplicationController.getInstance().getNetworkService().getPurchases(String.valueOf(userid), String.valueOf(yearText.getText()),String.valueOf(monthText.getText()));
                purchasesResponseObjectCall2.enqueue(new Callback<PurchasesResponseObject>() {
                    @Override
                    public void onResponse(Call<PurchasesResponseObject> call, Response<PurchasesResponseObject> response) {
                        PurchasesResponseObject a = response.body();
                        ArrayList<Purchase> list = response.body().data;

                        if (a.err == 0) { // 성공
                            PurchaseItemAdapter adapter;
                            adapter = new PurchaseItemAdapter(list);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        } else {    // 실패
                            Toast.makeText(getContext(), "예약이 하나도 없습니다.",Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<PurchasesResponseObject> call, Throwable t) {

                    }
                });
                break;
        }
    }

    // forward 버튼(->) , backward 버튼(<-)을 눌렀을 때 그에 따른 달력을 바꾸는 메소드
    private void changeMonths(int months){
        int year = Integer.valueOf(yearText.getText().toString());
        int month = Integer.valueOf(monthText.getText().toString());

        if(month + months < 1){
            yearText.setText(String.valueOf(year - 1));
            monthText.setText("12");
        }else if(month + months > 12){
            yearText.setText(String.valueOf(year + 1));
            monthText.setText("1");
        }else{
            monthText.setText(String.valueOf(month + months));
        }
    }
}
