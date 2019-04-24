package Heap;

import java.util.Arrays;
import java.util.Random;

import sort.SortInterface;

public class MinHeap<T extends Comparable<T>> implements SortInterface {
	private int capacity;
	private int size;
	private T[] heap;

	
	@SuppressWarnings("unchecked")
	public MinHeap (int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.heap = (T[])new Comparable[capacity];
	}
	
	public MinHeap () {}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E extends Comparable<E>> E[] sort(E[] arr) {
		heap = (T[])Arrays.copyOf(arr, arr.length);
		size = heap.length;
		int index = ((heap.length - 1) - 1) / 2;
		while (index >= 0) {
			siftDown(index--);
		}
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (E)extractMin();
		}
		
		return arr;
	}

	public void insert (T val) {
		if (size >= capacity) {
			throw new IllegalArgumentException("heap is full!");
		}
		
		heap[size++] = val;
		siftUp(size - 1);
	}
	
	public T extractMin () {
		
		T min = heap[0];
		swap(0, size - 1);
		size--;
		
		siftDown(0);
	
		return min;
	}
	
	private void siftDown (int i) {
		
		while (i < size) {
			int index = getMinChildIndex(i);
			
			if (index == -1 || heap[i].compareTo(heap[index]) < 0) {
				break;
			}
			
			swap(i, index);
			i = index;
		}
		
	}
	
	private int getMinChildIndex (int i) {
		if (i >= size) {
			return -1;
		}
		
		int l = i * 2 + 1;
		int r = i * 2 + 2;
		
		if (l >= size) {
			return -1;
		}
		
		if (r >= size) {
			return l;
		}
		
		return heap[l].compareTo(heap[r]) > 0 ? r : l;
	}
	
	
	private void siftUp (int i) {
		
		int parent = (i - 1) / 2;
		
		while (i > 0 && heap[parent].compareTo(heap[i]) > 0) {
			swap(i, parent);
			i = parent;
			parent = (i - 1) / 2;
		}
		
	}
	
	private void swap (int i, int j) {
		if (i >= size || j >= size) {
			throw new IllegalArgumentException("index out of bound");
		}
		T temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	public int getSizde () {
		return size;
	}
	
	public boolean isEmpty () {
		return size == 0;
	}
}
