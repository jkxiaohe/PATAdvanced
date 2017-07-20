package pat4;

import java.math.BigInteger;
import java.util.Scanner;

public class Main7 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		boolean[] result = new boolean[n];
		for(int i=0;i<n;i++){
			BigInteger n1 = new BigInteger(in.next());
			BigInteger n2 = new BigInteger(in.next());
			BigInteger n3 = new BigInteger(in.next());
			if(n1.add(n2).compareTo(n3) > 0){
				result[i] = true;
			}else{
				result[i] = false;
			}
		}
		for(int i=1;i<=n;i++){
			System.out.println("Case #"+i+": "+result[i-1]);
		}
	}
	
}
