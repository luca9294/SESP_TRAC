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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class WikiActivity extends Activity {
	
	private TextView status;
	String string;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_wiki);
		
		Bundle bundle = getIntent().getExtras();
	     String wiki = bundle.getString("Wiki");
		//JSONRPCHttpClient req = new JSONRPCHttpClient("http://10.7.152.228:8001/myproject/login/rpc");
		//req.setCredentials("luca92", "16071950");
	     SharedPreferences sharedPref= getSharedPreferences("mypref", 0);
	     String user = sharedPref.getString("user", "");
	     String password = sharedPref.getString("pass", "");
	     String server = sharedPref.getString("server", "");
		
	     
	     
		//TracServer trac = new TracServer(server,user, password);
		
		MainActivity main = new MainActivity();
		TracServer trac = new TracServer(server,user, password);
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
					intent.putExtra("Wiki", value);
					intent.putExtra("id", bundle.getString("id"));
					intent.putExtra("pass", bundle.getString("pass"));
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
	            
	            Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
			
			  startActivity(intent2);
	        
	        
	        
	        
	        
	        }
	        
	        return false;
	    }
	}		

		
		
		
	
	
			
	

	

