package org.scauhci.ExamSystem.android.dao;

import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

import android.text.format.Time;

public class ExamDao {

	public int add(ExamPojo examPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int delete(ExamPojo examPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int change(ExamPojo examPojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public ExamPojo getExamPojoByExamId(String examId) {
		ExamPojo examPojo = null;
		return examPojo;
	}

	public ExamPojo getExamPojo() {
		ExamPojo examPojo = null;
		return examPojo;
	}

	public int getNumberOfExam() {
		int numberOfExam = 0;
		return numberOfExam;
	}

	//targetTime初始化为系统当前时间，当注意系统当前时间是否与网络时间对对应。
	public Time getRemainTime() {
		Time targetTime = null;
		return getRemainTime(targetTime);
	}

	public Time getRemainTime(Time targetTime) {
		Time remainTime = null;
		return remainTime;
	}
}
