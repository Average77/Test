package com.isabella.test;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> list = new ArrayList<>();
    private Button bt1;
    private Button bt2;
    private PopupWindow pw;
    private MyAdapter adapter;
    private TextView textView;
    private int i;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button1);
        int heightPixels = getResources().getDisplayMetrics().heightPixels;
        i = heightPixels / 2;
        initPopuWindow();
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.GONE);
                view.setBackgroundResource(R.drawable.sp_popup_1);
                pw.showAtLocation(MainActivity.this.findViewById(R.id.line1), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.VISIBLE);
                view.setBackgroundResource(R.drawable.sp_popup);
                pw.showAtLocation(MainActivity.this.findViewById(R.id.line1), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }

    private void initPopuWindow() {
        /**
         * 第一个参数是view
         * 第二个参数是PopupWindow的宽
         * 第三个参数是PopupWindow的高
         */


        view = View.inflate(this, R.layout.popup_view, null);
        view.setBackgroundResource(R.drawable.sp_popup);
        textView = (TextView) view.findViewById(R.id.textview);
        Button pope_bt = (Button) view.findViewById(R.id.popup_bt);
        ListView lv = (ListView) view.findViewById(R.id.lv);

        initData();
        adapter = new MyAdapter(this, list);
        lv.setAdapter(adapter);

        //创建PopupWindow

        pw = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, i);
        pw.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#efefef")));
        //点击PopupWindow的外部消失PopupWindow
        pw.setOutsideTouchable(true);
        pw.setAnimationStyle(R.style.Animation);
        //点击返回键让PopupWindow消失
        pw.setFocusable(true);
        pope_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pw.dismiss();

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
                pw.dismiss();
            }
        });


    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            list.add("Test"+i);
        }
    }

}
