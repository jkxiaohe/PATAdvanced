package pat10;

import java.util.Scanner;

//1055
public class Main7 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int types = in.nextInt();
		int demand = in.nextInt();
		int[] stocks = new int[types];
		int[] total = new int[types];
		float[] unit = new float[types];
		for(int i=0;i<types;i++){
			stocks[i] = in.nextInt();
		}
		for(int i=0;i<types;i++){
			total[i] = in.nextInt();
			unit[i] = (float)total[i]/stocks[i];
		}
		
		float profit = 0;;
		while(types-- != 0){
			int index = findMax(unit);
			if(demand >= stocks[index]){
				profit += total[index];
				demand -= stocks[index];
			}else{
				profit += (float)demand/stocks[index] * total[index];
				demand = 0;
			}
		}
        
		System.out.printf("%.2f",profit);
		
	}
	
	private static int findMax(float[] tmp){
		int max = 0;
		for(int i=1;i<tmp.length;i++){
			if(tmp[i] > tmp[max]){
				max = i;
			}
		}
		tmp[max] = 0;
		return max;
	}
	
	
}
