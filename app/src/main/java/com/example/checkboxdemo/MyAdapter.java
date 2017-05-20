package com.example.checkboxdemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private Context myContext;
	private List<String> data;
	private TextView tvSure;
	private boolean isEdit = false;
	private Map<Integer, String> map;
	private Map<Integer, Boolean> bMap = new HashMap<Integer, Boolean>();
	int count = 0;
	public MyAdapter(Context myContext, List<String> data, TextView tvSure,Map<Integer, String> map) {
		super();
		this.myContext = myContext;
		this.data = data;
		this.tvSure = tvSure;
		this.map = map;
		for(int i = 0; i < data.size();i++){
			bMap.put(i, false);
		}
	}

	public void setData(List<String> data){
		this.data = data;
	}
	
	public void setEdit(boolean isEdit){
		this.isEdit = isEdit;
	}
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(myContext, R.layout.item, null);
			holder.tvContent = (TextView)convertView.findViewById(R.id.tvContent);
			holder.cbChoose = (CheckBox)convertView.findViewById(R.id.cbChoose);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		final String info = data.get(position);
		holder.tvContent.setText(info);
		if(isEdit){
			holder.cbChoose.setVisibility(View.VISIBLE);
			
			holder.cbChoose.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if(isChecked){
						map.put(position, info);
						bMap.put(position, isChecked);
					}else{
						map.remove(position);
						bMap.put(position, isChecked);
					}
					
					tvSure.setText("确定("+map.size()+")");
				}
			});
			
			tvSure.setText("确定("+map.size()+")");
			holder.cbChoose.setChecked(bMap.get(position));
		}else{
			map.clear();
			for(int i = 0; i < data.size();i++){
				bMap.put(i, false);
			}
			holder.cbChoose.setVisibility(View.GONE);
		}
		return convertView;
	}

	class ViewHolder {
		TextView tvContent;
		CheckBox cbChoose;
	}
}
