package org.scauhci.ExamSystem.android.view.exam;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.R;
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
	ExamModule examModule = new ExamModule();

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
		
		ArrayList<HashMap<String, Object>> examListItemDatas = examModule.getAllExamListItemData();
		examListView.setData(examListItemDatas);
	}
}
