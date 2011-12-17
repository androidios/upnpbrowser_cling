package org.teleal.cling.android.upnp;

import java.util.ArrayList;

import org.teleal.cling.model.meta.Device;



import android.app.Application;




public class UpnpBrowserApp extends Application{
	
	private ArrayList<Device> devicelist = new ArrayList();
	
	
	public Device getDevice(int position) {
		  return devicelist.get(position);
		}
	

	
	public void addDevice(Device d){
		devicelist.add(d);
	}
	
	public void rmDevice(Device d){
		devicelist.remove(d);
	}
	

}
