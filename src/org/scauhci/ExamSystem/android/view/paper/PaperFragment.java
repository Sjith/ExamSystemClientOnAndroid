package org.scauhci.ExamSystem.android.view.paper;

import java.util.ArrayList;
import java.util.HashMap;

import org.scauhci.ExamSystem.android.R;
import org.scauhci.ExamSystem.android.module.ExamModule;
import org.scauhci.ExamSystem.android.tool.Flag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PaperFragment extends Fragment {

	View root;
	HashMap<String, Object> questionData;
	ArrayList<HashMap<String, Object>> questionOptionDatas;
	ExamModule examModule = new ExamModule();

	public static PaperFragment newInstance(HashMap<String, Object> questionData) {
		PaperFragment paperFragment = new PaperFragment();

		paperFragment.questionData = questionData;
		
		return paperFragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_paper, container, false);
		
		initQuestionOptionData();
		
		return root;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	public void initQuestionOptionData(){
		questionOptionDatas = examModule.getQuestionOptionDatasByQuestionId(questionData.get("questionId") + "");
		TextView questionContent = (TextView) root.findViewById(R.id.question_content);
		questionContent.setText(questionData.get("questionContent") + "");
		RadioGroup questionOptions = (RadioGroup) root.findViewById(R.id.question_options);
		
		Log.e(Flag.DEBUG, questionOptionDatas + "");
		for (HashMap<String, Object> questionOptionData : questionOptionDatas) {
			RadioButton questionOption = (RadioButton) (getLayoutInflater(getArguments()).inflate(R.layout.question_option_item, questionOptions, false));
			questionOption.setText(questionOptionData.get("questionOptionContent") + "");
			questionOptions.addView(questionOption);
		}
	}
}
