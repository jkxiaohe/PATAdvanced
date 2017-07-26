package pat9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main7 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		in.nextInt();
		String[] nums = in.nextLine().split(" ");
		StringBuilder res = new StringBuilder();
		Comparator<String> cmp = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return (o1+o2).compareTo(o2+o1);
			}
		};
		Arrays.sort(nums,cmp);
		for(String n:nums){
			res.append(n);
		}
		int i=0;
		while(res.charAt(i) == '0' && i<res.length()-1)
			i++;
		System.out.println(res.substring(i));
		
	}
	
}
