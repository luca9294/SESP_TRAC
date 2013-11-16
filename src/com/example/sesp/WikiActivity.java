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
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
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
import android.widget.Spinner;
import android.widget.TextView;

public class WikiActivity extends Activity {
	
	private TextView status;
	String string;
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
	

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_wiki);
		bar();
		
		Bundle bundle = getIntent().getExtras();
	     String wiki = bundle.getString("Wiki");
	  	
	    title = (Button) findViewById(R.id.menu_title_1);	
		
	     
	     
		
		Login login = new Login();
		TracServer trac = login.getTrac();
		
		String user = login.getUser();
		
		title.setText("Logged as " + user);
		
		
		
	    status = (TextView)findViewById(R.id.spinner);
	   // Log.e("SeI", "SeI");
	    String string;
			//WIKI wiki = new WIKI();
			try {
				
			
				status.setText(Html.fromHtml(trac.getHTMLWIKI(wiki)));
				
				 string =  (String) status.getText();
}
			



	 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//Log.e("NO", "NO");
			}
		
		
	}
		
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wiki, menu);
		
		
		
		return true;
	}
	
	
	
	  @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        case R.id.item1:
	        	AlertDialog.Builder alert = new AlertDialog.Builder(this);

	        	alert.setTitle("WIKI PAGE");
	        	alert.setMessage("Insert the page-name:");

	        	// Set an EditText view to get user input 
	        	final EditText input = new EditText(this);
	        	alert.setView(input);

	        	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int whichButton) {
	        	  String value = input.getText().toString();
	        	  Bundle bundle = getIntent().getExtras();
	        	  Intent intent = new Intent(getApplicationContext(), WikiActivity.class);
				  startActivity(intent);
	        	  }
	        	});

	        	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        	  public void onClick(DialogInterface dialog, int whichButton) {
	        	    // Canceled.
	        	  }
	        	});

	        	alert.show();
	        	
	            return true;
	            
	        case R.id.item2:
	        	 
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
					Intent intent = new Intent(WikiActivity.this, WikiActivity.class);
					intent.putExtra("Wiki", "WikiStart");
				    startActivity(intent);
					
				}
				

		 	});
		 	
		 	
		 	menu2.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(WikiActivity.this, TicketActivity.class);
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
					Intent intent = new Intent(WikiActivity.this, MainActivity.class);
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

		
		
		
	
	
			
	

	

