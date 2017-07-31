package pat11;

import java.util.Arrays;
import java.util.Scanner;

public class Main5 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] init = new int[N];
		int[] part = new int[N];
		for(int i=0;i<N;i++){
			init[i] = in.nextInt();
		}
		for(int i=0;i<N;i++){
			part[i] = in.nextInt();
		}
		
		//插入排序的判断
		int[] tmp = Arrays.copyOf(init, N);
		boolean flag1 = insertSort(tmp,part,N);
		if(flag1){
			System.out.println("Insertion Sort");
			print(tmp);
			return;
		}
		
		//归并排序
		int[] tmp2 = Arrays.copyOf(init, N);
		boolean flag2 = mergeSort(tmp2,part,0,N-1);
		if(flag2){
			System.out.println("Merge Sort");
			print(tmp2);
		}
		
	}
	
	static boolean mergeSort(int[] a,int[] b,int start,int end){
		boolean flag = false;
		if(start < end){
			int mid = (start+end)/2;
			mergeSort(a,b,start,mid);
			mergeSort(a,b,mid+1,end);
			merge(a,start,mid,end);
			if(flag)
				return true;
			if(Arrays.equals(a, b))
				flag = true;
		}
		return true;
	}
	
	static void merge(int[] a,int start,int mid,int end){
		int[] tmp = new int[a.length];
//		int i=0,j=0,k=start;
		int i = start;
		int j = mid+1;
		int k = start;
		while(i<=mid && j<=end){
			if(a[i] < a[j]){
				tmp[k++] = a[i++];
			}else{
				tmp[k++] = a[j++];
			}
		}
		while(i<=mid)
			tmp[k++] = a[i++];
		while(j<=mid)
			tmp[k++] = a[j++];
		for(i=start;i<=end;i++)
			a[i] = tmp[i];
	}
	
	static boolean insertSort(int[] a,int[] b,int N){
		boolean flag = false;
		int i=0,j=0;
		for(i=1;i<N;i++){
			int tmp = a[i];
			if(a[i] < a[i-1]){
				for(j=i-1;j>=0 && tmp <a[j];j--){
					a[j+1] = a[j];
				}
				a[j+1] = tmp;
			}
			if(flag)
				return true;
			if(Arrays.equals(a, b)){
				flag = true;
			}
		}
		return false;
	}
	
	static void print(int[] a){
		System.out.print(a[0]);
		for(int i=1;i<a.length;i++){
			System.out.print(" " + a[i]);
		}
	}
	
}
