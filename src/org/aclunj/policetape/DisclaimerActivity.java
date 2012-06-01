package org.aclunj.policetape;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DisclaimerActivity extends Activity {
	
	private LinearLayout disclaimerTouchLayout;
	private Context c;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.disclaimer);
		Button yesButton = (Button) findViewById(R.id.yessirb);
		Button noButton = (Button) findViewById(R.id.nob);
		Button contactButton = (Button) findViewById(R.id.contactb);
		c = this;
		
		TextView txt = (TextView) findViewById(R.id.PoliceTape);  
        Typeface font = Typeface.createFromAsset(getAssets(), "Roboto-Bold.ttf");  
        txt.setTypeface(font);
		
		disclaimerTouchLayout = (LinearLayout) findViewById(R.id.detailsTouchLayout);
		
		disclaimerTouchLayout.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				//disclaimerTv.setText(R.string.disclaimer);	
				new AlertDialog.Builder(c)
                .setTitle("Are you Ready?")
                .setMessage(getString(R.string.disclaimer))
                .setPositiveButton("Okay!", null)
                .show();
			}
			
		});
		
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
