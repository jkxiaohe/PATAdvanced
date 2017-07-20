package pat5;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author gljg
 * 题意解析：接收用户输入的N个数字，指定大小为M,使用给定的数字组合使其和为M
 * 如果这样的解不唯一，应该输出最小的序列
 */
public class Main2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[] coins = new int[n];
		for(int i=0;i<n;i++)
			coins[i] = in.nextInt();
		//对用户输入的数据按照升序进行排列，原因：如果找到和为M的解，那么必定是最小的解
		Arrays.sort(coins);  
		int[] result = new int[n];  //result用来存储结果集中的每个数字
		//find函数用来寻找满足的最小序列，并返回result中存储的结果的数字的个数
		int j = find(coins,0,result,0,m);
		if(j<0){
			System.out.println("No Solution");
		}else{
			for(int i=0;i<j;i++)
				System.out.print(result[i] + " ");
			System.out.println(result[j]);
		}
	}
	
	/**
	 * 核心实现方法
	 * 函数接收了5个参数：int[] coins : 代表原始数据的数字，里面存放有用户输入的所有数字；
	 *             start : coins数组当前从第几个数字开始查找，本方法要用到递归实现，每次从coins开始查找的数字不同，所以需要用start来标记；
	 *             int[] result : result数组用来存放最后的结果，由于最后的结果可能是一个数字序列，所以用数组来存放多个数字；
	 *             int j : 标记result数组已经存放了几个数字了，下次存放时应该从第几个位置开始存放；
	 *             int m : M为最后的和，刚开始时，result中一个数字都没有，为M;每存放进一个数字，M-该数字，代表剩余的和是多少；
	 * 关于for(int i=start;i<coins.length;i++)循环的解析：
	 *       每次查找的时候，从start位置开始，而不是0，因为递归查找的时候，上一个位置已经查找过了，要从下一个位置开始，start代表了还未查找的下一个数字；
	 *       i<coins.length;  最大查找的长度也不能够超过用户输入的数据总和；
	 * 3个if循环条件的判断作用：
	 *       (1)当前数字<M,将它放入到result的第一个位置，再继续调用find函数去查找后面的数字是否满足，在递归的时候，i+1代表指向了coins的下一个数字，j+1说明result第一个位置
	 *       已经存有数字，下次找到满足要求的数字从j+1位置存放，m-coins[i]表示还需要和为多少才能满足要求；
	 *       (2)coins[i]==m ，说明此时result数组中存放的所有的数字和与M相等了，此时该组解是满足要求的，将该位置的数字放入result数组后，返回j的值，此时会返回到
	 *       上一个递归过程中，在生一个递归过程中，flag用来接收返回的值，即为j+1的值，c此时flag != j为真，返回标记flag,flag代表了result数组中存放的
	 *       最后一个数字的下标位置；
	 *       (3)该组数字无法满足要求，返回的是j-1,到上一个递归过程中，flag即为j,此时result[j]=0,说明该位置及其以后的数字是无法满足和为M的要求的，进入到for循环中，
	 *       i++,继续开始下一个数字的查找
	 */
	private static int find(int[] coins,int start,int[] result,int j,int m){
		for(int i=start;i<coins.length;i++){
			if(coins[i]<m){
				result[j] = coins[i];
				int flag = find(coins,i+1,result,j+1,m-coins[i]);
				if(flag != j)
					return flag;
				result[j] = 0;
			}else if(coins[i] == m){
				result[j] = coins[i];
				return j;
			}else{
				return j-1;
			}
		}
		return j-1;
	}
	
}
