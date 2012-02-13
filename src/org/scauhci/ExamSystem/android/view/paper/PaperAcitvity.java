package org.scauhci.ExamSystem.android.view.paper;

import org.scauhci.ExamSystem.android.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;

public class PaperAcitvity extends FragmentActivity {

	PaperTitleFragmentAdapter paperTitleFragmentAdapter;
	ViewPager paperPager;
	TitlePageIndicator paperPageIndicator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paper);
		
		initViewPager();
	}
	
	private void initViewPager(){
		paperTitleFragmentAdapter = new PaperTitleFragmentAdapter(getSupportFragmentManager());

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
