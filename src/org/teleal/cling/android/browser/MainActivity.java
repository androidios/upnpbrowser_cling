/*
 * Copyright (C) 2010 Teleal GmbH, Switzerland
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.teleal.cling.android.browser;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import org.teleal.android.util.FixedAndroidHandler;
import org.teleal.common.logging.LoggingUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Christian Bauer
 */
public class MainActivity extends TabActivity {

    private static Logger log = Logger.getLogger(MainActivity.class.getName());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fix the logging integration between java.util.logging and Android internal logging
        LoggingUtil.resetRootHandler(new FixedAndroidHandler());

        Logger.getLogger("org.teleal.cling").setLevel(Level.INFO);

        /* Enable this for debug logging:
        Logger.getLogger("org.teleal.cling.transport.Router").setLevel(Level.FINEST);

        // UDP communication
        Logger.getLogger("org.teleal.cling.transport.spi.DatagramIO").setLevel(Level.FINE);
        Logger.getLogger("org.teleal.cling.transport.spi.MulticastReceiver").setLevel(Level.FINE);

        // Discovery
        Logger.getLogger("org.teleal.cling.protocol.ProtocolFactory").setLevel(Level.FINER);
        Logger.getLogger("org.teleal.cling.protocol.async").setLevel(Level.FINER);

        // Description
        Logger.getLogger("org.teleal.cling.protocol.ProtocolFactory").setLevel(Level.FINER);
        Logger.getLogger("org.teleal.cling.protocol.RetrieveRemoteDescriptors").setLevel(Level.FINE);
        Logger.getLogger("org.teleal.cling.transport.spi.StreamClient").setLevel(Level.FINEST);

        Logger.getLogger("org.teleal.cling.protocol.sync.ReceivingRetrieval").setLevel(Level.FINE);
        Logger.getLogger("org.teleal.cling.binding.xml.DeviceDescriptorBinder").setLevel(Level.FINE);
        Logger.getLogger("org.teleal.cling.binding.xml.ServiceDescriptorBinder").setLevel(Level.FINE);
        Logger.getLogger("org.teleal.cling.transport.spi.SOAPActionProcessor").setLevel(Level.FINEST);

        // Registry
        Logger.getLogger("org.teleal.cling.registry.Registry").setLevel(Level.FINER);
        Logger.getLogger("org.teleal.cling.registry.LocalItems").setLevel(Level.FINER);
        Logger.getLogger("org.teleal.cling.registry.RemoteItems").setLevel(Level.FINER);
        */

        setContentView(R.layout.main);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, BrowseActivity.class);
        spec = tabHost.newTabSpec("browse")
                .setIndicator("Browse LAN", getResources().getDrawable(R.drawable.ic_tab_browse))
                .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, DemoActivity.class);
        spec = tabHost.newTabSpec("demo")
                .setIndicator("Demo Light", getResources().getDrawable(R.drawable.ic_tab_demo))
                .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }

}
