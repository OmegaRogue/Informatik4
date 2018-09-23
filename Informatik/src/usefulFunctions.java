/**
 * <h1>Useful Functions</h1>
 * Useful Functions is a collection of misc methods 
 * @author OmegaRogue
 * @version 1.0
 * @since 23-09-2018
 *
 */
public class usefulFunctions {
	/**
	 * This Method is used to print integer arrays
	 * @param array This is the integer array
	 * @return String This returns the integer arrays values as a String
	 */
	public static String ArrayString(int[] array){
		String out = Integer.toString(array[0]);
		for (int i = 1; i < array.length; i++) {
			out = out + "; " + array[i];
		}
		return out;
	}
	/**
	 * This Method is used to print String arrays
	 * @param array This is the String array
	 * @return String This returns the String arrays values as a String
	 */
	public static String ArrayString(String[] array){
		String out = array[0];
		for (int i = 1; i < array.length; i++) {
			out = out + "; " + array[i];
		}
		return out;
	}
	/**
	 * This Method is used to print float arrays
	 * @param array This is the float array
	 * @return String This returns the float arrays values as a String
	 */
	public static String ArrayString(float[] array){
		String out = Float.toString(array[0]);
		for (int i = 1; i < array.length; i++) {
			out = out + "; " + array[i];
		}
		return out;
	}
	/**
	 * This Method is used to print boolean arrays
	 * @param array This is the boolean array
	 * @return String This returns the boolean arrays values as a String
	 */
	public static String ArrayString(boolean[] array){
		String out = Boolean.toString(array[0]);
		for (int i = 1; i < array.length; i++) {
			out = out + "; " + array[i];
		}
		return out;
	}
}
