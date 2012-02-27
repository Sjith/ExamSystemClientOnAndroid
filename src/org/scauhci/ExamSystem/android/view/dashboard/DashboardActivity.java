package org.scauhci.ExamSystem.android.view.dashboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.R;
import org.scauhci.ExamSystem.android.analyzer.PaperXmlAnalyzer;
import org.scauhci.ExamSystem.android.tool.Flag;
import org.scauhci.ExamSystem.android.tool.GetThing;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.support.v4.view.MenuItem.OnMenuItemClickListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class DashboardActivity extends FragmentActivity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		addPreferencesMenuItem(menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);

		/*Test*/
		//Start
		initDatabase();
		//End
	}

	private void addPreferencesMenuItem(Menu menu) {
		MenuItem preferencesMenuItem = menu.add(R.string.menu_item_preferences);

		preferencesMenuItem.setIcon(R.drawable.menu_item_preferences);
		preferencesMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		preferencesMenuItem
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						// TODO Write the code to start PreferencesActivity.
						return false;
					}

				});
	}

	private void initDatabase() {

		/* Test */
		GetThing.setActivity(this);

		File database = new File("/data/data/org.scauhci.ExamSystem.android/databases",
				"exam_online.db");
		if (!database.exists()) {
			try {
				InputStream xmlInputStream = getAssets().open("paper_utf8.xml");
				PaperXmlAnalyzer paperXmlAnalyzer = new PaperXmlAnalyzer();
				paperXmlAnalyzer.analysisXml(xmlInputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
