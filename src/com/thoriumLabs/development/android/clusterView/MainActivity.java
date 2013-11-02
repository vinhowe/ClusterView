package com.thoriumLabs.development.android.clusterView;

/* Copyright (C) 2012 The Android Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

import com.actionbarsherlock.app.SherlockActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
//import com.actionbarsherlock.view.Menu;
//import com.actionbarsherlock.view.MenuItem;
//import com.actionbarsherlock.view.MenuInflater;


import com.thoriumLabs.development.android.circlecluster.R;

public class MainActivity extends SherlockActivity {
   
	 @Override
	 public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		 com.actionbarsherlock.view.MenuInflater inflate=getSupportMenuInflater();
	    inflate.inflate(R.menu.main_menu, menu);
	    return true;
	}
	/**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       Resources res = getResources();

       setContentView(R.layout.activity_main);
       // final ClusterView cluster = (ClusterView) this.findViewById(R.id.Cluster);
       // cluster.addItem("Agamemnon", 3, res.getColor(R.color.seafoam));
       // cluster.addItem("Bocephus", 3.5f, res.getColor(R.color.chartreuse));
       // cluster.addItem("Calliope", 2.5f, res.getColor(R.color.emerald));
       // cluster.addItem("Daedalus", 3, res.getColor(R.color.bluegrass));
        //cluster.addItem("Euripides", 1, res.getColor(R.color.turquoise));
        //cluster.addItem("Ganymede", 3, res.getColor(R.color.slate));

        //((Button) findViewById(R.id.Reset)).setOnClickListener(new View.OnClickListener() {
            //public void onClick(View view) {
               // cluster.setCurrentItem(0);
            //}
        //});
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.action_settings:
            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
            return true;
        case R.id.action_about:
            Intent about = new Intent(this, AboutDialog.class);
            startActivity(about);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}

