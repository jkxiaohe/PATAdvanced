package pat10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

//1052
public class Main6 {

	static int n;
	static int l;
	static int h;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer;
		
		tokenizer = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(tokenizer.nextToken());
		l = Integer.parseInt(tokenizer.nextToken());
		h = Integer.parseInt(tokenizer.nextToken());
		List<Man> list = new ArrayList<Man>();
		
		int id,virtue,talent;
		for(int i=0;i != n;i++){
			tokenizer = new StringTokenizer(reader.readLine());
			id = Integer.parseInt(tokenizer.nextToken());
			virtue = Integer.parseInt(tokenizer.nextToken());
			talent = Integer.parseInt(tokenizer.nextToken());
			if(virtue >= l && talent >= l){
				list.add(new Man(id,virtue,talent));
			}
		}
		
		Collections.sort(list);
		
		System.out.println(list.size());
		for(Man man : list){
			System.out.println(man);
		}
		
		
	}
	
	public static class Man implements Comparable<Man>{
		int id;
		int virtue;
		int talent;
		int total;
		int stage;
		
		public Man(int id,int virtue,int talent){
			this.id = id;
			this.virtue = virtue;
			this.talent = talent;
			total = virtue + talent;
			stage = updateStage();
		}
		
		public int updateStage(){
			if(virtue >= h){
				return talent >= h ? 1 : 2;
			}else{
				return virtue >= talent ? 3 : 4;
			}
		}

		/**
		 * 分批进行比较：首先根据每个人的等级进行比较，如果等级能够比较出2个人的顺序，直接依据等级比较的结果作为返回值；
		 *         如果这2个人的等级是相同的，在等级比较不出的情况下，根据总分进行比较，返回总分比较的结果；
		 *         如果总分依旧相同，无法得出结果，那么依据2个人的德分进行比较；
		 *         最后在上述3个条件都比较无果的情况下，根据编号id进行比较；
		 *    总之，比较的因素按照优先级依次降低，高优先级的比较因子放在最前面。
		 */
		
		@Override
		public int compareTo(Man o) {
			if(stage != o.stage){
				return stage - o.stage;
			}else if(total != o.total){
				return o.total - total;
			}else if(virtue != o.virtue){
				return o.virtue - virtue;
			}
			return id - o.id;
		}
		
		/**
		 * 重写该函数的输出形式，在最后输出结果的时候，会直接按照toString中的方法输出该类对象
		 */
		@Override
		public String toString() {
			return String.format("%d %d %d", id,virtue,talent);
		}
		
	}
	
}
