package org.scauhci.ExamSystem.android.pojo;

public class OptionPojo {

	private String optionId;
	private String questionId;
	private String optionContent;
	private boolean isQuestionStdAnswer;

	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getContent() {
		return optionContent;
	}

	public void setContent(String content) {
		this.optionContent = content;
	}

	public boolean isStdAnswer() {
		return isQuestionStdAnswer;
	}

	public void setStdAnswer(boolean isStdAnswer) {
		this.isQuestionStdAnswer = isStdAnswer;
	}

}
