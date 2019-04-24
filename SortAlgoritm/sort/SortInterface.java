package sort;

/**
 * @author zhongshenglong
 */
public interface SortInterface {
	/**
	 * The sort funtion that all sort class must has to implements
	 * @return
	 * @param arr is the array to be sorted 
	 */ 
	<T extends Comparable<T>> T[] sort(T[] arr);
}
