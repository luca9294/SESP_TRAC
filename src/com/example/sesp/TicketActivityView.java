package com.example.sesp;

import org.json.JSONException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class TicketActivityView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
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

}
