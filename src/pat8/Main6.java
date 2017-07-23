package pat8;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main6 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int col = in.nextInt();
		int row = in.nextInt();
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		int k;
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				k = in.nextInt();
				map.put(k,map.get(k)!=null ? map.get(k)+1 : 1);
			}
		}
		
		Iterator<Entry<Integer, Integer>> it = map.entrySet().iterator();
		int half = row * col / 2;
		while(it.hasNext()){
			Entry<Integer,Integer> en = it.next();
			if(en.getValue() > half){
				System.out.println(en.getKey());
				return;
			}
		}
		
		
	}
	
}
