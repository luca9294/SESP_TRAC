package engine;

import com.example.sesp.MainActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Login {
	String user, passw, URL;
	TracServer server;
	
	public Login(){}
	
	
	public void createSession(String URL, String user, String passw){
	Context applicationContext = MainActivity.getContextOfApplication();
	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);	
	SharedPreferences.Editor editor = prefs.edit();
	// put your value
	editor.putString("user", user);
	editor.putString("pass", passw);
	editor.putString("server", URL);
    this.user = user;
    this.passw = passw;
    this.URL = URL;
	// commits your edits
	editor.commit();
	}
	
	
	
	public void logout(){
		Context applicationContext = MainActivity.getContextOfApplication();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);	
		SharedPreferences.Editor editor = prefs.edit();
        editor.remove("user");
        editor.remove("pass");
        editor.remove("server");
        editor.clear();
        editor.commit();
		
		
		
	}
	
	public String getUser(){
		Context applicationContext = MainActivity.getContextOfApplication();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);	
		// put your value
		 String userSh = prefs.getString("user", "");
	    return userSh;
	}
	
	public String getPassw(){
		Context applicationContext = MainActivity.getContextOfApplication();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);	
		String passwordSh = prefs.getString("pass", "");
		return passwordSh;
	}
	
	
	public String getURL(){
		Context applicationContext = MainActivity.getContextOfApplication();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
		String serverSh = prefs.getString("server", "");	
		return serverSh;
	}

	
	public String[] getData (){
		Context applicationContext = MainActivity.getContextOfApplication();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);	
		// put your value
		 String userSh = prefs.getString("user", "");
	     String passwordSh = prefs.getString("pass", "");
	     String serverSh = prefs.getString("server", "");	
	
	     String result[] = {userSh,passwordSh,serverSh};
	     
	     return result;
		}
	
	
	
	public TracServer getTrac(){
		Context applicationContext = MainActivity.getContextOfApplication();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);	
		// put your value
			
	
	    server = new TracServer(this.getURL(),this.getUser(),this.getPassw());
	     
	     return server;
		}
	
	
	public boolean isLogged(){
		Context applicationContext = MainActivity.getContextOfApplication();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);	
		// put your value
		 String userSh = prefs.getString("user", "");
	     String passwordSh = prefs.getString("pass", "");
	     String serverSh = prefs.getString("server", "");	
	
	     
	     if (userSh.equals("")){
	    	 
	    	 return false;
	    	 
	     }
	     else{
	     
	     
	     return true;
		
		
	     }
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
