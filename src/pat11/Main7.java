package pat11;

import java.util.*;

/**
 * 1061
 * 思路：插入排序：先让前面的数有序，后面的数挨个插入前面有序的序列中；
 *    归并排序：所有的数字分为2组，各自进行排序，然后再归并为1组，和另1组归并的再进行排序；
 *    
 */
public class Main7 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		int i;
		for(i=0;i<n;++i){
			a[i] = in.nextInt();
		}
		for(i=0;i<n;++i){
			b[i] = in.nextInt();
		}
		
		//找出有序序列之后的第一个无序数字的下标，找到后退出，index之前的序列都是有序的，而index(包括)之后都是无序的
		int index = 0;
		for(i=0;i<n;++i){
			if(b[i]>b[i+1]){
				index = i+1;
				break;
			}
		}
		
		//判断是否是插入排序，如果是插入排序，那么只有前index个数字是有序的，后面的数字和原数组是一样的
		boolean flag = true;
		for(i=index;i<n;++i){
			if(a[i]!=b[i]){
				flag = false;
				break;
			}
		}
		
		if(flag){
			System.out.println("Insertion Sort");
			for(int j=index;j>0;--j){
				if(b[j]<b[j-1]){
					int temp = b[j];
					b[j] = b[j-1];
					b[j-1] = temp;
				}
			}
		}else{
			//如果是归并，那么当前每个子序列的大小为Index，下一轮比较时应为index*2
			System.out.println("Merge Sort");
			index = 2*index;
			for(int j=0;j<n;j+=index){
				int next = j+index>n ? n : j+index;
				Arrays.sort(b,j,next);
			}
		}
		
		for(i=0;i<n-1;++i){
			System.out.print(b[i] + " ");
		}
		System.out.println(b[i]);
		
	}
	
}
