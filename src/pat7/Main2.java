package pat7;

import java.util.Scanner;

/**
 *题意分析：第一行输入N代表节点数，后边输出这N个节点组成的圆圈之间的距离
 *    M代表要求几组点之间的最短距离，
 *    接下来M行分别给出起始点和重点,求出每组点之间的最短距离
 */
public class Main2 {

	public static void main(String[] args) {
		int[] sum = new int[100001];  //N的范围是[3,10^5],所以数组只要比100001大就好了
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();  //N代表圆环上的地点数目
		//循环接收每2个地点之间的距离，在这里使用sum+=累加上一个节点之间的距离，即sum后的每一个点事前面所有距离之和
		for(int i=1;i<=n;++i){
			int x = in.nextInt();
			sum[i] = x + sum[i-1];
		}
		
		//对用户输入的m组数据分别进行判断
		int m = in.nextInt();
		for(;m != 0;--m){
			int x = in.nextInt();
			int y = in.nextInt();
			int temp = Math.abs(sum[x-1] - sum[y-1]);
			int temp2 = sum[n] - temp;
			System.out.println(temp < temp2 ? temp : temp2);
		}
	}
	
}
