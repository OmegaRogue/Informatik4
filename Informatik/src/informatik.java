<<<<<<< HEAD
<<<<<<< HEAD:Informatik/src/Informatik.java


public class Informatik {

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
=======


public class informatik {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {32,17,24,7,8,12,4};
		System.out.print(a[0]);
		for (int i = 1; i < a.length; i++) {
			System.out.print("; "+a[i]);
		}
		
	}
	

}
>>>>>>> 1ed92f842152e3ae34c084dbc33049b377697a0f:Informatik/src/informatik.java
=======


public class informatik {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {32,17,24,7,8,12,4};
		System.out.print(a[0]);
		for (int i = 1; i < a.length; i++) {
			System.out.print("; "+a[i]);
		}
		
	}
	

}
>>>>>>> 1ed92f842152e3ae34c084dbc33049b377697a0f
