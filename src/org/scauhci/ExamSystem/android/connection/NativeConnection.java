package org.scauhci.ExamSystem.android.connection;

import java.io.InputStream;

import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;

public class NativeConnection {
	public int saveToNativeFile(InputStream inputStream, String sourcePath,
			String targetPath) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}
}
