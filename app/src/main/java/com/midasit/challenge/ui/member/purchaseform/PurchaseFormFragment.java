package com.midasit.challenge.ui.member.purchaseform;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.midasit.challenge.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class PurchaseFormFragment extends Fragment implements View.OnClickListener{

    TextView yearText;
    TextView monthText;

    public PurchaseFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View)inflater.inflate(R.layout.fragment_purchase_form, container, false);
        LinearLayout monthClick= (LinearLayout)view.findViewById(R.id.select_month_ll);

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

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.monthlyforward:
                changeMonths(1);
                break;
            case R.id.monthlybackward:
                changeMonths(-1);
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
