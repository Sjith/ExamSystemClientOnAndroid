package org.scauhci.ExamSystem.android.view.paper;

import org.scauhci.ExamSystem.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PaperEndFragment extends Fragment {
	View root;
	PaperAcitvity paperAcitvity;
	OnClickListener finishExamListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			paperAcitvity.finishExam();
			v.setEnabled(false);
		}
	};
	
	public static PaperEndFragment newInstance(){
		PaperEndFragment paperEndFragment = new PaperEndFragment();
		
		return paperEndFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		paperAcitvity = (PaperAcitvity) getActivity();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_paper_end, container, false);

		initFinishExamButton();
		return root;
	}
	
	private void initFinishExamButton() {
		Button finishExamButton = (Button) root
				.findViewById(R.id.finish_exam_button);

		finishExamButton.setOnClickListener(finishExamListener);
	}
}
