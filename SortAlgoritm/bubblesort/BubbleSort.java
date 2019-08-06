package bubblesort;
import java.util.Arrays;

import sort.SortInterface;
import sort.Swap;

/**
 * @author 
 */
public class BubbleSort implements SortInterface{
	
	/**初始化版本**/
	public <T extends Comparable<T>> T[] sort1(T[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		
		// start to sort 
		for (int i = 0; i < arr.length - 1; i++) {
			
			for (int j = 0; j < (arr.length - 1 - i); j++) {
				if (arr[j].compareTo(arr[j + 1]) > 0) {
				Swap.swap(arr, j, j + 1);
			}
		}
			
		}
		
		return arr;
	}
	
	/**优化版本, 增加了一个isSwap判断**/ 
	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		
		// start to sort 
	   // 用于记录是否已经是从小到大的顺序排列, 如果是则不进行排列
		boolean isSwap = true; 
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < (arr.length - 1 - i); j++) {
				if (arr[j].compareTo(arr[j + 1]) > 0) {
					Swap.swap(arr, j, j + 1);
					isSwap = false;
				}
			}
			
			// 如果没有进行交换, 则说明是从小到大的顺序排列的, 此时应该直接退出循环
			if (isSwap) {
				break;
			}
		}
		
		return arr;
	}
	
	
	
}
