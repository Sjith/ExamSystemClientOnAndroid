package org.scauhci.ExamSystem.android.view.dashboard;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class NoticeSlidingDrawer extends SlidingDrawer {

	ArrayList<HashMap<String, Object>> noticeListItemDatas = new ArrayList<HashMap<String, Object>>();
	NoticeListView noticeListView;
	ViewFlipper slidingDrawerSubtitleViewFlipper;

	public NoticeSlidingDrawer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoticeSlidingDrawer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onFinishInflate() {		
		super.onFinishInflate();
		initSlidingDrawer();
		initNoticeListView();
	}

	/** Initialize the slidingDrawerSubtitleViewFlipper */
	private void initSlidingDrawer() {
		slidingDrawerSubtitleViewFlipper = (ViewFlipper) findViewById(R.id.sliding_drawer_notice_subtitle_view_flipper);
		TextView slidingDrawerSubtitle = (TextView) LayoutInflater.from(
				getContext()).inflate(R.layout.sliding_drawer_notice_subtitle,
				null);

		// TODO This method should invoke other methods to get the NoticeTitle list,and this code should be change.
		slidingDrawerSubtitle.setText("通知标题测试");
		slidingDrawerSubtitleViewFlipper.addView(slidingDrawerSubtitle);
	}

	private void initNoticeListView() {
		noticeListView = (NoticeListView) findViewById(R.id.sliding_drawer_notice_content_list_notice);

		
		// TODO The code between start and end should be write in a method which can get the noticeListItemDatas.
		/* Start */
		HashMap<String, Object> noticeListItemData = new HashMap<String, Object>();

		noticeListItemData.put("noticeName", "通知标题测试");
		noticeListItemData.put("noticePublicTime", "发布时间：测试");
		noticeListItemData.put("noticeContent",
				"通知内容测试测试测试通知内容测试测试测试通知内容测试测试测试");
		noticeListItemDatas.add(noticeListItemData);
		/* End */
		noticeListView.setData(noticeListItemDatas);
	}
}
