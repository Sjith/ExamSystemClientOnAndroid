package org.scauhci.ExamSystem.android.view.exam;

import org.scauhci.ExamSystem.android.R;

import android.os.Bundle;
import android.support.v4.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*TODO Complete this Class.*/
public class CreateExamFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_create_exam, container, false);
		
		changeActionBar();
		return root;
	}

	public void changeActionBar(){
		ActionBar actionBar = ((FragmentActivity) getActivity()).getSupportActionBar();
		
		actionBar.setTitle(R.string.create_exam_title);
	}
}
