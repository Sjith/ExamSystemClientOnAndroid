package org.scauhci.ExamSystem.android.view.paper;

import org.scauhci.ExamSystem.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PaperFragment extends Fragment {

	View root;
	
	public static PaperFragment newInstance() {
		PaperFragment paperFragment = new PaperFragment();
		
		return paperFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_paper, container, false);
		return root;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

}
