package com.example.sesp;

import java.util.List;

import org.json.JSONException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListWikiActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_wiki);
		 ListView listView = (ListView)findViewById(R.id.textList);
		 TracServer server = new TracServer("http://10.7.145.105:8001/myproject","luca92","16071950");
	        final List<String> array;
			try {
				array = server.prova();
				 ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.row, R.id.textViewList, array);
			     listView.setAdapter(arrayAdapter);
			     
			     
			     listView.setOnItemClickListener(new OnItemClickListener() {
			          @Override
					  public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			        	  String wiki = array.get(arg2);
			        	  Intent intent = new Intent(getApplicationContext(), WikiActivity.class);
							intent.putExtra("Wiki", wiki);
							intent.putExtra("id", "luca92");
							intent.putExtra("pass", "16071950");
						  startActivity(intent);
			        	  
			        	  
			        	  
						}
			          });
			     
			     
			     
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	       
	    
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_wiki, menu);
		return true;
	}

}