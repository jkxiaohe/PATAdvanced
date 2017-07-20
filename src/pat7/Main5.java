package pat7;

import java.util.*;

public class Main5 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		for(int i=0;i<K;++i){
			list.add(new ArrayList<String>());
		}
		
		for(int i=0;i<N;++i){
			String name = in.next();
			int num = in.nextInt();
			for(int j=0;j<num;++j){
				int c = in.nextInt();
				list.get(c-1).add(name);
			}
		}
		
		for(int i=0;i<K;++i){
			Collections.sort(list.get(i));
			int m = list.get(i).size();
			System.out.println(i+1 + " " + m);
			for(int j=0;j<m;++j){
				System.out.println(list.get(i).get(j));
			}
		}
		
	}
	
}
