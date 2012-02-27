package org.scauhci.ExamSystem.android.view.paper;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.R;
import org.scauhci.ExamSystem.android.tool.Flag;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SlidingDrawer;

import com.viewpagerindicator.TitlePageIndicator;

/*TODO Complete this class.*/
public class PaperSlidingDrawer extends SlidingDrawer {

	ArrayList<HashMap<String, Object>> questionDatas;
	ArrayList<HashMap<String, Object>> questionListItemDatas = new ArrayList<HashMap<String, Object>>();
	QuestionListView questionListView;

	public PaperSlidingDrawer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PaperSlidingDrawer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		// initQuestionListView();
	}

	public void initQuestionListView() {
		questionListView = (QuestionListView) findViewById(R.id.sliding_drawer_paper_content_list_question);

		int i = 0;
		for (HashMap<String, Object> questionData : questionDatas) {
			HashMap<String, Object> questionListItemData = new HashMap<String, Object>();

			questionListItemData.put("questionContent",
					questionData.get("questionContent"));
			questionListItemData.put("questionIndex", ++i);

			questionListItemDatas.add(questionListItemData);
		}

		questionListView.setData(questionListItemDatas);
	}

	public void setQuestionDatas(
			ArrayList<HashMap<String, Object>> questionDatas) {
		this.questionDatas = questionDatas;
	}
}
