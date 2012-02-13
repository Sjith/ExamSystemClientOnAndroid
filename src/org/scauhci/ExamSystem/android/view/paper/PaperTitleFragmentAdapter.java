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
		String inflaterTitle = "第" + position + "题";
		return inflaterTitle;
	}
}
