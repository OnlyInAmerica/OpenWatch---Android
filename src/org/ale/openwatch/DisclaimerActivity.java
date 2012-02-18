package org.ale.openwatch;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DisclaimerActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.disclaimer);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		Button yesButton = (Button) findViewById(R.id.yessirb);
		Button noButton = (Button) findViewById(R.id.nob);
		Button contactButton = (Button) findViewById(R.id.contactb);
		
		yesButton.setOnClickListener(new OnClickListener(){
		@Override
			public void onClick(View arg0) {
				decide(true);
			}
		});
		
		noButton.setOnClickListener(new OnClickListener(){
			@Override
				public void onClick(View arg0) {
					decide(false);
				}
			});
		
		contactButton.setOnClickListener(new OnClickListener(){
			@Override
				public void onClick(View arg0) {
						Uri uri = Uri.parse( "http://www.aclu-nj.org/complaint" );
						startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
				}
			});
		
	}
	
	private void decide(boolean decision){
		Intent data = new Intent();
		data.putExtra("agreed", decision);
		setResult(RESULT_OK, data);
		finish();
	}
}
