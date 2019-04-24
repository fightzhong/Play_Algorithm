package sort;

// swap the data of i1 and i2 which are in arr;
/**
 * @author 
 */
public class Swap {
	public static <T> void swap (T[] arr, int i1, int i2) {
		T temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
}
