package org.scauhci.ExamSystem.android.view.exam;

import org.scauhci.ExamSystem.android.R;
import org.scauhci.ExamSystem.android.tool.Flag;
import org.scauhci.ExamSystem.android.view.dashboard.DashboardActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItem;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class ExamListActivity extends FragmentActivity {

	ViewPager examListPager;
	ExamListPagerAdapter examListPagerAdapter;
	static int numberOfFragment = 2;
	static int examListFragmentIndex = 0;
	static int createExamFragmentIndex = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exam_list);

		initPager();
	}

	private void initPager() {
		examListPagerAdapter = new ExamListPagerAdapter(
				getSupportFragmentManager());

		examListPager = (ViewPager) findViewById(R.id.exam_list_pager);
		examListPager.setAdapter(examListPagerAdapter);
	}

	public static class ExamListPagerAdapter extends FragmentPagerAdapter {

		public ExamListPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			ExamListFragment examListFragment = new ExamListFragment();
			CreateExamFragment createExamFragment = new CreateExamFragment();
			if (position == examListFragmentIndex) {
				return examListFragment;
			} else if (position == createExamFragmentIndex) {
				return createExamFragment;
			} else {
				return null;
			}
		}

		@Override
		public int getCount() {
			return numberOfFragment;
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, DashboardActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);

			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
