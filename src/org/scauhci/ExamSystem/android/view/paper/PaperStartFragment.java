package org.scauhci.ExamSystem.android.view.paper;

import java.util.HashMap;

import org.scauhci.ExamSystem.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class PaperStartFragment extends Fragment {
	View root;
	HashMap<String, Object> examData;
	TextView examNameView;
	TextView examStartTimeView;
	TextView examEndTimeView;
	TextView examLastTimeView;
	PaperAcitvity paperAcitvity;
	OnClickListener startExamListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			paperAcitvity.startExam();
			v.setEnabled(false);
		}
	};

	public static PaperStartFragment newInstance(HashMap<String, Object> examData) {
		PaperStartFragment paperStartFragment = new PaperStartFragment();

		paperStartFragment.setExamData(examData);
		return paperStartFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		paperAcitvity = (PaperAcitvity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root = inflater
				.inflate(R.layout.fragment_paper_start, container, false);

		initExamData();
		initStartExamButton();
		return root;
	}

	public void setExamData(HashMap<String, Object> examData) {
		this.examData = examData;
	}

	private void initExamData() {
		examNameView = (TextView) root.findViewById(R.id.exam_name);
		examStartTimeView = (TextView) root.findViewById(R.id.exam_start_time);
		examEndTimeView = (TextView) root.findViewById(R.id.exam_end_time);
		examLastTimeView = (TextView) root.findViewById(R.id.exam_last_time);
		
		examNameView.setText(examData.get("examName") + "");
		examStartTimeView.setText(examData.get("examStartTime") + "");
		examEndTimeView.setText(examData.get("examEndTime") + "");
		examLastTimeView.setText(examData.get("examLastTime") + "");
	}

	private void initStartExamButton() {
		Button startExamButton = (Button) root
				.findViewById(R.id.start_exam_button);

		startExamButton.setOnClickListener(startExamListener);
	}
}
