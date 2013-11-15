package com.example.sesp;
 
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
 
import java.util.ArrayList;
import java.util.Vector;
 
/**
 * Created with IntelliJ IDEA.
 * User: Shahab
 * Date: 8/22/12
 * Time: 11:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class CustomAdapter extends BaseAdapter {
 
    private static final String TAG = CustomAdapter.class.getSimpleName();

    Vector<Ticket> tickets;
    
    
    public CustomAdapter(Vector<Ticket> tickets) {
     this.tickets = tickets;
    }
 
    @Override
    public int getCount() {
        return tickets.size();    // total number of elements in the list
    }
 
    @Override
    public Object getItem(int i) {
        return tickets.get(i);    // single item in the list
    }
 
    @Override
    public long getItemId(int i) {
        return i;                   // index number
    }
 
    @Override
    public View getView(int index, View view, final ViewGroup parent) {
 
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.single_list_item, parent, false);
        }
 
         Ticket dataModel = tickets.get(index);
        
        String data[] = dataModel.getData();
        //{summary, keywords, status, resolution,type,version,milestone,reporter};
       
        TextView id = (TextView) view.findViewById(R.id.ticketID);
        id.setText("TICKET #" + dataModel.getStringID());
 
        TextView des = (TextView) view.findViewById(R.id.ticketDes);
        des.setText(" "+data[0]);
 
        TextView status = (TextView) view.findViewById(R.id.status);
        status.setText(" " +data[2]);
        
        TextView milestone = (TextView) view.findViewById(R.id.mileston);
        milestone.setText(" " +data[6]);
 
        // button click listener
        // this chunk of code will run, if user click the button
        // because, we set the click listener on the button only
 
      
 
 
        // if you commented out the above chunk of code and
        // activate this chunk of code
        // then if user click on any view inside the list view (except button)
        // this chunk of code will execute
        // because we set the click listener on the whole view
 
 
       
        
 
        return view;
    }
}