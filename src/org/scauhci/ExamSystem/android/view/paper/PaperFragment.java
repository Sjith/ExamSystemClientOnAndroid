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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class PaperFragment extends Fragment {

	View root;
	PaperAcitvity paperAcitvity;
	HashMap<String, Object> questionData;
	ArrayList<HashMap<String, Object>> questionOptionDatas;
	RadioGroup questionOptions;
	TextView questionContent;
	int questionOptionChooseIndex = -1;
	boolean isQuestionStarted = false;
	CheckBox questionStar;
	OnCheckedChangeListener questionOptionCheckedChangeListener;
	android.widget.CompoundButton.OnCheckedChangeListener questionStarCheckedChangeListener;

	public static PaperFragment newInstance(HashMap<String, Object> questionData) {
		PaperFragment paperFragment = new PaperFragment();

		paperFragment.questionData = questionData;

		return paperFragment;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		paperAcitvity = (PaperAcitvity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_paper, container, false);

		initQuestionData();

		return root;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	public void initQuestionStarButton() {
		questionStar = (CheckBox) root.findViewById(R.id.question_star_button);

		questionStarCheckedChangeListener = new android.widget.CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					questionData.put("isQuestionStarted", true);
				} else {
					questionData.put("isQuestionStarted", false);
				}
				isQuestionStarted = (Boolean) questionData
						.get("isQuestionStarted");
			}
		};

		questionStar
				.setOnCheckedChangeListener(questionStarCheckedChangeListener);

		isQuestionStarted = (Boolean) questionData.get("isQuestionStarted");
		if (isQuestionStarted != questionStar.isChecked()) {
			questionStar.setChecked(isQuestionStarted);
		}
	}

	public void initQuestionData() {
		questionContent = (TextView) root.findViewById(R.id.question_content);
		questionContent.setText(questionData.get("questionContent") + "");
		questionOptionDatas = paperAcitvity
				.getQuestionOptionDatasByQuestionId(questionData
						.get("questionId") + "");
		questionOptions = (RadioGroup) root.findViewById(R.id.question_options);

		questionOptionCheckedChangeListener = new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				RadioButton questionOption = (RadioButton) questionOptions
						.findViewById(checkedId);
				if (group.indexOfChild(questionOption) != -1) {
					questionOptionChooseIndex = group
							.indexOfChild(questionOption);
					questionData.put("questionAnswer",
							questionOptionDatas.get(questionOptionChooseIndex)
									.get("questionOptionContent"));
				}
			}
		};
		questionOptions
				.setOnCheckedChangeListener(questionOptionCheckedChangeListener);

		for (HashMap<String, Object> questionOptionData : questionOptionDatas) {
			RadioButton questionOption = (RadioButton) (getLayoutInflater(getArguments())
					.inflate(R.layout.question_option_item, questionOptions,
							false));
			questionOption.setText(questionOptionData
					.get("questionOptionContent") + "");
			if ((questionData.get("questionAnswer") + "")
					.equals(questionOptionData.get("questionOptionContent")
							+ "")) {
				questionOptions.addView(questionOption);
				questionOption.setChecked(true);
				questionOptionChooseIndex = questionOptionDatas
						.indexOf(questionOption);
			} else {
				questionOptions.addView(questionOption);
			}
		}
	}
}
