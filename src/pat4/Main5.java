package pat4;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 
 * @author dell
 *题意解析：计算任意2个集合的相似度，公式为： 集合的交集元素/集合的并集元素*100
 */
public class Main5 {

	public static void main(String[] args) {
		//用户在第一行会输入集合set总个数，这里可以使用HashSet数组来存储每个set集合对象，Set集合的特点是无序不可重复，它会自动将重复的元素去除，
		//满足题意的要求，即在并集和交集中都要将重复的元素去除掉(distinct numbers)
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		HashSet[] sets = new HashSet[n];
		for(int i=0;i<n;i++){
			int m = in.nextInt();
			sets[i] = new HashSet<Integer>(m);
			String[] nums = in.nextLine().split(" ");
			//nextLine()的特点接收当前行的所有数据，不论有没有对当前行的数据读取过，所以要忽略第一个数字
			for(int j=1;j<=m;j++){
				sets[i].add(Integer.parseInt(nums[j]));
			}
		}
		//题目的要求是：对于每一行的查询，都应该单独输出一行相似性的结果,所以这里直接在每个循环里输出本次查询的结果，不再将结果集中输出
		//并且，用户输出的每一行结果必须单独占据一行，所以需要在格式化输出后面加\n,应为printf不会像println那样自动输出换行
		int k = in.nextInt();
		for(int i=0;i<k;i++){
			int s = in.nextInt()-1;
			int e = in.nextInt()-1;
			HashSet<Integer> s1 = sets[s];
			HashSet<Integer> s2 = sets[e];
			int comm = 0;
			for(Integer integer : s2){
				if(s1.contains(integer))
					comm++;
			}
			double diff = s1.size() + s2.size() - comm;
			System.out.printf("%.1f%%\n" , comm/diff*100);
		}
		
	}
	
}
