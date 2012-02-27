package org.scauhci.ExamSystem.android.view.paper;

import org.scauhci.ExamSystem.android.tool.Flag;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.viewpagerindicator.TitleProvider;

public class PaperTitleFragmentAdapter extends PaperFragmentAdapter implements TitleProvider {

	public PaperTitleFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public String getTitle(int position) {
		String inflaterTitle;
		if (position == 0) {
			inflaterTitle = "开始考试";
		} else if (position == (this.getCount() -1)) {
			inflaterTitle = "结束考试";
		} else {
			inflaterTitle = "第" + position + "题";
		}
		return inflaterTitle;
	}
}
