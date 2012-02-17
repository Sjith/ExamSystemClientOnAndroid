package org.scauhci.ExamSystem.android.view.dashboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.R;
import org.scauhci.ExamSystem.android.analyzer.PaperXmlAnalyzer;
import org.scauhci.ExamSystem.android.tool.Flag;
import org.scauhci.ExamSystem.android.tool.GetThing;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.support.v4.view.MenuItem.OnMenuItemClickListener;
import android.util.Log;
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

		initSlidingDrawer();
		initNoticeListView();
		
		/*Test*/
		GetThing.setActivity(this);
		/*try {
			File xmlFile = new File("/sdcard", "paper_utf8.xml");
			FileInputStream xmlInputStream = new FileInputStream(xmlFile);
			PaperXmlAnalyzer paperXmlAnalyzer = new PaperXmlAnalyzer();
			paperXmlAnalyzer.analysisXml(xmlInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	private void initSlidingDrawer() {
		ViewFlipper slidingDrawerSubtitleViewFlipper = (ViewFlipper) findViewById(R.id.dashboard_sliding_drawer_subtitle_view_flipper);
		TextView slidingDrawerSubtitle = (TextView) getLayoutInflater()
				.inflate(R.layout.dashboard_sliding_drawer_subtitle, null);

		/*This method should invoke other methods to get the NoticeTitle list,and this code should be change.*/
		slidingDrawerSubtitle.setText("通知标题测试");
		slidingDrawerSubtitleViewFlipper.addView(slidingDrawerSubtitle);
	}

	private void initNoticeListView() {
		NoticeListView noticeListView = (NoticeListView) findViewById(R.id.dashboard_sliding_drawer_content_list_notice);
		
		/*The code between start and end should be write in a method which can get the noticeListItemDatas.*/
		/*Start*/
		ArrayList<HashMap<String, Object>> noticeListItemDatas = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> noticeListItemData = new HashMap<String, Object>();
		
		noticeListItemData.put("noticeName", "通知标题测试");
		noticeListItemData.put("noticePublicTime", "发布时间：测试");
		noticeListItemData.put("noticeContent", "通知内容测试测试测试通知内容测试测试测试通知内容测试测试测试");
		noticeListItemDatas.add(noticeListItemData);
		/*End*/
		noticeListView.setData(noticeListItemDatas);
	}

	private void addPreferencesMenuItem(Menu menu) {
		MenuItem preferencesMenuItem = menu.add(R.string.menu_item_preferences);

		preferencesMenuItem.setIcon(R.drawable.menu_item_preferences);
		preferencesMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		preferencesMenuItem
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Log.e(Flag.DEBUG,
								"You have clicked the preferencesMenuItem.");
						return false;
					}

				});
	}
}
