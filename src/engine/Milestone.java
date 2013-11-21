package engine;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.jsonrpc.JSONRPCException;
import org.jsonrpc.JSONRPCHttpClient;




public class Milestone {
String id;
Vector<Ticket> tickets;


public Milestone(String id){
	this.id = id;
}


public void getTicket() throws JSONRPCException, JSONException, InterruptedException{
Login login = new Login();
tickets = login.getTrac().getActiveTickets("milestone="+id);
	
}






}


	
	
