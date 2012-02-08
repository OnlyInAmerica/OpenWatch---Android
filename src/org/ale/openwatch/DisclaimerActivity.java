package org.ale.openwatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DisclaimerActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.disclaimer);
		Button yesButton = (Button) findViewById(R.id.yessirb);
		Button noButton = (Button) findViewById(R.id.nob);
		Button rtnButton = (Button) findViewById(R.id.returnb);
		
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
		
		rtnButton.setOnClickListener(new OnClickListener(){
			@Override
				public void onClick(View arg0) {
					decide(false);
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
