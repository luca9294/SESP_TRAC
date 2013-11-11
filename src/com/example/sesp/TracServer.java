package com.example.sesp;

import org.jsonrpc.JSONRPCException;
import org.jsonrpc.JSONRPCHttpClient;

import android.util.Log;

public class TracServer {
String url, user, passw;

String html, methods;
boolean login;

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
	
						
					} catch (JSONRPCException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}};
			e.start();
			e.join();
			
	return html;
	}
	
	
	public boolean validLogin(){
		
		
		final Thread f = new Thread() {

			@Override
			public void run() {
		
				JSONRPCHttpClient req = new JSONRPCHttpClient(url + "/login/rpc");
				req.setCredentials(user, passw);
				String ticket;
					try {
						methods = req.call("system.listMethods()").toString();
						
					} catch (JSONRPCException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}};
			
			f.start();
			try {
				f.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

			if (methods.length()==0){
				return false;}
			
			else
				logged=true;
				return true;}
	

	}
	
	
	
	
	
	
	
	
	
	
