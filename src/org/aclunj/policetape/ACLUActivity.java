package org.aclunj.policetape;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ACLUActivity extends Activity{
	
	 public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			setContentView(R.layout.about);
			
			Button donateButton = (Button) findViewById(R.id.donateb);
			
			donateButton.setOnClickListener(new OnClickListener(){
			@Override
				public void onClick(View arg0) {
					Uri uri = Uri.parse( "https://www.aclu.org/secure/support-aclu-new-jersey" );
					startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
				}
			});

	 }
}
