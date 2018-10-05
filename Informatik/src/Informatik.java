/**
 * Irgendwelche Sachen
 * @author OmegaRogue
 *
 */
public class Informatik {
/**
 * die Main Methode
 * 
 */
	public static void main(String[] args) {
		
		int[] a = {32,17,24,7,8,12,4};
		System.out.print(a[0]);
		for (int i = 1; i < a.length; i++) {
			System.out.print("; "+a[i]);
		}
		
	}
	public static String ArrayString(int[] array){
		//Array Elements to String
		String out = Integer.toString(array[0]);
		for (int i = 1; i < array.length; i++) {
			out = out + "; " + array[i];
		}
		return out;
	}
	public static String ArrayString(String[] array){
		//Array Elements to String
		String out = array[0];
		for (int i = 1; i < array.length; i++) {
			out = out + "; " + array[i];
		}
		return out;
	}
	public static String ArrayString(float[] array){
		//Array Elements to String
		String out = Float.toString(array[0]);
		for (int i = 1; i < array.length; i++) {
			out = out + "; " + array[i];
		}
		return out;
	}
	public static String ArrayString(boolean[] array){
		//Array Elements to String
		String out = Boolean.toString(array[0]);
		for (int i = 1; i < array.length; i++) {
			out = out + "; " + array[i];
		}
		return out;
	}

}


