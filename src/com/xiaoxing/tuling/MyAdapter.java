package com.xiaoxing.tuling;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	
	private Context mContext;
	private List<ListData> mList;
	private View v;
	private TextView tv;
	
	public MyAdapter(Context context,List list){
		
		mContext=context;
		mList=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        if(mList.get(position).getFlag()==ListData.RECEIVER){
        	v=inflater.inflate(R.layout.itemleft, null);
        	  tv=(TextView)v.findViewById(R.id.item_left);
        }
        else{
        	v=inflater.inflate(R.layout.itemright, null);
        	 tv=(TextView)v.findViewById(R.id.item_right);
        }
        tv.setText(mList.get(position).getContent());
		return v;
	}

}
