package com.example.sesp;

import java.net.URI;
import java.util.Vector;

import org.apache.http.conn.HttpHostConnectException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsonrpc.JSONRPCException;
import org.jsonrpc.JSONRPCHttpClient;
import org.jsonrpc.JSONRPCThreadedHttpClient;



import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class WikiActivity extends Activity {
	private TextView status;
	String string;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wiki);
		
		Bundle bundle = getIntent().getExtras();
	     String wiki = bundle.getString("Wiki");
		//JSONRPCHttpClient req = new JSONRPCHttpClient("http://10.7.152.228:8001/myproject/login/rpc");
		//req.setCredentials("luca92", "16071950");
		
		
		TracServer trac = new TracServer("http://172.29.12.37:8001/myproject","luca92", "16071950");
	    status = (TextView)findViewById(R.id.spinner);
			//WIKI wiki = new WIKI();
			try {
				status.setText(Html.fromHtml(trac.getHTMLWIKI("WikiStart")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
		
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
				

			
	

	}

