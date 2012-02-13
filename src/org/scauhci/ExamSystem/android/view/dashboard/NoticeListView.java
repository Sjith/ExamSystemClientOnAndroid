package org.scauhci.ExamSystem.android.view.dashboard;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class NoticeListView extends ListView {

	SimpleAdapter noticeListAdapter;
	AdapterView.OnItemClickListener noticeListItemOnClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View noticeListItemView, int position, long id)
		{
			TextView noticeListItemContent = (TextView) noticeListItemView.findViewById(R.id.list_notice_item_content);
			if (noticeListItemContent.getVisibility() == VISIBLE) {
				noticeListItemContent.setVisibility(GONE);
			} else if (noticeListItemContent.getVisibility() == GONE) {
				noticeListItemContent.setVisibility(VISIBLE);
			}
		}
	};
	
	public NoticeListView(Context context) {
		super(context);
	}
	
	public NoticeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
	
	public NoticeListView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
	}
	
	public void setData(ArrayList<HashMap<String, Object>> noticeListItemDatas) {
		
		noticeListAdapter = new SimpleAdapter(getContext(),
				noticeListItemDatas, R.layout.list_notice_item, new String[] {
						"noticeName", "noticePublicTime", "noticeContent" },
				new int[] { R.id.list_notice_item_name,
						R.id.list_notice_item_time_public,
						R.id.list_notice_item_content });
		this.setAdapter(noticeListAdapter);
		this.setOnItemClickListener(noticeListItemOnClickListener);
	}
	
}
