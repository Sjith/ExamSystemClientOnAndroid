package org.scauhci.ExamSystem.android.dao;

import org.scauhci.ExamSystem.android.tool.ExecuteResultFlag;
import org.scauhci.ExamSystem.android.tool.LogLabelFlag;
import org.scauhci.ExamSystem.android.tool.SQLStatement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DaoHelper extends SQLiteOpenHelper {

	public DaoHelper(Context context, String databasePath,
			CursorFactory cursorFactory, int databaseVersion) {
		super(context, databasePath, cursorFactory, databaseVersion);
		Log.e(LogLabelFlag.DEBUG, "The database creates sucessfully.");
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(SQLStatement.CREATE_TABLE_COURSE);
		database.execSQL(SQLStatement.CREATE_TABLE_EXAM);
		database.execSQL(SQLStatement.CREATE_TABLE_NOTICE);
		database.execSQL(SQLStatement.CREATE_TABLE_PAPER);
		database.execSQL(SQLStatement.CREATE_TABLE_QUESTION);
		database.execSQL(SQLStatement.CREATE_TABLE_QUESTION_OPTION);
		database.execSQL(SQLStatement.CREATE_TABLE_RELATION_PAPER_QUESTION);
		database.execSQL(SQLStatement.CREATE_TABLE_REMARK);
		database.execSQL(SQLStatement.CREATE_TABLE_SCORE);
		database.execSQL(SQLStatement.CREATE_TABLE_STUDENT);
		database.execSQL(SQLStatement.CREATE_TABLE_SUBMIT_ANSWER);
		Log.e(LogLabelFlag.DEBUG, "The tables create successfully.");
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.e(LogLabelFlag.DEBUG, "The database upgrade from version "
				+ oldVersion + " to version " + newVersion);
	}

	public int insert(String tableName, String[] keys, String[] values) {
		int executeResult = ExecuteResultFlag.ERROR;

		String allKey = null, allValue = null;

		for (int i = 0; i < keys.length; i++) {
			allKey += "'" + keys[i] + "', ";
			allValue += "'" + values[i] + "', ";
		}
		allKey = allKey.substring(0, allKey.length() - 2);
		allValue = allValue.substring(0, allValue.length() - 2);

		String[] bindArgs = { tableName, allKey, allValue };
		this.getReadableDatabase().execSQL(SQLStatement.INSERT, bindArgs);
		Log.e(LogLabelFlag.DEBUG, "The record inserts successfully.");

		return executeResult;
	}

	public int delete(String tableName, String[] whereConditionKeys,
			String[] whereConditionValues) {
		int executeResult = ExecuteResultFlag.ERROR;

		String whereConditions = null;

		for (int i = 0; i < whereConditionKeys.length; i++) {
			whereConditions += " " + whereConditionKeys[i] + " = " + "'"
					+ whereConditionValues[i] + "' AND";
		}
		whereConditions = whereConditions.substring(0,
				whereConditions.length() - 5);

		String[] bindArgs = { tableName, whereConditions };
		this.getReadableDatabase().execSQL(SQLStatement.DELETE, bindArgs);
		Log.e(LogLabelFlag.DEBUG, "The record deletes successfully.");

		return executeResult;
	}

	public int update(String tableName, String[] keys, String[] newValues,
			String[] whereConditionKeys, String[] whereConditionValues) {
		int executeResult = ExecuteResultFlag.ERROR;

		String setConditions = null, whereConditions = null;

		for (int i = 0; i < keys.length; i++) {
			setConditions += " " + keys[i] + " = " + "'" + newValues[i] + "',";
		}
		setConditions = setConditions.substring(0, setConditions.length() - 2);
		for (int i = 0; i < whereConditionKeys.length; i++) {
			whereConditions += " " + whereConditionKeys[i] + " = " + "'"
					+ whereConditionValues[i] + "' AND";
		}
		whereConditions = whereConditions.substring(0,
				whereConditions.length() - 5);

		String[] bindArgs = { setConditions, tableName, whereConditions };
		this.getReadableDatabase().execSQL(SQLStatement.UPDATE, bindArgs);
		Log.e(LogLabelFlag.DEBUG, "The record updates successfully.");

		return executeResult;
	}

	public Cursor select(String tableName, String[] keys,
			String[] whereConditionKeys, String[] whereConditionValues) {

		String[] selectionArgs = null;

		String selectConditions = null, whereConditions = null;

		for (int i = 0; i < keys.length; i++) {
			selectConditions += keys + ",";
		}
		selectConditions = selectConditions.substring(0,
				selectConditions.length() - 2);
		if (whereConditionKeys != null) {
			for (int i = 0; i < whereConditionKeys.length; i++) {
				whereConditions += " " + whereConditionKeys[i] + " = " + "'"
				+ whereConditionValues[i] + "' AND";
			}
			whereConditions = whereConditions.substring(0,
					whereConditions.length() - 5);
			selectionArgs = new String[]{ selectConditions, tableName + " WHERE " + whereConditions };
		} else {
			selectionArgs = new String[]{ selectConditions, tableName };
		}
		Cursor cursor = getReadableDatabase().rawQuery(SQLStatement.SELECT,
				selectionArgs);
		Log.e(LogLabelFlag.DEBUG, "Get the records successfully.");

		return cursor;
	}
}
