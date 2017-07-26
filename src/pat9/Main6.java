package pat9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 1049
 * 题意解析：根据用户输入的数字的片段，将这些数字片段进行组合，使得组合后的数字最小
 *       思路：将每个片段作为1个单位，这些有限个单位的排列组合是有限的，只要求出按序求出最小的排列的那个即可
 */
public class Main6 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String[] num = new String[n];
		for(int i=0;i<n;i++){
			num[i] = in.next();
		}
		
		/**
		 * 在这里使用了Arrays的比较方法，并传入了指定的比较器，对所有的2个片段进行比较，只要满足a+b<b+a,那么说明a应排在b的前面，
		 * 使用这种方法对所有的数字片段进行比较，当最后的数字片段无法再进行排序时，说明此时任何组合的方式都是最小的了
		 */
		Arrays.sort(num , new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return (o1+o2).compareTo(o2+o1);
			}
		});
		
		StringBuilder ans = new StringBuilder();
		for(int i=0;i<n;i++){
			ans.append(num[i]);
		}
		
		//首位的0不输出，进行判断
		int len = ans.length();
		int pos = -1;
		for(int i=0;i<len;i++){
			if(ans.charAt(i) != '0'){
				pos = i;
				break;
			}
		}
		
		if(pos < 0){
			System.out.println(0);
		}
		else{
			String res = ans.toString().substring(pos);
			System.out.println(res);
			
/*			for(int i=pos;i<len;i++){
				System.out.print(ans.charAt(i));
			}
			System.out.println();*/
		}
		
	}
	
}
