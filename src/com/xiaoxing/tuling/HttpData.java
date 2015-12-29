package com.xiaoxing.tuling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class HttpData extends AsyncTask<String, Void, String>{

	private HttpClient httpClient;
	private HttpGet  httpGet;
	private HttpResponse httpResponse;
	private HttpEntity httpEntity;
	private InputStream in;
	private String uri;
	private HttpGetDataListener listener;
	
	public HttpData(String uri,HttpGetDataListener listener){
		this.listener=listener;
		this.uri=uri;
	}
	@Override
	protected String doInBackground(String... params) {

		try {

			httpClient=new DefaultHttpClient();
			httpGet=new HttpGet(uri);
			httpResponse=httpClient.execute(httpGet);
			httpEntity=httpResponse.getEntity();
			in=httpEntity.getContent();
			BufferedReader br=new BufferedReader(new InputStreamReader(in));
			String line =null;
			StringBuffer sb=new StringBuffer();
			while((line=br.readLine())!=null){
				
				sb.append(line);
			}
			return sb.toString();
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return null;
	}
   @Override
     protected void onPostExecute(String result) {

	   listener.getDataUri(result);
	   super.onPostExecute(result);
        }
}
