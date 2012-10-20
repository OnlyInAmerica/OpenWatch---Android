# OpenWatch-Android
by Rich Jones and David Brodsky

### About
OpenWatch is a network-enabled secret recorder. It records secrets, secretly.
This is the Android version.

It secretly records audio and video then submits it to OpenWatch.net

Visit http://www.openwatch.net for more information


### Building: Eclipse and ADT

#### .aidl compilation
If `gen/recordService.java` and `gen/uploadService.java` are not generated after importing the project :

 + Check the project build path and ensure it contains exactly `/src` and `/libs`
     +  Extraneous paths (present in historical versions of the project) will break the build
     
#### Crittercism and SECRETS.java 

This file contains the [Crittercism](www.crittercism.com) ID used for live error reporting:

	public class SECRETS {
		public static String Crittercism_ID = "...";
	}

If you intent to build the project without Crittercism, remove `Crittercism.init(…)` from MainActivityGroup.onCreate():

	// src/MainActivityGroup.java
	// Crittercism disabled
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Crittercism.init(getApplicationContext(), SECRETS.Crittercism_ID);
        
        ...