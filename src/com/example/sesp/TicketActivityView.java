package com.example.sesp;

import org.json.JSONException;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class TicketActivityView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ticket_activity_view);
		
	
		Bundle bundle = getIntent().getExtras();
	    int id = bundle.getInt("id");
	    Ticket ticket = new Ticket(id);
	    try {
			ticket.retrieveData();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	
	    String data[] = ticket.getData(); 
	    
	    TextView title = (TextView)findViewById(R.id.textView0);
	    TextView reporter = (TextView)findViewById(R.id.textView1);
	    TextView priority = (TextView)findViewById(R.id.textView2);
	    TextView component = (TextView)findViewById(R.id.textView3);
	    TextView owner = (TextView)findViewById(R.id.textView4);
	    TextView milestone = (TextView)findViewById(R.id.textView5);
	    TextView create = (TextView)findViewById(R.id.textView7);
	    TextView mod = (TextView)findViewById(R.id.textView8);
	    TextView des = (TextView)findViewById(R.id.textView6);
	    
	    
	    //{summary, keywords, status, resolution,type,version,milestone,reporter,p,origine, modification};
	    
	    title.setText("#" + ticket.getID() + " " + data[0]+ " \n" + data[2]+ " " + data[4]);
	    reporter.setText(data[7]);
	    priority.setText(data[8]);
	    component.setText(data[9]);
	    owner.setText(data[10]);
	    milestone.setText(data[6]);
	    create.setText(data[11]);
	    mod.setText(data[12]);
	    des.setText(data[13]);
	    
	    
	    
	    
	
	
	
	
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ticket_activity_view, menu);
		return true;
	}
	
	
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
		 Bundle bundle = getIntent().getExtras();
		    int id = bundle.getInt("id");
		    Ticket ticket = new Ticket(id);
		    Login login = new Login();
		    try {
				ticket.retrieveData();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    String[] data = ticket.getData();
		 
		
		 
		 switch (item.getItemId()) {
	       //Accept Ticket
	        case R.id.item1:
	        	
	        	AlertDialog.Builder alert = new AlertDialog.Builder(this);
	        	alert.setTitle("ACCEPT TICKET");
	        	
	        	// {summary, keywords, status, resolution,type,version,milestone,reporter,priority,component,owner,origine, modification,description};
	        	if ((data[2].equals("accepted")) &&  (data[10]).equals(login.getUser())){
	        		alert.setMessage("You have already accepted this ticket!");	
	        		alert.setPositiveButton("Ok", null);
	  
	        	}
	        		
	        	 else{
					try {
						ticket.acceptTicket();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
	        	alert.setMessage("The Ticket has been accepted");
	        	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int whichButton) {
	        		Intent intent = new Intent(TicketActivityView.this, TicketActivity.class);
					intent.putExtra("Title", "ACTIVE TICKETS IN YOUR TRAC");
					intent.putExtra("String", "status!=closed");
				    startActivity(intent);
	        	  }
	        	});}

	        

	        	alert.show();
	        	
	            return true;
	            
	       /* case R.id.item2:
	        	 
		        	   Intent intent = new Intent(getApplicationContext(), ListWikiActivity.class);
					
					  startActivity(intent);
		        	  
	            return true;
	            
	        
	      
	        
	        
	        case R.id.item3:
	            SharedPreferences sharedPref= getSharedPreferences("mypref", 0);
	            SharedPreferences.Editor editor = sharedPref.edit();
	            editor.remove("user");
	            editor.remove("pass");
	            editor.remove("server");
	            editor.clear();
	            editor.commit();
	            
	            Login login = new Login();
	            login.logout();
	            
	            Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
			
			  startActivity(intent2);
	        
	        
	        
	        */
	        
	        }
	        
	        return false;
	    }
	
	
	
	
	
	
	
	

}
