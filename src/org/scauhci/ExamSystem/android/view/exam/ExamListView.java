package org.scauhci.ExamSystem.android.view.exam;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.R;
import org.scauhci.ExamSystem.android.tool.Flag;
import org.scauhci.ExamSystem.android.view.paper.PaperAcitvity;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ExamListView extends ListView {

	SimpleAdapter examListAdapter;
	AdapterView.OnItemClickListener examListItemOnClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View examListItemView,
				int position, long id) {
			Log.e(Flag.DEBUG, "You are click the item " + position + " in ExamList");
			Log.e(Flag.DEBUG, getContext().getClass().getName());
			Intent intent = new Intent(getContext(), PaperAcitvity.class);
			getContext().startActivity(intent);
		}
	};

	public ExamListView(Context context) {
		super(context);
	}

	public ExamListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ExamListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setData(ArrayList<HashMap<String, Object>> examListItemDatas) {

		examListAdapter = new SimpleAdapter(getContext(), examListItemDatas,
				R.layout.list_exam_item, new String[] { "examName",
						"examLastTime", "examStartTime", "examEndTime" },
				new int[] { R.id.list_exam_item_name,
						R.id.list_exam_item_time_last,
						R.id.list_exam_item_time_start,
						R.id.list_exam_item_time_end });
		this.setOnItemClickListener(examListItemOnClickListener);
		this.setAdapter(examListAdapter);
	}
}
