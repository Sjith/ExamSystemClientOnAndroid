package org.scauhci.ExamSystem.android.dao;

import java.util.ArrayList;

import org.scauhci.ExamSystem.android.pojo.ExamPojo;
import org.scauhci.ExamSystem.android.pojo.ScorePojo;
import org.scauhci.ExamSystem.android.pojo.StudentPojo;
import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

public class ScoreDao {

	public int add(ScorePojo scorePojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int delete(ScorePojo scorePojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public int change(ScorePojo scorePojo) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public ScorePojo getScorePojoByExamPojoAndStudentPojo(StudentPojo studentPojo, ExamPojo examPojo)	{
		ScorePojo scorePojo = null;
		return scorePojo;
	}
	
	public ArrayList<ScorePojo> getScorePojosByStudentPojo(StudentPojo studentPojo) {
		ArrayList<ScorePojo> scorePojos = null;
		return scorePojos;
	}
}
