package org.scauhci.ExamSystem.android.view.paper;

import java.util.ArrayList;
import java.util.HashMap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

public class PaperFragmentAdapter extends FragmentPagerAdapter {

	int nubmerOfQuestion = -1;
	ArrayList<HashMap<String, Object>> questionDatas;
	HashMap<String, Object> examData;

	public PaperFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {

		if (position == 0) {
			return PaperStartFragment.newInstance(examData);
		} else if (position == (this.getCount() -1)) {
			return PaperEndFragment.newInstance();
		} else {
			return PaperFragment.newInstance(questionDatas.get(position - 1));
		}
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
	}

	@Override
	public int getCount() {
		/* Test */
		// return nubmerOfQuestion;
		return nubmerOfQuestion + 2;
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
	
	public void setExamData(HashMap<String, Object> examData) {
		this.examData = examData;
	}
}
