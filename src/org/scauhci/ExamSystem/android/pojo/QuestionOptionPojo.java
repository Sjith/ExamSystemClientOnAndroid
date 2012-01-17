package org.scauhci.ExamSystem.android.pojo;

public class QuestionOptionPojo {

	private String questionOptionId;
	private String questionId;
	private String questionOptionContent;
	private boolean isQuestionStdAnswer;
	public String getQuestionOptionId() {
		return questionOptionId;
	}
	public void setQuestionOptionId(String questionOptionId) {
		this.questionOptionId = questionOptionId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestionOptionContent() {
		return questionOptionContent;
	}
	public void setQuestionOptionContent(String questionOptionContent) {
		this.questionOptionContent = questionOptionContent;
	}
	public boolean isQuestionStdAnswer() {
		return isQuestionStdAnswer;
	}
	public void setQuestionStdAnswer(boolean isQuestionStdAnswer) {
		this.isQuestionStdAnswer = isQuestionStdAnswer;
	}

}
