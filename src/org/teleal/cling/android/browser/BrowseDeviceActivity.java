package org.teleal.cling.android.browser;



import java.util.ArrayList;
import java.util.List;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.android.upnp.UpnpBrowserApp;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.LocalService;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.ServiceId;
import org.teleal.cling.support.contentdirectory.callback.Browse;
import org.teleal.cling.support.model.BrowseFlag;
import org.teleal.cling.support.model.DIDLContent;
import org.teleal.cling.support.model.item.Item;



import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.ArrayAdapter;



public class BrowseDeviceActivity extends ListActivity 
{   
	private Device device;
	
	private AndroidUpnpService upnpService;
	
	private Service service;
	

	
	private Service[] services;
	
	ArrayAdapter<String> adapter;
	
	List<String> items = new ArrayList<String>();
	
    private ServiceConnection serviceConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            upnpService = (AndroidUpnpService) service;
        }

        public void onServiceDisconnected(ComponentName className) {
            upnpService = null;
        }
    };
	
    public void onCreate(Bundle savedInstanceState) { 
    	super.onCreate(savedInstanceState);
    	
//    	services = new ArrayList<Service>();
    	
    	
    	
	  Bundle bundle = getIntent().getExtras();
	  int position = bundle.getInt("device");
	  
	  device = ((UpnpBrowserApp) getApplication()).getDevice(position);
	  
        
        items.add((String)(device.getDetails().getFriendlyName()));
        items.add((String)(device.getDetails().getSerialNumber()));
        
        

        getApplicationContext().bindService(
                new Intent(this, BrowserUpnpService.class),
                serviceConnection,
                Context.BIND_AUTO_CREATE
        );
        
        services = device.getServices();
        
        items.add(services[0].getServiceId().getNamespace());
        
        service = device.findService(new ServiceId("upnp-org", "ContentDirectory"));
        
        if(service == null)
        {
        	items.add("service of the device is null");
        }
        
        if(upnpService == null){
        	items.add("upnpService is null");
        }
        
        if((service != null)&&(upnpService != null)){
        	upnpService.getControlPoint().execute(
            		new Browse(service, "0", BrowseFlag.DIRECT_CHILDREN){

    					@Override
    					public void received(ActionInvocation arg0, DIDLContent arg1) {
    						// TODO Auto-generated method stub
//    				        assertEquals(arg1.getItems().size(), 2);
    				        Item item1 = arg1.getItems().get(0);
    						items.add(item1.toString());
    					}

    					@Override
    					public void updateStatus(Status arg0) {
    						// TODO Auto-generated method stub
    						
    					}

    					@Override
    					public void failure(ActionInvocation arg0,
    							UpnpResponse arg1, String arg2) {
    						// TODO Auto-generated method stub
    						
    					}
            			
            		}
            		);
        	
        }
        
        
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items); 
               
        this.setListAdapter(adapter); 
    }
    

}






