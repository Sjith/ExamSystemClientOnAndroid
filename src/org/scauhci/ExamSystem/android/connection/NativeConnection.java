package org.scauhci.ExamSystem.android.connection;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;
import org.scauhci.ExamSystem.android.tool.GetThing;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;

public class NativeConnection {
	public int saveToNativeFile(InputStream inputStream, String sourcePath,
			String targetPath) {
		int executeResult = ExecuteResultFlag.ERROR;
		return executeResult;
	}

	public InputStream openFromNativeFile(Uri sourceUri) {
		ContentResolver contentResolver = GetThing.getActivity().getContentResolver();
		InputStream inputStream = null;
		try {
			inputStream = contentResolver.openInputStream(sourceUri);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return inputStream;
	}
}
