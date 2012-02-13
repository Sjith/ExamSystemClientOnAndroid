package org.scauhci.ExamSystem.android.view.paper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PaperFragmentAdapter extends FragmentPagerAdapter {
	
	int nubmerOfQuestion = 6;

	public PaperFragmentAdapter(FragmentManager fm) {
		super(fm);
	}
	
	@Override
	public Fragment getItem(int position) {
		return PaperFragment.newInstance();
	}

	@Override
	public int getCount() {
		return nubmerOfQuestion;
	}

	public void setCount(int numberOfQuestion) {
		this.nubmerOfQuestion = numberOfQuestion;
		notifyDataSetChanged();
	}
}
