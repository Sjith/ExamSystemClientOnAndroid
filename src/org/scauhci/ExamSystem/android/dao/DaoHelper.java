package org.scauhci.ExamSystem.android.dao;

import org.scauhci.ExamSystem.android.tool.Flag;
import org.scauhci.ExamSystem.android.tool.GetThing;

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
		super(GetThing.getActivity(), databasePath, cursorFactory, databaseVersion);
		Log.e(Flag.DEBUG, "The DaoHelper creates sucessfully.");
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
		Log.e(Flag.DEBUG, "The tables create successfully.");
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.e(Flag.DEBUG, "The database upgrade from version " + oldVersion
				+ " to version " + newVersion);
	}

	public int insert(String tableName, String[] keys, String[] values) {
		int executeResult = Flag.ERROR;

		String allKey = "", allValue = "";

		for (int i = 0; i < keys.length; i++) {
			if (!values[i].equals("")) {
				allKey += "'" + keys[i] + "', ";
				allValue += "'" + values[i] + "', ";
			}
		}
		allKey = allKey.substring(0, allKey.length() - 2);
		allValue = allValue.substring(0, allValue.length() - 2);

		String[] bindArgs = { tableName, allKey, allValue };
		this.getReadableDatabase().execSQL(
				getCompleteStatement(SQLStatement.INSERT, bindArgs));
		Log.e(Flag.DEBUG, "The record inserts successfully.");

		return executeResult;
	}

	public int delete(String tableName, String[] whereConditionKeys,
			String[] whereConditionValues) {
		int executeResult = Flag.ERROR;

		String whereConditions = "";

		for (int i = 0; i < whereConditionKeys.length; i++) {
			if (!whereConditionValues[i].equals("")) {
				whereConditions += " " + whereConditionKeys[i] + " = " + "'"
				+ whereConditionValues[i] + "' AND";
			}
		}
		whereConditions = whereConditions.substring(0,
				whereConditions.length() - 5);

		String[] bindArgs = { tableName, whereConditions };
		this.getReadableDatabase().execSQL(
				getCompleteStatement(SQLStatement.DELETE, bindArgs));
		Log.e(Flag.DEBUG, "The record deletes successfully.");

		return executeResult;
	}

	public int update(String tableName, String[] keys, String[] newValues,
			String[] whereConditionKeys, String[] whereConditionValues) {
		int executeResult = Flag.ERROR;

		String setConditions = "", whereConditions = "";

		for (int i = 0; i < keys.length; i++) {
			if (!newValues[i].equals("")) {
				setConditions += " " + keys[i] + " = " + "'" + newValues[i] + "',";
			}
		}
		setConditions = setConditions.substring(0, setConditions.length() - 1);
		for (int i = 0; i < whereConditionKeys.length; i++) {
			if (!whereConditionValues[i].equals("")) {
				whereConditions += " " + whereConditionKeys[i] + " = " + "'"
				+ whereConditionValues[i] + "' AND";
			}
		}
		whereConditions = whereConditions.substring(0,
				whereConditions.length() - 5);

		String[] bindArgs = { setConditions, tableName, whereConditions };
		this.getReadableDatabase().execSQL(
				getCompleteStatement(SQLStatement.UPDATE, bindArgs));
		Log.e(Flag.DEBUG, "The record updates successfully.");

		return executeResult;
	}

	public Cursor select(String tableName, String[] keys,
			String[] whereConditionKeys, String[] whereConditionValues) {

		String[] selectionArgs;

		String selectConditions = "", whereConditions = "";

		for (int i = 0; i < keys.length; i++) {
			selectConditions += keys[i] + ",";
		}
		selectConditions = selectConditions.substring(0,
				selectConditions.length() - 1);
		if (whereConditionKeys != null) {
			for (int i = 0; i < whereConditionKeys.length; i++) {
				if (!whereConditionValues[i].equals("")) {
					whereConditions += " " + whereConditionKeys[i] + " = " + "'"
					+ whereConditionValues[i] + "' AND";
				}
			}
			whereConditions = whereConditions.substring(0,
					whereConditions.length() - 4);
			selectionArgs = new String[] { selectConditions,
					tableName + " WHERE " + whereConditions };
		} else {
			selectionArgs = new String[] { selectConditions, tableName };
		}
		Log.e(Flag.DEBUG, getReadableDatabase() + "");
		
		Cursor cursor = getReadableDatabase().rawQuery(
				getCompleteStatement(SQLStatement.SELECT, selectionArgs), null);

		return cursor;
	}

	public String getCompleteStatement(String basicStatement,
			String[] selectionArgs) {
		String completeStatement = "";

		for (int i = 0, j = 0; i < basicStatement.length(); i++) {
			if (basicStatement.charAt(i) == '?') {
				completeStatement += selectionArgs[j++];
			} else {
				completeStatement += basicStatement.charAt(i);
			}
		}

		return completeStatement;
	}
}
