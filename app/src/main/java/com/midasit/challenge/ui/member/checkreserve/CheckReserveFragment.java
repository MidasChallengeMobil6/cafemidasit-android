package com.midasit.challenge.ui.member.checkreserve;


/*
사용자에서 신청.준비중.완료 뷰.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.midasit.challenge.R;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckReserveFragment extends Fragment {

    Handler handler;
    TextView conditionView;
    private int condition = 0; // 0 신청, 1 준비중, 2 완료

    private Socket mSocket;
    public CheckReserveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_reserve, container, false);
        conditionView = view.findViewById(R.id.reserve_condition_tv);
        handler =new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(condition == 0){
                    conditionView.setBackgroundColor(getResources().getColor(R.color.greenish_teal));
                    conditionView.setText("신청중입니다.");
                }else if(condition == 1){
                    conditionView.setBackgroundColor(getResources().getColor(R.color.yellow));
                    conditionView.setText("준비중입니다!~.");
                }else if(condition ==2){
                    conditionView.setBackgroundColor(getResources().getColor(R.color.dodger_blue));
                    conditionView.setText("완료되었습니다~");
                }

                handler.postDelayed(this, 1000);
            }
        });

        Button button1 = (Button)view.findViewById(R.id.reserve_contact);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                condition = 0;
            }
        });
        Button button2 = (Button)view.findViewById(R.id.reserve_ready);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                condition = 1;
            }
        });
        Button button3 = (Button)view.findViewById(R.id.reserve_done);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                condition = 2;
            }
        });


        //소켓 통신 시도
        try{
            mSocket = IO.socket("http://192.168.0.44:8080");
            mSocket.connect();
            Log.e("11111", "잘되요");
            mSocket.on("my event", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.e("22222", "잘되요");
                    String str = args[1].toString();
                    Log.e("3333",str);
                }
            });
        }catch (URISyntaxException e){
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
    }
}
