package org.scauhci.ExamSystem.android.view.paper;

import java.util.ArrayList;
import java.util.HashMap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PaperFragmentAdapter extends FragmentPagerAdapter {

	int nubmerOfQuestion = 6;
	ArrayList<HashMap<String, Object>> questionDatas;

	public PaperFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {

		/* Test */
		return PaperFragment.newInstance(questionDatas.get(position));
		/*
		 * if (position == 0) { return null; } else if (position ==
		 * nubmerOfQuestion) { return null; } else { return
		 * PaperFragment.newInstance(questionDatas.get(position - 1)); }
		 */

	}

	@Override
	public int getCount() {
		/*Test*/
		return nubmerOfQuestion;
		// return nubmerOfQuestion + 2;
	}

	public void setCount(int numberOfQuestion) {
		this.nubmerOfQuestion = numberOfQuestion;
		notifyDataSetChanged();
	}

	public void setQuestionDatas(
			ArrayList<HashMap<String, Object>> questionDatas) {
		this.questionDatas = questionDatas;
		notifyDataSetChanged();
	}
}
