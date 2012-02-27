package org.scauhci.ExamSystem.android.view.paper;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class PaperPager extends ViewPager {

	private boolean pagingEnable = false;

	public PaperPager(Context context) {
		super(context);
	}

	public PaperPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (isPagingEnable()) {
			return super.onInterceptTouchEvent(ev);
		} else {
			return false;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (isPagingEnable()) {
			return super.onTouchEvent(ev);
		} else {
			return false;
		}
	}

	public boolean isPagingEnable() {
		return pagingEnable;
	}

	public void setPagingEnable(boolean pagingEnable) {
		this.pagingEnable = pagingEnable;
	}
}
