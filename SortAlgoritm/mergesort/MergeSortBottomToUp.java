package mergesort;

import java.lang.reflect.Array;
import java.util.Arrays;

import sort.SortInterface;
/**自底向上的归并排序**/ 
/**
 * @author
 */
public class MergeSortBottomToUp implements SortInterface{
	
	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		
		int mergeLen = 2;
		int m = 0;
		while (mergeLen < arr.length) {
			
			for (int i = 0; i < arr.length; i += mergeLen) {
				int r = Math.min(i + mergeLen - 1, arr.length - 1);
				merge(arr, i, r, m);
				m = Math.min(m + mergeLen, arr.length - 1); 
			}
			
			mergeLen *= 2;
			m = mergeLen / 2 - 1;
		}
		merge(arr, 0, arr.length - 1, m);
		return arr;
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Comparable<T>> void merge (T[] arr, int l, int r, int m) {
		T[]newArr = (T[])Array.newInstance(arr.getClass().getComponentType(), r - l + 1);
		
		int insertIndex = 0;
		if (l == r || m >= r ||arr[m].compareTo(arr[m + 1]) <= 0) { 
			return;
		} else if (arr[r].compareTo(arr[l]) <= 0) {
			for (int i = m + 1; i <= r; i++) {
				newArr[insertIndex++] = arr[i];
			}
			
			for (int i = l; i <= m; i++) {
				newArr[insertIndex++] = arr[i];
			}
			
			
		} else { // 这里开始不满足两个数组一边倒的情况
			int lIndex = l;
			int rIndex = m + 1;
			while (lIndex <= m && rIndex <= r) {
				if (arr[lIndex].compareTo(arr[rIndex]) < 0) {
					newArr[insertIndex++] = arr[lIndex++];
				} else {
					newArr[insertIndex++] = arr[rIndex++];
				}
			}

			if (lIndex <= m) {
				for (int i = lIndex; i <= m; i++) {
					newArr[insertIndex++] = arr[lIndex++];
				}
			} else {
				for (int i = rIndex; i <= r; i++) {
					newArr[insertIndex++] = arr[rIndex++];
				}
			}
		}
		
		// 将元素替换
		for (int i = l; i <= r; i++) {
			arr[i] = newArr[i - l];
		}
	}
}
