package quicksort;

import java.util.Arrays;

import insertionsort.InsertionSort;
import sort.SortInterface;
import sort.Swap;
/**
 * 第一版本QuickSort: 通过递归的方式, 将一个区间的第一个元素放在这个区间应该放入的位置, 然后对其左右区间的元素进行相同的快速排序
 * 弊端: 对于一个基本有序的数组, 其很大概率退化成链表
 * @author 
 */
public class QuickSort implements SortInterface {

	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		sort(arr, 0, arr.length - 1);
		return arr;
	}
	
	/**快速排序递归算法, 对arr的[l, r]部分进行快速排序, 其实就是对该范围的第一个元素l快速排序, 查看其应该插入的位置**/
	private <T extends Comparable<T>> void sort(T[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		
		/**获取到应该插入的位置**/ 
		int littleIndex = l;
		
		for (int i = l + 1; i <= r; i++) {
			
			if (arr[i].compareTo(arr[l]) < 0) {
				// 小的情况, 将littleIndex后一位的值, 即比调整的值大的那一列中的第一个值调整到当前遍历值位置, 同时littleIndex 应该 + 1;
				Swap.swap(arr, i, ++littleIndex);
			}
			
		}
		
		// 到此为止, 其应该插入位置即为littleIndex位置, 交换该位置和被排序元素的位置
		int insertIndex = littleIndex;
		Swap.swap(arr, insertIndex, l);
		
		// 对其左边和右边的元素进行快速排序
		sort(arr, l, insertIndex - 1);
		sort(arr, insertIndex + 1, r);
		
	};
}

/**
 * 第一版快速排序存在的问题: 
 * 		<1> 当这个数组是一个近乎有序的数组, 或者这个数组就是一个有序的数组的时(最坏情况),
 * 			 那么其在进行递归的时候, 每次都会把右边的元素作为一个区间递归下去, 当前需要排序的元素就是其本身位置
 * 			因为没有进行两边的分化, 导致数据的快速排序区间退化成了一个链表的形式, [0, 10000] -> [1, 10000] -> [2, 10000],
 * 			并且会造成栈溢出的异常情况
 * 解决方案:(随机快速排序)
 *  		对一个排序的区间[l, r], 为了防止其有序状态下退化成链表的形式, 我们不能每次都对l这个位置的元素, 而应该对该区间的随机一个元素
 *  		进行排序, 这样就可以以二叉树的形式去递归
 *  	
 * 优化二: 对于区间个数小于15的, 我们对这15个元素进行插入排序(希尔排序)可以优化一些性能
 * 优化三: 如果区间过于庞大, 那么对于区间个数少于500左右甚至更多的应该采用希尔排序(希尔排序比插入排序性能好非常多)
 *  		
 * @author 
 */
class QuickSortByRandomSort implements SortInterface {
	private static int BASEVAL = 15;
	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		sort(arr, 0, arr.length - 1);
		return arr;
	}
	
	private <T extends Comparable<T>> void sort(T[] arr, int l, int r) {
		// 在数据个数低于600个的时候, 采用希尔排序
		if (r - l <= BASEVAL) {
//			new ShellSort().sortOriginal(arr, l, r);
			new InsertionSort().sortOriginal(arr, l, r);
			return;
		}
		
		// 获取[l, r]区间随机的一个元素的索引并将其放在第一个位置
		int randomIndex = (int)(Math.random() * (r - l) + l);
		Swap.swap(arr, l, randomIndex);
		
		// 下面的排序逻辑不变, 依然是对第一个元素进行排序
		int littleIndex = l;
		
		for (int i = l + 1; i <= r; i++) {
			
			if (arr[i].compareTo(arr[l]) < 0) {
				Swap.swap(arr, i, ++littleIndex);
			}
			
		}
		
		int insertIndex = littleIndex;
		Swap.swap(arr, insertIndex, l);
		
		sort(arr, l, insertIndex - 1);
		sort(arr, insertIndex + 1, r);
		
	};
	
}

/**
 * 随即快速排序元素的弊端:
 * 			<1> 因为我们在进行快速排序的过程中, 其实是将大于等于该元素的值放在右边, 小于等于该元素的值放在左边, 那么一旦出现
 * 				 大量的重复元素, 比如区间设置为10以内的时候, 比其小的值的个数很大概率会远远少于比其大的值的个数加上等于其值的个数,
 * 					这样在左右进行快排的时候仍然很大概率会退化成链表的递归形式, 此时也会造成栈溢出的异常
 * 优化方式: (二路快速排序)
 * 			因为是所有相等的元素都聚集在了右边, 所以导致右边的递归深度很深, 所以优化的方式就是将相等的元素分布在两旁
 * 			通过一个布尔类型来进行判断, true的时候为左边插入, false的时候放在右边, 然后每次遇到相同的元素的时候都对布尔值进行取反,
 * 			这样就能够达到左右均衡了
 * @author 
 */
class QuickSortByTwoWays implements SortInterface{
	
	private static int BASEVAL = 15;
	private boolean leftInsert = false;
	
	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		sort(arr, 0, arr.length - 1);
		return arr;
	}
	
	private <T extends Comparable<T>> void sort(T[] arr, int l, int r) {
		// 在数据个数低于600个的时候, 采用希尔排序
		if ((r - l) <= BASEVAL) {
//			new ShellSort().sortOriginal(arr, l, r);
			new InsertionSort().sortOriginal(arr, l, r);
			return;
		}
		
		// 获取[l, r]区间随机的一个元素的索引并将其放在第一个位置
		int randomIndex = (int)(Math.random() * (r - l) + l);
		Swap.swap(arr, l, randomIndex);
		
		// 下面的排序逻辑不变, 依然是对第一个元素进行排序
		int littleIndex = l;
		
		for (int i = l + 1; i <= r; i++) {
			
			
			if (arr[i].compareTo(arr[l]) < 0 || (arr[i].compareTo(arr[l]) == 0 && leftInsert)) {
				Swap.swap(arr, i, ++littleIndex);
			}
			
			if (arr[i].compareTo(arr[l]) == 0) {
				leftInsert = !leftInsert;
			}
			
		}
		
		int insertIndex = littleIndex;
		Swap.swap(arr, insertIndex, l);
		
		sort(arr, l, insertIndex - 1);
		sort(arr, insertIndex + 1, r);
		
	};
	
}

/**
 * 二路快速排序的弊端: 其对于相等的元素平均分布在两边, 那么这些元素仍然需要被继续排序, 冗余
 * 
 * 三路快速排序第一种实现: 
 * 将元素分为三个部分, 小于V, 等于V, 大于V, leftIndex指向小于V的所有元素的最后一个元素, equalIndex指向等于V的最后一个元素
 * 变量的维护:
 * 		<1> 当遇到小于V的情况时, 先将该元素与leftIndex后一位元素进行位置交换, 此时还需要判断这个元素是否时等于V
 * 		<2> 如果等于V, 此时需要判断equalIndex是否在littleIndex的后面, 如果不在, 说明其还指向初始的位置, 也说明
 * 			 该元素是第一个等于V的元素, 此时将equalIndex移动到littleIndex位置, 然后对equalIndex + 1位置的元素与当前元素
 * 			替换位置
 * 			如果equalIndex在littleIndex后面了, 那就直接将equalIndex + 1位置的元素与当前元素替换就好了
 * 		<3> 如果遇到大于V的情况, 则什么都不做
 * 注意: 我们需要将通常情况先写出来, 假设已经分为了三块区域, <V =V >V  三块, 然后将遍历元素时对大于等于小于V的通常情况进行编写
 * 		最后再对特殊情况进行编写, 比如5 4 5这种情况, 当遍历到第二个5的时候, equalIndex还指向初始位置, 此时需要将其调整到littleIndex的位置, 
 * 		然后再执行交换操作
 * 
 * @author 
 */
class QuickSortByThreeWays1 implements SortInterface {
	
	private static int BASEVAL = 15;
	
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		sort(arr, 0, arr.length - 1);
		return arr;
	}
	
	private <T extends Comparable<T>> void sort(T[] arr, int l, int r) {
		// 在数据个数低于600个的时候, 采用希尔排序
		if ((r - l) <= BASEVAL) {
//			new ShellSort().sortOriginal(arr, l, r);
			new InsertionSort().sortOriginal(arr, l, r);
			return;
		}
		
		// 获取[l, r]区间随机的一个元素的索引并将其放在第一个位置
		int randomIndex = (int)(Math.random() * (r - l) + l);
		Swap.swap(arr, l, randomIndex);
		
		// 对第一个元素进行快速排序
		int littleIndex = l; // 最后一个小于V的位置
		int equalIndex = l; // 最后一个等于V的位置
		
		for (int i = l + 1; i <= r; i++) {
			
			// 小于当前元素
			if (arr[i].compareTo(arr[l]) < 0) {
				Swap.swap(arr, i, ++littleIndex);
			} 
			
			// 等于当前的元素(小于之后还要再考虑一次这个元素是否等于V)
			if (arr[i].compareTo(arr[l]) == 0) {
				// 此时需要判断equalIndex是否还指向初始位置
				if (equalIndex <= littleIndex) {
					equalIndex = littleIndex;
				}
				Swap.swap(arr, i, ++equalIndex);
			}
			
		}
		
		int insertIndex = littleIndex;
		// 对右边进行遍历前, 需要考虑没有出现相同的元素的情况, 那么此时equalIndex仍然是指向第一个元素的, 此时需要将其调整到littleIndex位置
		// 这样右边的遍历才能够以equalIndex + 1的位置开始
		equalIndex = equalIndex > littleIndex ? equalIndex : littleIndex;

		Swap.swap(arr, insertIndex, l);
		
		sort(arr, l, insertIndex - 1);
		sort(arr, equalIndex + 1, r);
		
	};
	
}

/**
 * 三路快速排序第二种实现: 从左向右查找, 当小于key的时候, 与left + 1的值进行位置交换, 当等于key的时候, 直接比较下一个, 当大于key的时候, 将其与
 * 					right - 1的值进行位置交换
 * 变量的维护: 
 * 			left: l  ---> [l + 1, left] < key
 * 			right: r + 1 ---> [right, r] > key
 * 			i: l + 1 ---> [left + 1, i) == key
 * 初始的时候这些值对应的范围都是空的
 * 
 * @author zhongshenglong
 *
 */
class QuickSortByThreeWays2 implements SortInterface{
	private static int BASEVAL = 15;
	
	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		arr = Arrays.copyOf(arr, arr.length);
		
		sort(arr, 0, arr.length - 1);
		
		return arr;
	}
	
	// 对arr闭区间[l, r]部分进行快速排序, 递归算法
	private <T extends Comparable<T>> void sort (T[] arr, int l, int r) {
		
		if (r - l <= BASEVAL) {
			new InsertionSort().sortOriginal(arr, l, r);
			return;
		}
		
		
		// 获取[l, r]区间随机的一个元素的索引并将其放在第一个位置
		int randomIndex = (int)(Math.random() * (r - l) + l);
		Swap.swap(arr, l, randomIndex);
		
		// 获取被排序的元素
		T key = arr[l];
		
		int left = l; // [l + 1, left] < key
		int right = r + 1; // [right, r] > key
		int i = l + 1; // [left + 1, i) == key, 这个i不能为闭区间, 因为在循环结束时(i < right), i是指向第一个比其大的元素的, 或者指向空
		while (i < right) {
			
			// 从左往右遍历一直到right为止
			if (arr[i].compareTo(key) < 0) {

				// 如果比key小, 那么就将其放在left的下一个位置, 同时维护i和left
				Swap.swap(arr, ++left, i++);
			} else if (arr[i].compareTo(key) > 0) {
				
				// 如果比key大, 则放在right的前一个位置
				Swap.swap(arr, --right, i);
			} else {
				
				// 如果和key相等, 那么直接考虑下一个元素就好了
				i++;
			}
			
		}
		
		// 循环结束, [l + 1, left] < key   [left + 1, i) == key   [right, r] > key;
		Swap.swap(arr, left, l);
		
		// 继续递归下去, 但是需要先判断是否存在这个区间
		sort(arr, l, left - 1);
		
		sort(arr, right, r);
	}
	
}