package com.younes.sellme;

import com.parse.Parse;

import android.app.Application;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		
		super.onCreate();
		
		 Parse.initialize(this, "sdLw8ithvtoTWKbb14Wgut3v8G6FoZdMni3G2BRq", "DPBR3yloUtLq8h4R075rhwVr6gre34bUCtUXBSG1");
	}
	
	

}
