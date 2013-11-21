package com.example.sesp;

import org.json.JSONException;

import engine.Login;
import engine.Ticket;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TicketActivityView extends Activity {
	private LinearLayout slidingPanel;
	private boolean isExpanded;
	private DisplayMetrics metrics;	
	private RelativeLayout headerPanel;
	private RelativeLayout menuPanel;
	private int panelWidth;
	private ImageView menuViewButton,menuViewButton1;
	Button menu1, menu4, menu5;
	Button menu2,menu3, title ;
	FrameLayout.LayoutParams menuPanelParameters;
	FrameLayout.LayoutParams slidingPanelParameters;
	LinearLayout.LayoutParams headerPanelParameters ;
	LinearLayout.LayoutParams listViewParameters;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ticket_activity_view);
		
		bar();
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
		    final Ticket ticket = new Ticket(id);
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
	        	
	        	
	        	else if ((data[2].equals("closed"))){
	        		alert.setMessage("The ticket is closed!\nReopen it to perform operations!");	
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
	            
	            

	            
	            
	        case R.id.item6:
	        	 
		        	  
	        	AlertDialog.Builder alert2 = new AlertDialog.Builder(this);
	        	alert2.setTitle("DELETE TICKET");
	        	
	        	// {summary, keywords, status, resolution,type,version,milestone,reporter,priority,component,owner,origine, modification,description};
	        	
	        		
	        	
					try {
						ticket.closeTicket();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
	        	alert2.setMessage("Do you really want to delete this Ticket?");
	        	
	        	alert2.setNegativeButton("YES", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int whichButton) {
	        		try {
						ticket.deleteTicket();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		Intent intent = new Intent(TicketActivityView.this, TicketActivity.class);
					intent.putExtra("Title", "ACTIVE TICKETS IN YOUR TRAC");
					intent.putExtra("String", "status!=closed");
				    startActivity(intent);
	        	  }
	        	});
	        	alert2.setPositiveButton("NO", null);
	        

	        	alert2.show();
	            return true;
	            
	            
	            
	            
    case R.id.item2:
	        	
	        	AlertDialog.Builder alert1 = new AlertDialog.Builder(this);
	        	alert1.setTitle("CLOSE TICKET");
	        	
	        	// {summary, keywords, status, resolution,type,version,milestone,reporter,priority,component,owner,origine, modification,description};
	        	if ((data[2].equals("closed"))){
	        		alert1.setMessage("This Ticket has been already closed!");	
	        		alert1.setPositiveButton("Ok", null);
	  
	        	}
	        	
	        
	        		
	        	 else{
					try {
						ticket.closeTicket();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
	        	alert1.setMessage("The Ticket has been closed");
	        	alert1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int whichButton) {
	        		Intent intent = new Intent(TicketActivityView.this, TicketActivity.class);
					intent.putExtra("Title", "ACTIVE TICKETS IN YOUR TRAC");
					intent.putExtra("String", "status!=closed");
				    startActivity(intent);
	        	  }
	        	});}

	        

	        	alert1.show();
	        	
	            return true;
	            
	            
	            
	            
	            
    case R.id.item3:
    	
    	AlertDialog.Builder alert3 = new AlertDialog.Builder(this);
    	alert3.setTitle("REASSIGN TICKET");
    	
    	// {summary, keywords, status, resolution,type,version,milestone,reporter,priority,component,owner,origine, modification,description};
    	if ((data[2].equals("closed"))){
    		alert3.setMessage("This Ticket is closed!\nReopen it to perform operations!");	
    		alert3.setPositiveButton("Ok", null);

    	}
    	
    
    		
    	 else{
			
    	alert3.setMessage("Reassign to");
    	final EditText input = new EditText(this);
    	alert3.setView(input);
    	alert3.setPositiveButton("Cancel", null);
    	alert3.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {
    		try {
				ticket.reassignTicket(input.getText().toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    	
    		Intent intent = new Intent(TicketActivityView.this, TicketActivity.class);
			intent.putExtra("Title", "ACTIVE TICKETS IN YOUR TRAC");
			intent.putExtra("String", "status!=closed");
		    startActivity(intent);
    	  }
    	});}

    

    	alert3.show();
    	
        return true;
        
        
        
        
        

        
 case R.id.item4:
    	
    	AlertDialog.Builder alert4 = new AlertDialog.Builder(this);
    	alert4.setTitle("REOPEN TICKET");
    	
    	// {summary, keywords, status, resolution,type,version,milestone,reporter,priority,component,owner,origine, modification,description};
    	if (! (data[2].equals("closed"))){
    		alert4.setMessage("This ticket is not closed!");	
    		alert4.setPositiveButton("Ok", null);

    	}
    	
    
    		
    	 else{
		try {
			ticket.reopenTicket();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	alert4.setMessage("The Ticket has been reopened!");
    	alert4.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {
    	
    		
    	
    		Intent intent = new Intent(TicketActivityView.this, TicketActivity.class);
			intent.putExtra("Title", "ACTIVE TICKETS IN YOUR TRAC");
			intent.putExtra("String", "status!=closed");
		    startActivity(intent);
    	  }
    	});}

    

    	alert4.show();
    	return true;
    	
    	
    	
 case R.id.item5:
 	
 	AlertDialog.Builder alert5 = new AlertDialog.Builder(this);
 	alert5.setTitle("MODIFY TICKET");
 	
 	// {summary, keywords, status, resolution,type,version,milestone,reporter,priority,component,owner,origine, modification,description};
 	if ((data[2].equals("closed"))){
 		alert5.setMessage("This ticket is closed!\nReopen it to perform operations.");	
 		alert5.setPositiveButton("Ok", null);
 	 	alert5.show();
 	}
 	
 
 		
 	 else{
 		Intent intent = new Intent(TicketActivityView.this, ModifyActivity.class);
			intent.putExtra("id", ticket.getID());
		    startActivity(intent);
 	  }

 



        return true;

        

	        
	        }
	        
	        return false;
	    }
	
	
	 public void bar(){
			metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);
			panelWidth = (int) ((metrics.widthPixels)*0.75);
		
			headerPanel = (RelativeLayout) findViewById(R.id.header);
			headerPanelParameters = (LinearLayout.LayoutParams) headerPanel.getLayoutParams();
			headerPanelParameters.width = metrics.widthPixels;
			headerPanel.setLayoutParams(headerPanelParameters);
			
			menuPanel = (RelativeLayout) findViewById(R.id.menuPanel);
			menuPanelParameters = (FrameLayout.LayoutParams) menuPanel.getLayoutParams();
			menuPanelParameters.width = panelWidth;
			menuPanel.setLayoutParams(menuPanelParameters);
			
			slidingPanel = (LinearLayout) findViewById(R.id.slidingPanel);
			slidingPanelParameters = (FrameLayout.LayoutParams) slidingPanel.getLayoutParams();
			slidingPanelParameters.width = metrics.widthPixels;
			slidingPanel.setLayoutParams(slidingPanelParameters);
			
			
		 

		 	menu1 = (Button) findViewById(R.id.menu_item_1);	
		 	menu2 = (Button) findViewById(R.id.menu_item_2);	
		 	menu3 = (Button) findViewById(R.id.menu_item_3);	
			menu4 = (Button) findViewById(R.id.menu_item_4);	
		 	menu5 = (Button) findViewById(R.id.menu_item_5);
	 
		 	menu1.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(TicketActivityView.this, WikiActivity.class);
					intent.putExtra("Wiki", "WikiStart");
				    startActivity(intent);
					
				}
				

		 	});
		 	
		 	
		 	menu2.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(TicketActivityView.this, TicketActivity.class);
					intent.putExtra("Title", "ACTIVE TICKETS IN YOUR TRAC");
					intent.putExtra("String", "status!=closed");
				    startActivity(intent);
					
					
				}
				

		 	});
		 	
		 	
		 	menu5.setOnClickListener(new OnClickListener(){
		 		@Override
				public void onClick(View arg0) {
		 			Login login = new Login();
		 			login.logout();
					Intent intent = new Intent(TicketActivityView.this, MainActivity.class);
				    startActivity(intent);
					
				}
		 	});
		 	
			 
		
			menuViewButton = (ImageView) findViewById(R.id.menuViewButton);
			
			menuViewButton.setOnClickListener(new OnClickListener() {
			    public void onClick(View v) {
			    	if(!isExpanded){
			    		isExpanded = true;   		    				        		
			        	
			    		//Expand
			    		new ExpandAnimation(slidingPanel, panelWidth,
			    	    Animation.RELATIVE_TO_SELF, 0.0f,
			    	    Animation.RELATIVE_TO_SELF, 0.75f, 0, 0.0f, 0, 0.0f);		    			         	    
			    	}else{
			    		isExpanded = false;
			    		
			    		//Collapse
			    		new CollapseAnimation(slidingPanel,panelWidth,
	            	    TranslateAnimation.RELATIVE_TO_SELF, 0.75f,
	            	    TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 0, 0.0f, 0, 0.0f);
			   
						
			    	}         	   
			    }
			});
			
			menuViewButton1 = (ImageView) findViewById(R.id.imageViewButton);
			
			menuViewButton1.setOnClickListener(new OnClickListener() {
			    public void onClick(View v) {
			    	openOptionsMenu();
			   
						
			    	}         	   
			    }
			);
	
	
	
	
	

}}
