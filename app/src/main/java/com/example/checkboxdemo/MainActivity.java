package com.example.checkboxdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {

	private Context myContext = null;
	private ListView lvData = null;
	private List<String> data = null;
	private TextView tvSure = null;
	private TextView tvCancel = null;
	private LinearLayout llBottom = null;
	private MyAdapter adapter = null;
	private TextView tvTotal = null;
	private Map<Integer, String> map = new HashMap<Integer, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myContext = MainActivity.this;
        initView();
        initData();
        setAdapter();
        setLinstener();
    }
    
	private void setLinstener() {
		tvCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				adapter.setEdit(false);
				adapter.notifyDataSetChanged();
				llBottom.setVisibility(View.GONE);
			}
		});
		
		tvSure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//确定删除
				for(int i : map.keySet()){
					data.remove(map.get(i));
				}
				
				adapter.setEdit(false);
				adapter.notifyDataSetChanged();
				llBottom.setVisibility(View.GONE);
				tvTotal.setText("总数:" + data.size());
			}
		});
		
		lvData.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				adapter.setEdit(true);
				adapter.notifyDataSetChanged();
				llBottom.setVisibility(View.VISIBLE);
				return false;
			}
		});
	}

	private void setAdapter() {
		tvTotal.setText("总数:"+data.size());
		adapter = new MyAdapter(myContext, data, tvSure,map);
		lvData.setAdapter(adapter);
	}

	private void initData() {
		data = new ArrayList<String>();
		for(int i = 0; i < 30; i++){
			data.add("小凯" + i);
		}
	}

	private void initView() {
		lvData = (ListView)super.findViewById(R.id.listView1);
		llBottom = (LinearLayout)super.findViewById(R.id.llBottom);
		tvCancel = (TextView)super.findViewById(R.id.tvCancel);
		tvSure = (TextView)super.findViewById(R.id.tvSure);
		tvTotal = (TextView)super.findViewById(R.id.tvTotal);
	}

}
