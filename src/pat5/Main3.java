package pat5;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] words = in.nextLine().toLowerCase().split("[^a-zA-Z0-9]+");
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0;i<words.length;i++){
			Integer count = map.get(words[i]);
			count = count == null ? 0 : count;
			map.put(words[i], count+1);
		}
		int max = 0;
		String result = null;
		for(Entry<String,Integer> entry : map.entrySet()){
			if(entry.getValue() > max){
				max = entry.getValue();
				result = entry.getKey();
			}else if(entry.getValue() == max){
				result = result.compareTo(entry.getKey()) < 0 ? result : entry.getKey();
			}
		}
		System.out.println(result + " " + max);
		
	}
	
}
