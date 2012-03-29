package org.aclunj.policetape;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EulaActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.eula);
		Button yesButton = (Button) findViewById(R.id.eulaYesB);
		Button noButton = (Button) findViewById(R.id.eulaNoB);
		TextView txt = (TextView) findViewById(R.id.PoliceTape);  
        Typeface font = Typeface.createFromAsset(getAssets(), "Roboto-Bold.ttf");  
        txt.setTypeface(font);
		
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
	}
	
	private void decide(boolean decision){
		Intent data = new Intent();
		data.putExtra("agreed", decision);
		setResult(RESULT_OK, data);
		finish();
	}

}
