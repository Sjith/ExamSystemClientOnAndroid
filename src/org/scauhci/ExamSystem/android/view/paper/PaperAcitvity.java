package org.scauhci.ExamSystem.android.view.paper;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.R;
import org.scauhci.ExamSystem.android.module.ExamModule;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;

public class PaperAcitvity extends FragmentActivity {

	PaperTitleFragmentAdapter paperTitleFragmentAdapter;
	ViewPager paperPager;
	TitlePageIndicator paperPageIndicator;
	String paperId;
	ArrayList<HashMap<String, Object>> questionDatas;
	ExamModule examModule = new ExamModule();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paper);

		/* Init the data of paper and question. */
		Bundle bundle = this.getIntent().getExtras();
		paperId = bundle.getString("paperId");
		
		questionDatas = examModule.getQuestionDatasByPaperId(paperId);

		initViewPager();
	}

	public ArrayList<HashMap<String, Object>> getQuestionDatas() {
		return questionDatas;
	}

	private void initViewPager() {
		paperTitleFragmentAdapter = new PaperTitleFragmentAdapter(
				getSupportFragmentManager());

		paperTitleFragmentAdapter.setCount(questionDatas.size());
		paperTitleFragmentAdapter.setQuestionDatas(questionDatas);
		paperPager = (ViewPager) findViewById(R.id.paper_pager);
		paperPager.setAdapter(paperTitleFragmentAdapter);
		paperPageIndicator = (TitlePageIndicator) findViewById(R.id.paper_indicator);
		paperPageIndicator.setViewPager(paperPager);
		paperPageIndicator.setFooterIndicatorStyle(IndicatorStyle.Triangle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

}
