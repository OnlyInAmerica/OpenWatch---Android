package org.aclunj.policetape;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.content.Context;
import android.content.res.Resources;

public class KnowYourRightsActivity extends Activity {
	
	Context c;
	TableLayout body;
	ScrollView scrolly;
	TextView title;
	String state;
	RelativeLayout car;
	RelativeLayout home;
	RelativeLayout stop;
	RelativeLayout arrest;
	Resources res;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.rights);
		c = this;
		state = "default";
		body = (TableLayout) findViewById(R.id.contentTable);
		title = (TextView) findViewById(R.id.tvTitle);
		scrolly = (ScrollView) findViewById(R.id.scrollView);
		//TextView kyr = (TextView) findViewById(R.id.knowthyrights);
		
		//kyr.setText(Html.fromHtml(getString(R.string.bulletListTest)));
		
		setButtonListeners();
		res = getResources();
		

 }
	
	public void fillTable(String[] content){
		title.setText(content[0]);
		body.removeAllViews();
		for(int x=1;x<content.length;x++){
			TableRow tr = new TableRow(c);
			TextView bullet = (TextView) View.inflate(c, R.layout.rightstext, null);
			bullet.setWidth(20);
			bullet.setTextSize(20);
			bullet.setText("¥");
			TextView text = (TextView) View.inflate(c, R.layout.rightstext, null);
			text.setText(content[x]);
			text.setSingleLine(false);
			tr.addView(bullet);
			tr.addView(text);
			body.addView(tr);
		}
		scrolly.scrollTo(0, 0);
	}
	
	public void setButtonListeners(){
		car = (RelativeLayout) findViewById(R.id.carLayout);
		home = (RelativeLayout) findViewById(R.id.homeLayout);
		stop = (RelativeLayout) findViewById(R.id.questioningLayout);
		arrest = (RelativeLayout) findViewById(R.id.arrestLayout);
		body = (TableLayout) findViewById(R.id.contentTable);
		
		car.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(state != "car"){
					fillTable(res.getStringArray(R.array.car_rights));
					state = "car";
				}
			}
		});
		home.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(state != "home"){
					fillTable(res.getStringArray(R.array.home_rights));
					state = "home";
				}
			}
		});
		stop.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(state != "stop"){
					fillTable(res.getStringArray(R.array.stop_rights));
					state = "stop";
				}
			}
		});
		arrest.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(state != "arrest"){
					fillTable(res.getStringArray(R.array.arrest_rights));
					state = "arrest";
				}
			}
		});
	}
}
