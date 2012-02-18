package org.ale.openwatch;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.content.Context;
import android.content.res.Resources;

public class KnowYourRightsActivity extends Activity {
	
	Context c;
	TableLayout body;
	String state;
	RelativeLayout car;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.rights);
		c = this;
		state = "default";
		//TextView kyr = (TextView) findViewById(R.id.knowthyrights);
		
		//kyr.setText(Html.fromHtml(getString(R.string.bulletListTest)));
		
		setButtonListeners();

 }
	public void setButtonListeners(){
		car = (RelativeLayout) findViewById(R.id.carLayout);
		body = (TableLayout) findViewById(R.id.contentTable);
		car.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				if(state == "default"){
					int views = body.getChildCount();
					body.removeViews(1, views-(4));
					views = body.getChildCount();
					
					//Set title
					TextView title = (TextView) findViewById(R.id.tvTitle);
					title.setText("If You're Stopped In Your Car");
					
					Resources res = getResources();
					String[] carText = res.getStringArray(R.array.car_rights);
					for(int x=1;x<carText.length+1;x++){
						TableRow view = (TableRow) body.getChildAt(x);
						TextView content = (TextView) view.getChildAt(1);
						int numViews = view.getChildCount();
						content.setText(carText[x-1]);
					}
					
					ScrollView scroller = (ScrollView) findViewById(R.id.scrollView);
					scroller.scrollTo(0, 0);
					state = "car";
				}
				else{
					setContentView(R.layout.rights);
					state = "default";	
					setButtonListeners();
				}
			}
			
		});
	}
}
