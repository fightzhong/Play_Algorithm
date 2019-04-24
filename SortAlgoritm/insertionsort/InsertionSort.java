package insertionsort;
import java.util.Arrays;

import sort.SortInterface;
import sort.Swap;

/*
 	时间复杂度: O(n^2)
	插入排序分析: 由于我们一直是将数据不停的往前放入恰当的位置, 那么在被排序的当前值的前面的所有值都是从小到大排序的,
	 				所以在数据是从小到大的排列情况下, 其时间复杂度能缩小到O(n)级别
 */
/**
 * @author 
 */
public class InsertionSort implements SortInterface{

	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		// get the duplicate array of arr
		arr = Arrays.copyOf(arr, arr.length);
		
		return sort(arr, 0, arr.length - 1);
	}
	
	/**根据边界来进行插入排序, [l, r]前闭后闭, 排序的是一个副本数组, 原数组是没有进行任何改变的**/ 
	public <T extends Comparable<T>> T[] sort (T[] arr, int l, int r) {
		arr = Arrays.copyOf(arr, arr.length);
		
		for (int i = l + 1; i <= r; i++) {
			
			for (int k = i; k > l; k--) {
				if (arr[k].compareTo(arr[k - 1]) < 0) {
					Swap.swap(arr, k, k - 1);
				} else {
					break;
				}
			}
		}
		
		return arr;
	}
	
	/**根据边界来进行插入排序, [l, r]前闭后闭, 排序的是一个原数组**/
	public <T extends Comparable<T>> void sortOriginal (T[] arr, int l, int r) {
		
		for (int i = l + 1; i <= r; i++) {
			
			for (int k = i; k > l; k--) {
				if (arr[k].compareTo(arr[k - 1]) < 0) {
					Swap.swap(arr, k, k - 1);
				} else {
					break;
				}
			}
		}
	}
}
