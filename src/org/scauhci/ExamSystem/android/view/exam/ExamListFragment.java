package org.scauhci.ExamSystem.android.view.exam;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.R;
import org.scauhci.ExamSystem.android.module.ExamListModule;
import org.scauhci.ExamSystem.android.module.ExamModule;

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
	ExamListActivity examListActivity;
	ArrayList<HashMap<String, Object>> examListItemDatas;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		examListActivity = (ExamListActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_exam_list, container, false);
		
		changeActionBar();
		initExamListView();
		return root;
	}
	
	public void changeActionBar(){
		ActionBar actionBar = ((FragmentActivity) getActivity()).getSupportActionBar();
		
		actionBar.setTitle(R.string.exam_list_title);
	}

	public void setExamListItemDatas(
			ArrayList<HashMap<String, Object>> examListItemDatas) {
		this.examListItemDatas = examListItemDatas;
		/*TODO Debug this NULL point exception.*/
	}

	public void initExamListView() {
		examListView = (ExamListView) (root.findViewById(R.id.list_exam));
		
		examListView.setExamListItemDatas(examListItemDatas);
	}
}
