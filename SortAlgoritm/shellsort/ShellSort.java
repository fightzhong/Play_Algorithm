package shellsort;

import java.util.Arrays;

import sort.SortInterface;
import sort.Swap;

/*
 	时间复杂度: 介于O(nlogn) 与 O(n^2) 之间
  	希尔排序, 将所有的元素分为指定的若干个小组, 以增量来分割, 对每一个小组都进行插入排序, 
  				然后依次缩小增量, 即每一组的个数增多了, 然后再进行排序, 当增量达到1的时候, 此时
  				元素处于基本有序状态, 继续执行插入排序, 此时插入排序效率会高很多, 因为对于有序的
  				元素, 可以直接break退出循环
  				
  	对于插入排序: 因为插入排序只能让当前元素前进一个位置, 而希尔排序通过增量的形式, 
  						可以让一个元素可以一次性地朝最终位置前进一大步, 从而极大的减少了移动和
  						swap的使用, 性能极高！！
 */ 
/**
 * @author 
 */
public class ShellSort implements SortInterface {

	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		
//		int mid = arr.length / 2;
//		while (mid > 0) {
//			// 分为几组就执行几次的插入排序
//			for (int i = 0; i < mid; i++) {
//				// 对每一组的值进行插入排序
//				for (int k = i + mid; k < arr.length; k += mid) {
//					
//					for (int j = k; j > i; j -= mid) {
//						if (arr[j].compareTo(arr[j - mid]) < 0) {
//							Swap.swap(arr, j, j - mid);
//						} else {
//							break;
//						}
//					}
//					
//				}
//			}
//			mid /= 2;
//		}
		sortOriginal(arr, 0, arr.length - 1);
		
		return arr;
	}
	
	/**对闭区间[l, r]的数据进行希尔排序**/
	public <T extends Comparable<T>> void sortOriginal (T[] arr, int l, int r) {
		
		// 通过元素个数进行分组
		int group = (r - l + 1) / 2;
		
		// 不停的进行分组, 组数由大-> 小, 从而使得每一组的元素个数由少到多, 少的时候每一个元素跨度很大, 不再是原来插入排序的一个跨度
		while (group > 0) {
			
			// 从[l, group + l]这个范围为每一组的初始值, 也是分为多少个组, 则对多少个组进行插入排序
			for (int i = l; i < group + l; i++) {
				
				// 从该组第二个元素开始, 第i组的第二个元素为 i + group, 一直进行插入排序
				for (int k = i + group; k <= r; k += group) {
					
					for (int j = k; j > i; j -= group) {
						if (arr[j].compareTo(arr[j - group]) < 0) {
							Swap.swap(arr, j, j - group);
						} else {
							// 如果该元素比前一个元素大则直接退出循环(因为前面的元素已经是从小到大了)
							break;
						}
					}
					
				}
				
			}
			
			group /= 2;
		}
		
	}
	
}
