package com.example.sesp;

import java.util.Vector;

import org.json.JSONException;
import org.jsonrpc.JSONRPCException;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class TicketActivity extends Activity {
	private LinearLayout slidingPanel;
	private boolean isExpanded;
	private DisplayMetrics metrics;	
	private RelativeLayout headerPanel;
	private RelativeLayout menuPanel;
	private int panelWidth;
	private ImageView menuViewButton;
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
		setContentView(R.layout.activity_ticket);
		bar();
		
		
		Bundle bundle = getIntent().getExtras();
	    String title = bundle.getString("Title");
	    final String string = bundle.getString("String");
	    TextView title1 = (TextView) findViewById(R.id.title);
	    title1.setText(title);
		Login login = new Login();
	
		final TracServer server = login.getTrac();
		ListView listView = (ListView) findViewById(R.id.sample);
		CustomAdapter customAdapter;
		try {
			customAdapter = new CustomAdapter(server.getActiveTickets(string));
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
	
		listView.setOnItemClickListener(new OnItemClickListener() {
	          @Override
			  public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	        	  Intent intent = new Intent(getApplicationContext(), TicketActivityView.class);
	        	  Vector<Ticket> vector;
				try {
					vector = server.getActiveTickets(string);
					intent.putExtra("id", vector.get(arg2).getID());
				    startActivity(intent);
				} catch (JSONRPCException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	  
	        	  
	        	  
	        	  
	        	  
				}
	          });
	
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ticket, menu);
		return true;
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
				Intent intent = new Intent(TicketActivity.this, WikiActivity.class);
				intent.putExtra("Wiki", "WikiStart");
			    startActivity(intent);
				
			}
			

	 	});
	 	
	 	
	 	menu2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(TicketActivity.this, TicketActivity.class);
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
				Intent intent = new Intent(TicketActivity.this, MainActivity.class);
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
