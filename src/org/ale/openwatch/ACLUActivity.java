package org.ale.openwatch;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ACLUActivity extends Activity{
	
	 public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.about);
			Button donateButton = (Button) findViewById(R.id.donateb);
			
			donateButton.setOnClickListener(new OnClickListener(){
			@Override
				public void onClick(View arg0) {
					Uri uri = Uri.parse( "http://www.aclu-nj.org/donate" );
					startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
				}
			});

	 }
}
