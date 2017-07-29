package pat11;

import java.util.*;

/**
 * 1059
 * 题目解析：求解该数字序列中最长的满足Max<=min*p的数字长度。
 * 思路：按照解体报告中的方法，设置2个变量i,j,分别从拍好序的数组的首个开始，循环向后移动；
 *    i为结果序列的头,j为结果序列的尾，i从0开始，循环增大；对于每个i,j++向后走，并判断a[j]<=p*a[i]是否满足；
 *    当不满足时退出j的循环，此时j只想了满足条件的序列的最后的位置，j-i 既是满足条件的序列的最大长度；
 *    由 a[j]<=a[i]*p<=a[i+1]*p,可得：
 *        即便此时i向后继续移动，也仍然会满足条件，但是序列的最大长度  (j-i) 却短了，所以即便向后移动，也能保证answer的值为j不动时的最大值；
 *    所以，对于每次i的递增，判断j此时是否满足 a[j]/a[i] <= p 的条件，如果满足继续递增j,知道不满足时退出，再判断当前序列
 *  的最大长度  (j-i) 和answer 的大小关系，选择最大的那个序列；
 *     解体报告中的为  (a[j]+a[i]-1)/a[i] <= p , 是因为：另外注意，判断p倍可能超int，所以建议用除法（上取整）来判断倍数关系。
 */
public class Main2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int answer = 1;
		int[] a = new int[n];
		for(int i=0;i<n;++i){
			a[i] = in.nextInt();
		}
		in.close();
		
		Arrays.sort(a, 0, n);
        
		for(int i=0,j=0;(i<n) && (j<n);++i){
/*			for(;(j<n) && ((a[j]+a[i]-1)/a[i]<=m);++j)
				;*/
			for(;(j<n) && ((a[j]+a[i]-1)/a[i]<=m);++j)
				;
			
			answer = Math.max(answer, j-i);
		}
		System.out.println(answer);
		
	}
	
}
