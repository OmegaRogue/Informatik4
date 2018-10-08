package omegaRogue;

import java.util.HashMap;
import java.util.Map;

public class CharImg {
	public static Map<String, String> charMap;
	public static String test;
	public static void main(String[] args) {
		charMap = new HashMap<String,String>();
		charMap.put("00","\n");
		charMap.put("01"," ");
		charMap.put("02","─");
		charMap.put("03","│");
		charMap.put("04","┌");
		charMap.put("05","┐");
		charMap.put("06","└");
		charMap.put("07","┘");
		charMap.put("08","├");
		charMap.put("09","┤");
		charMap.put("0A","┬");
		charMap.put("0B","┼");
		test =  "0405010101040500"+
				"0607010101060700"+
				"0101010101010100"+
				"0602020202020700";
		System.out.println(generate(test));
		
	}
	public static String generate(String hexCode) {
		String out = "";
		for (int i = 0; i < hexCode.length(); i+=2) {
			out += charMap.get(hexCode.substring(i, i+2));
		}
		return out;
	}
	


}
