package org.scauhci.ExamSystem.android.view.exam;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.R;

import android.os.Bundle;
import android.support.v4.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExamListFragment extends Fragment {

	View root;
	ExamListView examListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_exam_list, container, false);
		
		initExamListView();
		changeActionBar();
		return root;
	}
	
	public void changeActionBar(){
		ActionBar actionBar = ((FragmentActivity) getActivity()).getSupportActionBar();
		
		actionBar.setTitle(R.string.exam_list_title);
	}

	public void initExamListView() {
		examListView = (ExamListView) (root.findViewById(R.id.list_exam));
		
		/*The code between start and end should be write in a method which can get the noticeListItemDatas.*/
		/*Start*/
		ArrayList<HashMap<String, Object>> examListItemDatas = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> examListItemData = new HashMap<String, Object>();

		examListItemData.put("examName", "考试名字测试");
		examListItemData.put("examLastTime", "考试时长：测试");
		examListItemData.put("examStartTime", "开始时间：测试");
		examListItemData.put("examEndTime", "结束时间：测试");
		examListItemDatas.add(examListItemData);
		/*End*/
		examListView.setData(examListItemDatas);
	}
}
