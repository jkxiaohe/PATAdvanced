package pat2;

import java.util.Scanner;

public class Main8 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] source = new int[n];
		int[] sorted = new int[n];
		
		for(int i=0;i<n;i++)
			source[i] = in.nextInt();
		for(int i=0;i<n;i++)
			sorted[i] = in.nextInt();
		
		int sortedLength = 0;
		boolean isInsertionSort = true;
		
		for(int i=1;i<n;i++){
			if(sorted[i] < sorted[i-1]){
				sortedLength = i;
				break;
			}
		}
		for(int i=sortedLength;i<n;i++){
			if(source[i] != sorted[i]){
				isInsertionSort = false;
				break;
			}
		}
		
		if(isInsertionSort){
			int tmp = sorted[sortedLength];
			int i = sortedLength;
			while(i>0 && tmp<=sorted[i-1]){
				sorted[i] = sorted[--i];
			}
			sorted[i] = tmp;
		}else{
			buildMaxHeap(source);
			int i = n-1;
			for(;i>0;i--){
				swap(source,0,i);
				maxHeapify(source, i, 0);
				boolean m = true;
				for(int j=0;j<n;j++){
					if(source[j] != sorted[j]){
						m = false;
						break;
					}
				}
				if(m)
					break;
			}
			swap(sorted,0,i-1);
			maxHeapify(sorted, i-1, 0);
		}
		
		System.out.println(isInsertionSort ? "Insertion Sort" : "Heap Sort");
		for(int i=0;i<n-1;i++)
			System.out.print(sorted[i] + " ");
		System.out.print(sorted[n-1]);
	}
	
	private static void buildMaxHeap(int[] array){
		int start = getParentIndex(array.length-1);
		for(int i=start;i>=0;i--){
			maxHeapify(array, array.length, i);
		}
	}
	
	private static void maxHeapify(int[] array,int heapSize,int index){
		int left = getLeftChildIndex(index);
		int right = getRightChildIndex(index);
		int largest = index;
		if(left < heapSize && array[left] > array[largest])
			largest = left;
		if(right < heapSize && array[right] > array[largest])
			largest = right;
		if(largest != index){
			swap(array,index,largest);
			maxHeapify(array,heapSize,largest);
		}
	}
	
	private static void swap(int[] array,int i,int j){
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	private static int getParentIndex(int index){
		return (index-1) >> 1;
	}
	
	private static int getLeftChildIndex(int index){
		return (index << 1) +1;
	}
	
	private static int getRightChildIndex(int index){
		return (index << 1) +2;
	}
}
