import java.util.Arrays;

/**
 	Description: 
 			Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 			
 			
 	Example:
		Input:
		nums1 = [1,2,3,0,0,0], m = 3
		nums2 = [2,5,6],       n = 3		
		
		Output: [1,2,2,3,5,6]
 
 
 */
public class leetcode_088_MergeSortedArray {
	public static void main(String[] args) {
		int[] nums1 = new int[] {1,2,3,0,0,0};
		int[] nums2 = new int[] {2,5,6};
		
		merge(nums1, 3, nums2, 3);
	}

	 public static void merge(int[] nums1, int m, int[] nums2, int n) {
		 
		 int[] mergeArr = new int[m + n];
		 int l = 0, r= 0, i = 0;
		 
		 while (l < m && r < n) {
			 if (nums1[l] < nums2[r]) {
				 mergeArr[i++] = nums1[l++];
				 continue;
			 }
			 mergeArr[i++] = nums2[r++];
		 }
		 
		 while (l < m) {
			 mergeArr[i++] = nums1[l++];
		 }
	 
		 while (r < n) {
			 mergeArr[i++] = nums2[r++];
		 }
		 
		 for (int j = 0; j < mergeArr.length; j++) {
			 nums1[j] = mergeArr[j];
		 }
		 System.out.println(Arrays.toString(nums1));
    }
}
