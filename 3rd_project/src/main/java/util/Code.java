package util;

public class Code {

	public static String getCode() {
		String result = "";
		double num = Math.random();
		int num1 = (int)Math.round(num*100);
		result = String.valueOf(num1);
		return result;
	}
}
