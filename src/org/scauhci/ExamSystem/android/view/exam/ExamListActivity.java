package org.scauhci.ExamSystem.android.view.exam;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.R;
import org.scauhci.ExamSystem.android.module.ExamListModule;
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
import android.view.ViewGroup;

public class ExamListActivity extends FragmentActivity {

	ViewPager examListPager;
	ExamListPagerAdapter examListPagerAdapter;
	ExamListFragment examListFragment;
	CreateExamFragment createExamFragment;
	ExamListModule examListModule;
	ArrayList<HashMap<String, Object>> examListItemDatas;
	final int numberOfFragment = 2;
	final int examListFragmentIndex = 0;
	final int createExamFragmentIndex = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exam_list);

		examListModule = ExamListModule.getInstance();
		examListItemDatas = examListModule.getAllExamListItemData();
		
		initPager();
	}
	
	public ArrayList<HashMap<String, Object>> getAllExamListItemData() {
		return examListItemDatas;
	}

	public ExamListModule getExamListModule() {
		return examListModule;
	}

	public ExamListFragment getExamListFragment() {
		return examListFragment;
	}

	public CreateExamFragment getCreateExamFragment() {
		return createExamFragment;
	}

	/**Initialize the ExamListPager.*/
	private void initPager() {
		examListFragment = new ExamListFragment();
		createExamFragment = new CreateExamFragment();		
		examListPagerAdapter = new ExamListPagerAdapter(
				getSupportFragmentManager());
		examListPagerAdapter.setExamListItemDatas(examListItemDatas);

		examListPager = (ViewPager) findViewById(R.id.exam_list_pager);
		examListPager.setAdapter(examListPagerAdapter);
	}

	public class ExamListPagerAdapter extends FragmentPagerAdapter {
		
		ArrayList<HashMap<String, Object>> examListItemDatas;
		
		public ExamListPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		
		public void setExamListItemDatas(
				ArrayList<HashMap<String, Object>> examListItemDatas) {
			this.examListItemDatas = examListItemDatas;
			getExamListFragment().setExamListItemDatas(examListItemDatas);
			notifyDataSetChanged();
		}

		@Override
		public Fragment getItem(int position) {
			if (position == examListFragmentIndex) {
				return getExamListFragment();
			} else if (position == createExamFragmentIndex) {
				return getCreateExamFragment();
			} else {
				return null;
			}
		}

		@Override
		public int getCount() {
			return numberOfFragment;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
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
	
	/*TODO Complete this method.*/
	public void addExam(){
		
	}
}
