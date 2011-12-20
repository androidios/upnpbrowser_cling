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
import android.os.Bundle;
import android.widget.ArrayAdapter;



public class BrowseDeviceActivity extends ListActivity 
{   
	private Device device;
	
	private AndroidUpnpService upnpService;
	
	private Service service;
	
	ArrayAdapter<String> adapter;
	
    public void onCreate(Bundle savedInstanceState) { 
    	super.onCreate(savedInstanceState);
    	
    	
    	
    	
    	
	  Bundle bundle = getIntent().getExtras();
	  int position = bundle.getInt("device");
	  
	  device = ((UpnpBrowserApp) getApplication()).getDevice(position);
	  
        List<String> items = new ArrayList<String>();
        items.add((String)(device.getDetails().getFriendlyName()));
        items.add((String)(device.getDetails().getSerialNumber()));
        items.add((String)(device.getDetails().getModelDetails().getModelDescription()));
        
//        device.getServices()
        
        service = device.findService(new ServiceId("tt", null));
        
        upnpService.getControlPoint().execute(
        		new Browse(service, "0", BrowseFlag.DIRECT_CHILDREN){

					@Override
					public void received(ActionInvocation arg0, DIDLContent arg1) {
						// TODO Auto-generated method stub
//				        assertEquals(arg1.getItems().size(), 2);
				        Item item1 = arg1.getItems().get(0);
						
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
        
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items); 
               
        this.setListAdapter(adapter); 
    }
    

}






