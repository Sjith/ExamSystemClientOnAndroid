package org.scauhci.ExamSystem.android.view.paper;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.R;
import org.scauhci.ExamSystem.android.tool.Flag;

import com.viewpagerindicator.TitlePageIndicator;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class QuestionListView extends ListView {

	SimpleAdapter questionListAdapter;
	AdapterView.OnItemClickListener questionListItemOnClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View questionListItemView, int position, long id)
		{
			questionListItemView.setBackgroundResource(R.drawable.list_item_star);
			TitlePageIndicator paperPageIndicator = (TitlePageIndicator) ((PaperAcitvity)getContext()).findViewById(R.id.paper_indicator);
			PaperSlidingDrawer paperSlidingDrawer = (PaperSlidingDrawer) ((PaperAcitvity)getContext()).findViewById(R.id.sliding_drawer_paper);
			paperSlidingDrawer.animateToggle();
			paperPageIndicator.setCurrentItem(position + 1);
		}
	};
	
	public QuestionListView(Context context) {
		super(context);
	}
	
	public QuestionListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
	
	public QuestionListView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
	}
	
	public void setData(ArrayList<HashMap<String, Object>> questionListItemDatas) {
		
		questionListAdapter = new SimpleAdapter(getContext(),
				questionListItemDatas, R.layout.list_question_item, new String[] {
						"questionIndex", "questionContent" },
				new int[] { R.id.list_question_item_index,
						R.id.list_question_item_name });
		this.setAdapter(questionListAdapter);
		this.setOnItemClickListener(questionListItemOnClickListener);
	}
	
}
