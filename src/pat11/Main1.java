package pat11;

import java.util.Scanner;

//1058
public class Main1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s1 = in.nextLine().toUpperCase();
		String s2 = in.nextLine().toUpperCase();
		String s3 = "";
		
		int i,j;
		for(i=0,j=0;i<s1.length() && j<s2.length();){
			if(s1.charAt(i) != s2.charAt(j)){
				String t = s1.charAt(i)+"";
				if(!s3.contains(t))
					s3 += t;
				i++;
			}else{
				i++;
				j++;
			}
		}
		while(i<s1.length()){
			String t = s1.charAt(i)+"";
			if(!s3.contains(t))
				s3 += t;
			i++;
		}
		System.out.println(s3);
		
	}
	
}
