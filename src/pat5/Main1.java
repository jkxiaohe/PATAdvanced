package pat5;

import java.util.*;

/**
 * 题意分析：用户首先输入一个数字N,其后的数字一定是(0,***,N-1),只是顺序不一致
 *       通过数字0和任意一个数字进行交换，最后时所有的数字升序排列，求所要交换的次数
 * 思路：当0在圈内时，需要(m-1)次的交换，m是圈内的数字个数；
 *    当0不在圈内时，需要(m+1)次，m为圈内的数字个数；
 *    对于那些不需要交换，即处在正确位置上的数字，是不需要进行交换的，他们不在圈内；
 */
public class Main1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] nums = new int[n];
		boolean[] visited = new boolean[n];
		for(int i=0;i<n;i++)
			nums[i] = in.nextInt();
		
		//圈内有0的元素个数
		int swaps = 0;  //统计交换的次数
		int index = 0;  //当前查询到的数字所在的下标
		visited[index] = true;  //该位置上的数据统计过后标记为true
		//从下标为0的位置开始查找，直到找到值0位置，在这个遍历过程中的所有数字都在圈0的范围内，当找到数字0时，退出循环，此时swaps的值正是圈内所有的数字m-1
		while(nums[index] != 0){   
			index = nums[index];
			visited[index] = true;
			swaps++;
		}
		
		//圈内无0环元素的查找
		/**
		 * 对于无0环的元素，可以从下标1开始查找，通过下标1处的值可以查询到所有非0环内的元素，
		 * 如果没有找完（非0圈有多个），那么就对所有的数字进行遍历
		 */
		for(int i=1;i<n;i++){
			//visited[i]=true 说明该数字在圈0以内已经被访问过了，
			//nums[i]==i 说明该位置上的值处于正确的状态，不在圈的范围之内
			//这2个条件都排除掉，剩余的是在圈0以内，并且位置不正确，需要交换的数字
			if(visited[i] == false && nums[i] != i){
				swaps++;     //每次查找的都是一个非0圈，次数为m+1
				index = i;    //指向圈内的下一个数字的地址，并标记为true,访问过了
				visited[index] = true;
				swaps++;
				//对圈内的数字一直往下遍历，直到找到正确位置上的正确值swaps++用来记录每次新查找的数字个数
				while(nums[index] != i){
					index = nums[index];
					visited[index] = true;
					swaps++;
				}
			}
		}
		System.out.println(swaps);
	}
	
}
