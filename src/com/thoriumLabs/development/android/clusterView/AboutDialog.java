package com.thoriumLabs.development.android.clusterView;

import com.actionbarsherlock.app.SherlockActivity;
import com.thoriumLabs.development.android.circlecluster.R;
import com.thoriumLabs.development.android.circlecluster.R.layout;
import com.thoriumLabs.development.android.circlecluster.R.menu;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.support.v4.app.NavUtils;

public class AboutDialog extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= 14){
		DisplayMetrics metrics = this.getResources().getDisplayMetrics();
		int width = metrics.widthPixels;
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
	    WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	    
	    LayoutParams params = getWindow().getAttributes(); 
	    params.height = metrics.heightPixels-(metrics.heightPixels/10); //fixed height
	    params.width = 950; //fixed width
	    params.alpha = 1;
	    params.dimAmount = 0.5f;
	    getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
		}
		setupActionBar();
		setContentView(R.layout.activity_about_dialog);
		// Show the Up button in the action bar.
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	@SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
    if (Build.VERSION.SDK_INT > 11){
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setSubtitle("About");
    }else{
		//getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("ClusterView");
		getSupportActionBar().setSubtitle("About");
    }

	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about_dialog, menu);
		return true;
	}
@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
	NavUtils.navigateUpFromSameTask(this);
	return true;
		}
	return super.onOptionsItemSelected(item);
	}*/

}
