package org.aclunj.policetape;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aclunj.policetape.R;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class RecorderActivity extends Activity {
    /** Called when the activity is first created. */
    
    public static boolean hidden = false;
    final Handler mHandler = new Handler();
    public VideoRecorder vr;
    //private ImageView iv;
    private LinearLayout container;
    private FrameLayout fl;
    private MainActivityGroup mag;
    private Activity mainer;
    Context co;
    
    boolean recording = false;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.playa);
        container = (LinearLayout) findViewById(R.id.camcorder_container);
        
        vr = (VideoRecorder) findViewById(R.id.camcorder_preview);
        //iv = (ImageView) findViewById(R.id.hider);
        co = this;

    }
    
    public void reset() {
        vr.setPath("/recordings/" + getDateString() + "_aclunj.mp4");
        vr.recorder.reset();
    }
    
    public String getPath() {
        return vr.getPath();
    }
    
    public void onResume() {
        super.onResume();
    }
    
    private String getDateString(){
    	Date now = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd_KK.mma");
    	return sdf.format(now);
    }

       
    
    public void start() {
            container.setVisibility(View.VISIBLE);
            //iv.setVisibility(View.VISIBLE);
            hidden = true;
            
            final VideoRecorder vvv = vr;
            vr.setPath("/recordings/" + getDateString() + "_aclunj.mp4");
            mHandler.post(new Runnable() {

                public void run() {
                    try {
                        vvv.start(co);
                        recording = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }});
            
    }
    
    public void stop() {
            
            
            final VideoRecorder vvv = vr;
            mHandler.post(new Runnable() {

                public void run() {
                    try {
                        recording = false;
                        vvv.stop();
                        container.setVisibility(View.GONE);
                        //iv.setVisibility(View.GONE);
                        hidden = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }});
            //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
           
    }
    
    public void setParentGroup(MainActivityGroup magg) {
        mag = magg;
    }
    
    public void setMainActivity(Activity magg) {
        mainer = magg;
        fl = (FrameLayout) mainer.findViewById(R.id.Recorder);
    }
    
    public void setFL(FrameLayout magg) {
        fl = magg;
    }
    
    public boolean isVideoRecording() {
        return recording;
    }
    
}