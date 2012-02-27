package org.scauhci.ExamSystem.android.view.paper;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.R;
import org.scauhci.ExamSystem.android.module.ExamListModule;
import org.scauhci.ExamSystem.android.module.ExamModule;
import org.scauhci.ExamSystem.android.module.PaperModule;
import org.scauhci.ExamSystem.android.tool.Flag;
import org.scauhci.ExamSystem.android.view.exam.ExamListActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;

public class PaperAcitvity extends FragmentActivity {

	HashMap<String, Object> examData;
	PaperTitleFragmentAdapter paperTitleFragmentAdapter;
	PaperPager paperPager;
	TitlePageIndicator paperPageIndicator;
	String paperId;
	String examId;
	ArrayList<HashMap<String, Object>> questionDatas;
	PaperModule paperModule;
	ExamModule examModule;
	ExamListModule examListModule; 
	ExamListActivity examListActivity;

	public ExamModule getExamModule() {
		return examModule;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paper);

		/** Initialize the member variables.*/
		Bundle bundle = this.getIntent().getExtras();
		examId = bundle.getString("examId");
		examListModule = ExamListModule.getInstance();
		examModule = examListModule.getExamModule(examId);
		paperModule = examModule.getPaperModule();
		examData = examModule.getExamData();
		questionDatas = paperModule.getAllQuestionData();

		initViewPager();
		initSlidingDrawer();
	}

	public ArrayList<HashMap<String, Object>> getAllQuestionData() {
		return questionDatas;
	}

	private void initViewPager() {
		paperTitleFragmentAdapter = new PaperTitleFragmentAdapter(
				getSupportFragmentManager());

		paperTitleFragmentAdapter.setQuestionDatas(questionDatas);
		paperTitleFragmentAdapter.setExamData(examData);
		paperPager = (PaperPager) findViewById(R.id.paper_pager);
		paperPager.setAdapter(paperTitleFragmentAdapter);
		paperPageIndicator = (TitlePageIndicator) findViewById(R.id.paper_indicator);
		paperPageIndicator.setViewPager(paperPager);
		paperPageIndicator.setFooterIndicatorStyle(IndicatorStyle.Triangle);
	}
	
	private void initSlidingDrawer() {
		PaperSlidingDrawer paperSlidingDrawer = (PaperSlidingDrawer) findViewById(R.id.sliding_drawer_paper);
		
		paperSlidingDrawer.setQuestionDatas(questionDatas);
		paperSlidingDrawer.initQuestionListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	public ArrayList<HashMap<String, Object>> getQuestionOptionDatasByQuestionId(String questionId){
		return paperModule.getQuestionOptionDatasByQuestionId(questionId);
	}
	
	public void startExam(){
		paperPager.setPagingEnable(true);
		paperTitleFragmentAdapter.setCount(questionDatas.size());
		paperPager.setCurrentItem(1);
	}
	
	public void finishExam() {
		paperPager.setPagingEnable(true);
		paperTitleFragmentAdapter.setCount(0);
		examModule.finishExam();
	}
}
