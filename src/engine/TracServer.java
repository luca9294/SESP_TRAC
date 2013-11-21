package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsonrpc.JSONRPCException;
import org.jsonrpc.JSONRPCHttpClient;




import android.util.Log;

public class TracServer {
String url, user, passw;
String toE;
String html, methods;
boolean login;
JSONArray list;
JSONObject object;
JSONArray array;
boolean logged = false;
	
	
	public TracServer(String url, String user, String passw){
		this.url = url;
		this.user = user;
		this.passw = passw;
		
		
	}
	
	
	
	public String getHTMLWIKI(String name) throws InterruptedException{
		final String string;
		final String name1 = name;

		
		final Thread e = new Thread() {

			@Override
			public void run() {
		
				JSONRPCHttpClient req = new JSONRPCHttpClient(url + "/login/rpc");
				 req.setCredentials(user, passw);
				String ticket;
					try {
						html = req.call("wiki.getPageHTML", name1).toString();
						//html = req.call("ticket.create", "ECLIP", "ECLIP").toString();
						
					} catch (JSONRPCException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}};
			e.start();
			e.join();
			
	return html;
	}
	
	
	
	public JSONArray getLIST() throws InterruptedException, JSONException{

		
		final Thread e = new Thread() {

			@Override
			public void run() {
		
				JSONRPCHttpClient req = new JSONRPCHttpClient(url + "/login/rpc");
				req.setCredentials(user, passw);
				String ticket;
					try {
						list = (JSONArray) req.call("wiki.getAllPages");
					
						
					} catch (JSONRPCException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}};
			e.start();
			e.join();
			
			
		
			
	return list;
	}
	
	
	public Vector<Ticket> getActiveTickets(final String string) throws JSONRPCException, JSONException, InterruptedException{
		
		Vector<Ticket> list = new Vector<Ticket>();
			final Thread e = new Thread() {

				@Override
				public void run() {
			
					JSONRPCHttpClient req = new JSONRPCHttpClient(url + "/login/rpc");
					req.setCredentials(user, passw);
					String ticket;
					
						try {
							//array = (JSONArray) req.call("ticket.query","status!=closed");
							array = (JSONArray) req.call("ticket.query",string);
							
						} catch (JSONRPCException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

				}};
				e.start();
				e.join();
				
				
				
		for (int i = 0; i<array.length(); i++){
			Ticket t = new Ticket (array.getInt(i));
			t.retrieveData();
			list.add(t);
			
			
			
		}	
				

				
				
		return list;
		
				
			}
	
	
	
	
	public String[] getMilestones() throws InterruptedException, JSONException{
		String[] list1;
		final Thread e = new Thread() {
			
			@Override
			public void run() {
		
				JSONRPCHttpClient req = new JSONRPCHttpClient(url + "/login/rpc");
				req.setCredentials(user, passw);
				String ticket;
			
					try {
						//array = (JSONArray) req.call("ticket.query","status!=closed");
						array = (JSONArray) req.call("ticket.milestone.getAll");
						
					} catch (JSONRPCException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}};
			e.start();
			e.join();
			list1=new String[array.length()];
			for (int i = 0; i<array.length(); i++){
			String t  = (array.getString(i));
			list1[i] = t;
			
			}
			
		
		return list1;
		
		
	}
	
	
	
	public void createTicket(final String summary, final String description, final JSONObject datas) throws InterruptedException, JSONException{
		final Thread e = new Thread() {
			
			@Override
			public void run() {
		
				JSONRPCHttpClient req = new JSONRPCHttpClient(url + "/login/rpc");
				req.setCredentials(user, passw);
				String ticket;
			
					try {
						//array = (JSONArray) req.call("ticket.query","status!=closed");
					 req.call("ticket.create",summary, description, datas);
						
					} catch (JSONRPCException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}};
			e.start();
			e.join();
	
		
	}
	
	

	
	
	
	public JSONObject getTicketJohson(int id) throws InterruptedException{
		
		final int p = id;
				
				final Thread e = new Thread() {

					@Override
					public void run() {
				
						JSONRPCHttpClient req = new JSONRPCHttpClient(url + "/login/rpc");
						req.setCredentials(user, passw);
						String ticket;
						
							try {
								JSONArray array = (JSONArray) req.call("ticket.get",p);
								 object =  (JSONObject) array.get(3);

								
							} catch (JSONRPCException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

					}};
					e.start();
					e.join();
					
			return object;
			}
	
	
	

	
	
	
	
	
	public List<String> prova() throws InterruptedException, JSONException{
		JSONArray arr = this.getLIST();
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < arr.length(); i++){
		    list.add(arr.getString(i));
		}
		
		
		
		
		
		
		return list;
		
	}
	
public boolean isValid() throws InterruptedException, JSONException{
		
		List<String> list = this.prova();
		
		if (list.isEmpty())
			return false;
		
		else
			return true;
		
	}
	
	
public String[] getComponent() throws InterruptedException, JSONException{
	String[] list1;
	final Thread e = new Thread() {
		
		@Override
		public void run() {
	
			JSONRPCHttpClient req = new JSONRPCHttpClient(url + "/login/rpc");
			req.setCredentials(user, passw);
			
				try {
					//array = (JSONArray) req.call("ticket.query","status!=closed");
					array = (JSONArray) req.call("ticket.component.getAll");
					
				} catch (JSONRPCException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}};
		e.start();
		e.join();
		list1=new String[array.length()];
		for (int i = 0; i<array.length(); i++){
		String t  = (array.getString(i));
		list1[i] = t;
		
		}
		
	
	return list1;
	
	
}



public void updateTicket(final int id, final JSONObject data) throws InterruptedException, JSONException{
	//String[] list1 = {"ciao"};
	final Thread e = new Thread() {
		
		@Override
		public void run() {
	
			JSONRPCHttpClient req = new JSONRPCHttpClient(url + "/login/rpc");
			req.setCredentials(user, passw);
			
				try {
					//array = (JSONArray) req.call("ticket.query","status!=closed");
					//JSONObject object = new JSONObject();
					//object.put("status", "closed");
					
					req.call("ticket.update",id,"",data,false);
					
				} catch (JSONRPCException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}};
		e.start();
		e.join();
	
}

public void deleteTicket(final int id) throws InterruptedException, JSONException{
	
	final Thread e = new Thread() {
		
		@Override
		public void run() {
	
			JSONRPCHttpClient req = new JSONRPCHttpClient(url + "/login/rpc");
			req.setCredentials(user, passw);
			
				try {
					//array = (JSONArray) req.call("ticket.query","status!=closed");
					//JSONObject object = new JSONObject();
					//object.put("status", "closed");
					
					req.call("ticket.delete",id);
					
				} catch (JSONRPCException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}};
		e.start();
		e.join();
	
}






public String[] getVersion() throws InterruptedException, JSONException{
	String[] list1;
	final Thread e = new Thread() {
		
		@Override
		public void run() {
	
			JSONRPCHttpClient req = new JSONRPCHttpClient(url + "/login/rpc");
			req.setCredentials(user, passw);
			
				try {
					//array = (JSONArray) req.call("ticket.query","status!=closed");
					array = (JSONArray) req.call("ticket.version.getAll");
					
				} catch (JSONRPCException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}};
		e.start();
		e.join();
		list1=new String[array.length()];
		for (int i = 0; i<array.length(); i++){
		String t  = (array.getString(i));
		list1[i] = t;
		
		}
		
	
	return list1;
	
	
}
	
	
	
	
	
	
	
}
	
	
	
	
