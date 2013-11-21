package com.example.sesp;

import org.json.JSONException;
import org.json.JSONObject;


import engine.Login;
import engine.Ticket;
import engine.TracServer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ModifyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_modify);
		
		Login login = new Login();

		Bundle bundle = getIntent().getExtras();
	    int id = bundle.getInt("id");
	    Ticket ticket = new Ticket(id);
	    try {
			ticket.retrieveData();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String[] data = ticket.getData();
		
		//{summary, keywords, status, resolution,type,version,milestone,reporter,priority,component,owner,origine, modification,description};
		
		final TracServer server = login.getTrac();

		final EditText edit = (EditText) findViewById(R.id.summary);
		final EditText edit2 = (EditText) findViewById(R.id.description);
		final TextView text = (TextView) findViewById(R.id.title);
		
		edit.setText(data[0]);
		edit2.setText(data[13]);
		text.setText("Modify Ticket #" + id);
		
		
		
		final Spinner sp = (Spinner) findViewById(R.id.milestone);
		final Spinner sp2 = (Spinner) findViewById(R.id.component);
		final Spinner sp3 = (Spinner) findViewById(R.id.version);
		final Spinner sp4 = (Spinner) findViewById(R.id.type);
		final Spinner sp5 = (Spinner) findViewById(R.id.priority);

		ArrayAdapter<String> adp;
		ArrayAdapter<String> adp2;
		ArrayAdapter<String> adp3;
		try {
			adp = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, server.getMilestones());
			adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			sp.setAdapter(adp);

			adp2 = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, server.getComponent());
			adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			sp2.setAdapter(adp2);

			adp3 = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, server.getVersion());
			adp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			sp3.setAdapter(adp3);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	try {
		

		sp.setSelection(getSpinnerId(server.getMilestones(),data[6]));
		sp2.setSelection(getSpinnerId(server.getComponent(),data[9]));
		sp3.setSelection(getSpinnerId(server.getVersion(),data[5]));
		
		

		String types[] = {"defect","enhancement","task"};
		String priority[] = {"major","blocker","critical","minor","trivial"};
		
		
		sp4.setSelection(getSpinnerId(types ,data[4]));
		sp5.setSelection(getSpinnerId(priority ,data[8]));
		
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	

 Button b1 = (Button) findViewById(R.id.button1);
	b1.setOnClickListener(new View.OnClickListener(){

		@Override
		public void onClick(View arg0) {
		
		   String summary = edit.getText().toString();
		   String description = edit2.getText().toString();
		   
			
			String milestone=sp.getSelectedItem().toString();
			String component = sp2.getSelectedItem().toString();
			String version = sp3.getSelectedItem().toString();
			String type = sp4.getSelectedItem().toString();
			String priority = sp5.getSelectedItem().toString();
			
			
			
			

			final JSONObject result = new JSONObject();
			try {
				result.put("summary", summary);
				result.put("description", description);
				result.put("milestone", milestone);
				result.put("component", component);
				result.put("version", version);
				result.put("type", type);
				result.put("priority", priority);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		   
		   
		   
			
			
			
		
		
		
		if (summary.length() == 0 || description.length()==0){
			showDialog(2);
			
			
		} 
		
		else{
			Bundle bundle = getIntent().getExtras();
		    int id = bundle.getInt("id");
		    Ticket ticket = new Ticket(id);
		    try {
				ticket.retrieveData();
				ticket.modifyTicket(result);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		showDialog(1);
		}
			
		
			
			
			
		}
		
		
		
		
	});


	


		
		
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prova, menu);
		return true;
	}
	
	
	
	public int getSpinnerId(String[] string, String value){
		int result = 0;
		for (int i = 0; i <string.length; i++){
			if (string[i].equals(value)){
				result=i;
				break;
			}
			
			
		}
		
		
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 1: {
			// Create out AlterDialog
			Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Your Ticket has been successfully modified!");
			builder.setCancelable(true);
			builder.setPositiveButton("OK", new OkOnClickListener());
			AlertDialog dialog = builder.create();
			dialog.show();
			break;
		}
		
		
		case 2: {
			// Create out AlterDialog
			Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("You haven't filled all fields!");
			builder.setCancelable(true);
			builder.setPositiveButton("OK", null);
			AlertDialog dialog = builder.create();
			dialog.show();
			break;
		}
		
		
		

		}
		
		return super.onCreateDialog(id);
	}

	private final class OkOnClickListener implements
    DialogInterface.OnClickListener {
  public void onClick(DialogInterface dialog, int which) {
	  Intent intent = new Intent(ModifyActivity.this, TicketActivity.class);
		intent.putExtra("Title", "ACTIVE TICKETS IN YOUR TRAC");
		intent.putExtra("String", "status!=closed");
	    startActivity(intent);
		
  }





	}

	
	
}
