package com.example.sesp;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class Ticket {
	private int id;
	private String summary, keywords, status, resolution,type,version,milestone,reporter;
	private String component, priority, description, owner;
	private Date origine, modification;
	
	

public Ticket (int id){	
	
	this.id = id;
	
}



public void retrieveData() throws JSONException, InterruptedException{
	Login login = new Login();
	
	TracServer server = login.getTrac();
	
	JSONObject object = server.getTicketJohson(id);
	
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
	String[] result = {summary, keywords, status, resolution,type,version,milestone,reporter};
	return result;
	
}

public String getStringID(){
	return String.valueOf(id);
	
}

public int getID(){
	return id;
	
}
		














}