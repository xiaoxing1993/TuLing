package com.xiaoxing.tuling;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements HttpGetDataListener,OnClickListener{

	private HttpData httpData;
	private String uri;
	public static final String TEXT="text";
	private List<ListData> list;
	private ListView lv;
	private EditText et;
	private Button but;
	public static final String URI="http://www.tuling123.com/openapi/api?key=107d65b5a80509c35d31cd063810f63e&info=";
	private MyAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	@Override
	public void getDataUri(String data) {

		parseText(data);
		
		
	}
	public void initView(){
		lv=(ListView)findViewById(R.id.lv);
		et=(EditText)findViewById(R.id.et);
		but=(Button)findViewById(R.id.but);
		but.setOnClickListener(this);
		list=new ArrayList<ListData>();
		adapter=new MyAdapter(this, list);
		lv.setAdapter(adapter);
		
	}

	public void parseText(String data){
		
		try {
			JSONObject json=new JSONObject(data);
			String text=json.getString(TEXT);
			ListData listData=new ListData(text);
			listData.setFlag(ListData.RECEIVER);
			list.add(listData);
			adapter.notifyDataSetChanged();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onClick(View v) {

		String etText=et.getText().toString();
		ListData data=new ListData(etText);
		data.setFlag(ListData.SEND);		
		uri=URI+etText;		
		list.add(data);
		et.setText("");
		InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
		adapter.notifyDataSetChanged();
		httpData=(HttpData) new HttpData(uri, this).execute();
	}
}
