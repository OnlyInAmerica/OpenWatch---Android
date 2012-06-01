package org.aclunj.policetape;

import org.aclunj.policetape.MyLocation.LocationResult;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DescribeActivity extends Activity{
	
    Context c;
	TextView lead;
	EditText title;
	EditText pub_desc;
	EditText priv_desc;
	Button b;
	ProgressBar p;
	TextView loading;
	TextView locationText;
	Button locationSwitch; 
	boolean switchPressed = false;
	boolean switchOn = true;		
	Location loc;
	double lat;
	double lon;
	boolean hasLoc;
	private static final int DISCLAIMER_CODE = 0;
	
    uploadService u_service;
    private boolean u_servicedBind = false;
	
    private ServiceConnection u_connection = new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder service) {
            u_service = uploadService.Stub.asInterface(service);
            System.out.println("onServiceConnected");
            }

        public void onServiceDisconnected(ComponentName name) {
            System.out.println("onServiceDisConnected");
            u_service = null;
            }
    };
	
	public void onCreate(Bundle icicle) { 
          super.onCreate(icicle); 
          c = this;
          //no title bar
          requestWindowFeature(Window.FEATURE_NO_TITLE);
          getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
          setContentView(R.layout.profile);
          
          lead = (TextView) findViewById(R.id.carrier);
          loading = (TextView) findViewById(R.id.loadingtext);
          title = (EditText) findViewById(R.id.title);
          pub_desc = (EditText) findViewById(R.id.pub_desc);
          priv_desc = (EditText) findViewById(R.id.priv_desc);
          b = (Button) findViewById(R.id.thebutton);
          p = (ProgressBar) findViewById(R.id.progressbar);
          locationSwitch = (Button) findViewById(R.id.locationButton);
          locationText = (TextView) findViewById(R.id.location_text);
          
          TextView txt = (TextView) findViewById(R.id.PoliceTape);  
          Typeface font = Typeface.createFromAsset(getAssets(), "Roboto-Bold.ttf");  
          txt.setTypeface(font);
          
	      lead.setText(getString(R.string.please_describe));
	      
	      /*
	       * alert2.setPositiveButton(getString(R.string.yes_upload), new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int whichButton) {
                                       mHandler.post(new Runnable() {

                                           public void run() {
                                               Intent mainIntent = new Intent(c, DescribeActivity.class); 
                                               startActivity(mainIntent);
                                           }});

                                       finish();
                               }});
	       */
          
          b.setOnClickListener(new OnClickListener(){
        	  //redirect to aclu eula
			public void onClick(View v) {
				if(title.getText().toString().equals("") || pub_desc.getText().toString().equals("")){
					new AlertDialog.Builder(c)
	                .setTitle("Please Provide More Information")
	                .setMessage("A title and public description must be provided before uploading.")
	                .setPositiveButton("Okay!", null)
	                .show();
					return;
				}
				Intent i = new Intent(c, DisclaimerActivity.class);
        		startActivityForResult(i, DISCLAIMER_CODE);

				/*if(title.getText().toString().equals("") || pub_desc.getText().toString().equals("")){
					return;
				}
				
				b.setPressed(true);
				b.setEnabled(false);
				p.setVisibility(View.VISIBLE);
				loading.setVisibility(View.VISIBLE);
				loading.setText("Sending..");
				
		        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		        
		        final SharedPreferences.Editor editor = prefs.edit();
		        editor.putString("pub_desc", pub_desc.getText().toString());
		        editor.putString("priv_desc", priv_desc.getText().toString());
	            editor.putString("title", title.getText().toString());
	            
	            if(hasLoc && switchOn) {
	                editor.putString("location", lat + ", " + lon);
	            }
	            else{
	                editor.putString("location", "");
	            }
	            
		        editor.commit();
				
				Handler mHandler = new Handler();
				mHandler.postDelayed(new Runnable(){

					public void run() {
						
					    try {
                            u_service.start();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
					    
				
					}}, 200);
				finish();*/
			}});
          
          locationSwitch.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() != MotionEvent.ACTION_DOWN) {
                    return false;
                }
                
                if(switchOn) {
                    locationSwitch.setBackgroundResource(R.drawable.switch_right_unpressed_small);
                    switchOn = false;
                    locationText.setText(getString(R.string.location_off));
                }
                else {
                    locationSwitch.setBackgroundResource(R.drawable.switch_left_unpressed_small);
                    switchOn = true;
                    locationText.setText(getString(R.string.location_on));
                }
                
                
                return false;
            }});
          
          MyLocation myLocation = new MyLocation();
          LocationResult locationResult = new LocationResult(){
              @Override
              public void gotLocation(final Location location){
                  //Got the location!
                  
                  loc = location;
                  if (location != null) {
                      lat = location.getLatitude();
                      lon = location.getLongitude();
                  }
                  hasLoc = true;
                  };
              };
          myLocation.getLocation(this, locationResult);
          
          startService(new Intent(this, uService.class));
          bindUploadService();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == DISCLAIMER_CODE) {
			if (data.hasExtra("agreed") && data.getBooleanExtra("agreed", false)){
				b.setPressed(true);
				b.setEnabled(false);
				p.setVisibility(View.VISIBLE);
				loading.setVisibility(View.VISIBLE);
				loading.setText("Sending..");
				
		        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		        
		        final SharedPreferences.Editor editor = prefs.edit();
		        editor.putString("pub_desc", pub_desc.getText().toString());
		        editor.putString("priv_desc", priv_desc.getText().toString());
	            editor.putString("title", title.getText().toString());
	            
	            if(hasLoc && switchOn) {
	                editor.putString("location", lat + ", " + lon);
	            }
	            else{
	                editor.putString("location", "");
	            }
	            
		        editor.commit();
				
				Handler mHandler = new Handler();
				mHandler.postDelayed(new Runnable(){

					public void run() {
						
					    try {
                            u_service.start();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
					    
				
					}}, 200);
				finish();
				
			}
		}
	}
	
    private void bindUploadService(){
        u_servicedBind = bindService(new Intent(this, uService.class), 
                u_connection, Context.BIND_AUTO_CREATE);
    }
	
}
