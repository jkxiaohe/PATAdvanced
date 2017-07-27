package pat10;

import java.util.Scanner;

/**
 * 1052
 * @author gljg
 * 对每个字符串分别判断即可
 *
 */
public class Main1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[] s1 = in.nextLine().toCharArray();
		char[] s2 = in.nextLine().toCharArray();
		char[] s3 = in.nextLine().toCharArray();
		char[] s4 = in.nextLine().toCharArray();
		
		String[] weeks = {"MON","TUE","WED","THU","FRI","SAT","SUN"};
		
		boolean first = true;
		for(int i=0;i<s1.length  && i<s2.length;i++){
			if(first && s1[i]==s2[i] && s1[i]>='A' && s1[i]<='Z'){
				System.out.printf("%s ",weeks[s1[i]-'A']);
				first = false;
			}
			else if(!first && s1[i]==s2[i] && s1[i]>='A' && s1[i]<='Z'){
				System.out.printf("%s",s1[i]-'A'+10);
				break;
			}
			else if(!first && s1[i]==s2[i] && s1[i]>='0' && s1[i]<='9'){
				System.out.printf("%02d",s1[i]-'0');
				break;
			}
		}
		
		for(int i=0,j=0;i<s3.length && j<s4.length;i++,j++){
			if(s3[i]==s4[j] && Character.isLetter(s3[i])){
				System.out.printf(":%02d",i);
				break;
			}
		}
		
	}
	
}
