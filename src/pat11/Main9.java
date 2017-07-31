package pat11;

import java.util.Scanner;

//1063
/**
 * 统计pat的个数，首先，A前面有几个P,意味着就有几个PA；
 *   T之前有几个PA,意味着就有几个PAT;
 *   所以可以分别统计P,PA,PAT的个数；
 *   每次遇到一个P，P++;
 *   当遇到一个A时，PA=PA+P,p当前字符A可以和前面的P个‘P’字符组成PA,Pa是前面的A和P所能组成的PA的个数；
 *   当遇到一个T时，PAT = PAT + PA,PA是T字符前可以和它组成的PAT的个数，PAT是该位置前面的T和PA所组成的个数。
 *   由于最后的结果可能很大，所以要对大数求余数，需要在每次pat增加的地方求余，否则pat的大小可能会越界。
 */
public class Main9 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[] data = in.nextLine().toCharArray();
		int p = 0;
		int pa = 0;
		int pat = 0;
		
		for(char c : data){
			if(c == 'P'){
				++p;
			}
			else if(c == 'A'){
				pa += p;
			}
			else{
				pat  += pa;
				pat %= 1000000007;
			}
		}
		
		System.out.println(pat);
	}
	
}
