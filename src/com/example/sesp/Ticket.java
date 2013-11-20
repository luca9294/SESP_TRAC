package com.example.sesp;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class Ticket {
	private int id;
	private String summary, keywords, status, resolution,type,version,milestone,reporter;
	private String component, priority, description, owner;
	private String origine, modification;
	
	

public Ticket (int id){	
	
	this.id = id;
	
}



public void retrieveData() throws JSONException, InterruptedException{
	Login login = new Login();
	
	TracServer server = login.getTrac();
	
	JSONObject object = server.getTicketJohson(id);
	
	origine=object.getJSONObject("time").getJSONArray("__jsonclass__").getString(1);
    origine = origine.replace("T", " ");
	
    modification=object.getJSONObject("changetime").getJSONArray("__jsonclass__").getString(1);
    modification = modification.replace("T", " ");
	
	
	summary = object.getString("summary");
	keywords = object.getString("keywords");
	status = object.getString("status");
	resolution = object.getString("resolution");
	type = object.getString("type");
	version = object.getString("version");
	milestone = object.getString("milestone");
	reporter = object.getString("reporter");
	component = object.getString("component");
	priority = object.getString("priority");
	description = object.getString("description");
	owner = object.getString("owner");
	
}


public String[] getData(){
	String[] result = {summary, keywords, status, resolution,type,version,milestone,reporter,priority,component,owner,origine, modification,description};
	return result;
	
}

public boolean isActive(){
	if (status!="closed")
		return true;
	
	
	else 
		return false;
	
	
	
	
}


public String getOwner(){
	 return owner;
}



public String getStringID(){
	return String.valueOf(id);
	
}

public int getID(){
	return id;
	
}


public void acceptTicket() throws JSONException, InterruptedException{
	//The owner will be changed from (none) to luca92. Next status will be 'accepted'.
	Login login = new Login();
	TracServer server = login.getTrac();
	JSONObject object = new JSONObject();
	object.put("owner", login.getUser());
	object.put("status", "accepted");
	owner = login.getUser();
	status = "accepted";
	server.updateTicket(id, object);

}

public void closeTicket() throws JSONException, InterruptedException{
	//The owner will be changed from (none) to luca92. Next status will be 'accepted'.
	Login login = new Login();
	TracServer server = login.getTrac();
	JSONObject object = new JSONObject();
	object.put("resolution", "fixed");
	object.put("status", "closed");
	resolution = "fixed";
	status = "closed";
	server.updateTicket(id, object);

}


public void deleteTicket() throws JSONException, InterruptedException{
	//The owner will be changed from (none) to luca92. Next status will be 'accepted'.
	Login login = new Login();
	TracServer server = login.getTrac();
	server.deleteTicket(id);

}
		
		


		














}