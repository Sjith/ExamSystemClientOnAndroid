package org.scauhci.ExamSystem.android.tool;

public class HashValue {

	public static long getDJBHashValue(String key) {
		long value = 5381;

		for (int i = 0; i < (key.length() > 10 ? 10 : key.length()); i++) {
			value = ((value << 5) + value) + key.charAt(i);
		}

		return value;
	}
}
