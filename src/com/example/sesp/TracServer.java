package com.example.sesp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.jsonrpc.JSONRPCException;
import org.jsonrpc.JSONRPCHttpClient;

import android.util.Log;

public class TracServer {
String url, user, passw;
String toE;
String html, methods;
boolean login;
JSONArray list;
boolean logged = false;
	
	
	public TracServer(String url, String user, String passw){
		this.url = url;
		this.user = user;
		this.passw = passw;
		
		
	}
	
	
	
	public String getHTMLWIKI(String name) throws InterruptedException{
		final String string;
		final String name1 = name;

		
		final Thread e = new Thread() {

			@Override
			public void run() {
		
				JSONRPCHttpClient req = new JSONRPCHttpClient(url + "/login/rpc");
				 req.setCredentials(user, passw);
				String ticket;
					try {
						html = req.call("wiki.getPageHTML", name1).toString();
						//html = req.call("ticket.create", "ECLIP", "ECLIP").toString();
						
					} catch (JSONRPCException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}};
			e.start();
			e.join();
			
	return html;
	}
	
	
	
	public JSONArray getLIST() throws InterruptedException, JSONException{

		
		final Thread e = new Thread() {

			@Override
			public void run() {
		
				JSONRPCHttpClient req = new JSONRPCHttpClient(url + "/login/rpc");
				req.setCredentials(user, passw);
				String ticket;
					try {
						list = (JSONArray) req.call("wiki.getAllPages");
					
						
					} catch (JSONRPCException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}};
			e.start();
			e.join();
			
			
		
			
	return list;
	}
	
	
	
	public List<String> prova() throws InterruptedException, JSONException{
		JSONArray arr = this.getLIST();
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < arr.length(); i++){
		    list.add(arr.getString(i));
		}
		
		
		
		
		
		
		return list;
		
	}
	
public boolean isValid() throws InterruptedException, JSONException{
		
		List<String> list = this.prova();
		
		if (list.isEmpty())
			return false;
		
		else
			return true;
		
	}
	
	
	
	
	
	
	
	
	
	
}
	
	
	
	
