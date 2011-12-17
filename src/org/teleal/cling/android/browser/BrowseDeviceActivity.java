package org.teleal.cling.android.browser;



import java.util.ArrayList;
import java.util.List;

import org.teleal.cling.android.upnp.UpnpBrowserApp;
import org.teleal.cling.model.meta.Device;



import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;



public class BrowseDeviceActivity extends ListActivity 
{   
	private Device device;
	
	ArrayAdapter<String> adapter;
	
    public void onCreate(Bundle savedInstanceState) { 
    	super.onCreate(savedInstanceState);
    	
    	
    	
	  Bundle bundle = getIntent().getExtras();
	  int position = bundle.getInt("device");
	  
	  device = ((UpnpBrowserApp) getApplication()).getDevice(position);
	  
        List<String> items = fillArray();
        items.add((String)(device.getDetails().getFriendlyName()));
        
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items); 
               
        this.setListAdapter(adapter); 
    }
    
    private List<String> fillArray() 
    { 
        List<String> items = new ArrayList<String>(); 
        items.add("日曜日"); 
        items.add("月曜日"); 
        items.add("火曜日"); 
        items.add("水曜日"); 
        items.add("木曜日"); 
        items.add("金曜日"); 
        items.add("土曜日"); 
        return items; 
    } 
}