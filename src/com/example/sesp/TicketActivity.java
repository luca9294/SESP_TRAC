package com.example.sesp;

import org.json.JSONException;
import org.jsonrpc.JSONRPCException;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.widget.ListView;

public class TicketActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ticket);
		Login login = new Login();
	
		TracServer server = login.getTrac();
		ListView listView = (ListView) findViewById(R.id.sample);
		CustomAdapter customAdapter;
		try {
			customAdapter = new CustomAdapter(server.getActiveTickets());
			listView.setAdapter(customAdapter);
		} catch (JSONRPCException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	
	
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ticket, menu);
		return true;
	}

}
