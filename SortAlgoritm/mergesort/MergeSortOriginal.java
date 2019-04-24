package mergesort;

import java.util.Arrays;

import insertionsort.InsertionSort;
import sort.SortInterface;
/**
 * 直接修改原数组的元素来进行排序, 比MergeSort少开辟了好多数组空间, 但是时间耗费更大, 建议用空间来换时间
 * @author 
 */
public class MergeSortOriginal implements SortInterface{
	private static int MERGE_SORT_LEAST_LENGTH = 15;
	
	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		
		sort(arr, 0, arr.length - 1);
		
		return arr;
	}

	/**对arr的[l, r]前闭后闭的元素进行排序**/ 
	private <T extends Comparable<T>> void sort(T[] arr, int l, int r) {
		if (r - l <= MERGE_SORT_LEAST_LENGTH) {
			new InsertionSort().sortOriginal(arr, l, r);
			return;
		}
		
		int m = (r - l) / 2 + l;
		
		// 将当前数组分割为两份, 分别对这两份进行归并排序
		sort(arr, l, m);
		sort(arr, m + 1, r);
		
		merge(arr, l, m, r);
	}
	
	private <T extends Comparable<T>> void merge(T[] arr, int l, int m, int r) {
		// 复制当前数组的元素, 对这些元素在newArr中的进行归并操作, 并将其归并后的结果取代原数组指定位置
		T[] newArr = Arrays.copyOfRange(arr, l, r + 1);
		
		// 根据原数组的m, 计算其在newArr中的m
		int mid = m - l;
		
		// 原数组中应该插入元素的位置 
		int insertIndex = l;
		
		// 左边的所有元素都小于等于右边所有的元素, 此时应该不做任何操作
		if (newArr[mid].compareTo(newArr[mid + 1]) <= 0) {
			return;
		}
		// 右边所有的元素小于等于左边所有的元素
		else if (newArr[0].compareTo(newArr[newArr.length - 1]) >= 0) { 
			// 先将右边所有的元素放入原数组
			for (int i = mid + 1; i <= newArr.length - 1; i++) {
				arr[insertIndex++] = newArr[i];
			}
			
			// 再将左边所有的值放入原数组
			for (int i = 0; i <= mid; i++) {
				arr[insertIndex++] = newArr[i];
			}
		} else { // 此时应该对两个数组的值都进行判断, 不存在一边全大于另一边的情况
			// newArr中对于左边的数据正在被操作的位置
			int lIndex = 0;
			
			// newArr中对于右边的数据正在被操作的位置
			int rIndex = mid + 1;
			
			// 两边的数组都存在数据的情况下
			while (lIndex <= mid && rIndex <= (newArr.length - 1)) { 
				if (newArr[lIndex].compareTo(newArr[rIndex]) < 0) {
					arr[insertIndex++] = newArr[lIndex++];
				} else {
					arr[insertIndex++] = newArr[rIndex++];
				}
			}
			
			// 此时只有一边存在数据
			if (lIndex <= mid) {
				while (lIndex <= mid) {
					arr[insertIndex++] = newArr[lIndex++];
				}
			} else {
				while (rIndex <= (newArr.length - 1)) {
					arr[insertIndex++] = newArr[rIndex++];
				}
			}
			
		}
	}

}
