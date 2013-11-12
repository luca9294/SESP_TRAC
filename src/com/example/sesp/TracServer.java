package com.example.sesp;

import org.jsonrpc.JSONRPCException;
import org.jsonrpc.JSONRPCHttpClient;

import android.util.Log;

public class TracServer {
String url, user, passw;
String toE;
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
	
	
	
	
	
	
}
	
	
	
	
