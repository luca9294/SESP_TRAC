package com.example.sesp;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		 SharedPreferences sharedPref= getSharedPreferences("mypref", 0);
	     String userSh = sharedPref.getString("user", "");
	     String passwordSh = sharedPref.getString("pass", "");
	     String serverSh = sharedPref.getString("server", "");		
		
		
		if ((serverSh.length() != 0) && (passwordSh.length() != 0)){
			Intent intent = new Intent(getApplicationContext(), WikiActivity.class);
			intent.putExtra("Wiki", "WikiStart");
		    startActivity(intent);
			
			
			
		}
		
		
		
		
		else{
		
		
		
		
		
		
		final EditText server = (EditText) findViewById(R.id.editText1);
		final EditText user = (EditText) findViewById(R.id.editText2);
		final EditText pass = (EditText) findViewById(R.id.editText3);
	
		Button button1 = (Button) findViewById(R.id.button1);
	
	
	String s;
	button1.setOnClickListener(new View.OnClickListener(){
		

		@Override
		public void onClick(View v) {
			String serverS = server.getText().toString();
			String userS = user.getText().toString();
			String passS = pass.getText().toString();
			
			if (!(serverS.length() > 4)){
				showDialog(4);
				}
			
			
			else if (serverS.equals("") || userS.equals("") || passS.equals("")){
					showDialog(1);
				}
			
			else if (!(serverS.substring(0, 4).equals("http"))){
					showDialog(2);
				
			}

			else if ((serverS.substring(serverS.length()-4, serverS.length()).equals("/rpc")) || (serverS.substring(serverS.length()-4, serverS.length()).equals("/xmlrpc")) ){
				showDialog(3);
		}
			
			else{
				TracServer server_trac = new TracServer(serverS,userS,passS);
				//if (server.validLogin()){
				   SharedPreferences sharedPref= getSharedPreferences("mypref", 0);
				    //now get Editor
				   SharedPreferences.Editor editor= sharedPref.edit();
				    //put your value
				     editor.putString("user", userS);
				     editor.putString("pass", passS);
				     editor.putString("server", serverS);
				     
				   //commits your edits
				     editor.commit();
				  
				
					Intent intent = new Intent(getApplicationContext(), WikiActivity.class);
					intent.putExtra("Wiki", "WikiStart");
				    startActivity(intent);
					
					
					
					
				//}
			//	else 
				//	showDialog(4);
				
			}
				
	
	
		}
			
			
			
			
			
			
		});
		}
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}



protected Dialog onCreateDialog(int id) {
	switch (id) {
	case 1: {
		// Create out AlterDialog
		Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("You haven't inserted the required datas! Please retry");
		builder.setCancelable(true);
		builder.setPositiveButton("OK", null);
		AlertDialog dialog = builder.create();
		dialog.show();
		break;
	}
	
	
	case 2: {
		// Create out AlterDialog
		Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Your address must begin with http://");
		builder.setCancelable(true);
		builder.setPositiveButton("OK", null);
		AlertDialog dialog = builder.create();
		dialog.show();
		break;
	}
	
	case 3: {
		// Create out AlterDialog
		Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("The /rpc part will be add automatically by the system!\nPlease rewrite without the /rpc part");
		builder.setCancelable(true);
		builder.setPositiveButton("OK", null);
		AlertDialog dialog = builder.create();
		dialog.show();
		break;
	}
	
	case 4: {
		// Create out AlterDialog
		Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("The inserted datas aren't correct! Please retry.");
		builder.setCancelable(true);
		builder.setPositiveButton("OK", null);
		AlertDialog dialog = builder.create();
		dialog.show();
		break;
	}
	

	}
	
	return super.onCreateDialog(id);
}







}
