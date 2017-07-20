package pat3;

import java.io.BufferedInputStream;
import java.util.*;

public class Main7 {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedInputStream(System.in));
		
		int numP = in.nextInt();
		int numG = in.nextInt();
		
		int[] weight = new int[numP];
		for(int i=0;i<numP;i++){
			weight[i] = in.nextInt();
		}
		
		Queue<IntegerPair> queue1 = new LinkedList<>();
		Queue<IntegerPair> queue2 = new LinkedList<>();
		for(int i=0;i<numP;i++){
			int id = in.nextInt();
			queue1.offer(new IntegerPair(i, weight[id]));
		}
		
		int count = numG;
		List<IntegerPair> out = new ArrayList<>();
		
		int[] ranks = new int[numP];
		
		while(!queue1.isEmpty() || !queue2.isEmpty()){
			if(queue1.size() == 1){
				IntegerPair pair = queue1.poll();
				ranks[pair.id] = 1;
				break;
			}
			while(!queue1.isEmpty()){
				IntegerPair max = queue1.poll();
				for(int i=1;i<numG;i++){
					IntegerPair temp = queue1.poll();
					if(temp == null) break;
					if(max.weight >= temp.weight){
						out.add(temp);
					}
					else{
						out.add(max);
						max = temp;
					}
				}
				queue2.offer(max);
			}
			
			int rank = queue2.size() + 1;
			for(IntegerPair integerPair : out){
				IntegerPair pair = queue2.poll();
				ranks[pair.id] = 1;
				break;
			}
			
			while(!queue2.isEmpty()){
				IntegerPair max = queue2.poll();
				for(int i=1;i<numG;i++){
					IntegerPair temp = queue2.poll();
					if(temp == null) break;
					if(max.weight >= temp.weight)
					{
						out.add(temp);
					}else{
						out.add(max);
						max = temp;
					}
				}
				queue1.offer(max);
			}
			
			rank = queue1.size() + 1;
			for(IntegerPair integerPair : out){
				ranks[integerPair.id] = rank;
			}
			out.clear();
		}
		
		for(int i=0;i<ranks.length;i++){
			System.out.print(ranks[i]);
			if(i != numP-1){
				System.out.println(" ");
			}
		}
		
	}
	
	private static class IntegerPair{
		Integer id,weight,rank;
		public IntegerPair(int i,int w){
			id = i;
			weight = w;
		}
	}
}
