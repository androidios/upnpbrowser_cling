package org.teleal.cling.android.browser;



import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;



public class BrowseDeviceActivity extends ListActivity 
{   
	
	ArrayAdapter<String> adapter;
	
    public void onCreate(Bundle savedInstanceState) { 
    	super.onCreate(savedInstanceState);
    	
        List<String> items = fillArray();        
        
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