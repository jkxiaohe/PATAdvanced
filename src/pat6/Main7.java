package pat6;

import java.util.Scanner;

public class Main7 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int sum = in.nextInt();
		int[] nums = new int[n];
		for(int i=0;i<n;i++){
			nums[i] = in.nextInt();
		}
		
		boolean equal = false;  //判断存不存在相等
		int minSum = 0;  //如果不存在相等的值，记录最小的值
		StringBuilder sb1 = new StringBuilder();  //存放所有相等的下标
		String s = null;  //存放最小的那个记录值
		//从第一个数字开始，循环遍历到结束
		for(int i=0;i<n;i++){
			int res = 0;  //res存放每次遍历过程中的数字和
			for(int j=i;j<n;j++){  //向后查询数字和，直到找到一个合适的位置
				res += nums[j];
				//有2中情况：==sum；比sum大的最小值
				if(res == sum){
					equal = true;
					sb1.append((i+1) + "-" + (j+1) + "\n");
					break;
				}
				if(res > sum && !equal){
					if(minSum > res)
						s = new String((i+1) + "-" + (j+1));
				}
				if(res > sum)  //及时退出循环，避免做无谓的判断
					break;
			}
		}
		
		if(equal){
			System.out.print(sb1.toString());
		}else{
			System.out.println(s);
		}
		
	}
	
}
