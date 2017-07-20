package pat5;

import java.util.Scanner;

public class Main5 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N,M,K,ds;
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		ds = in.nextInt();
		int[][] dist = new int[1024][1024];
		for(int i=1;i<=N+M;i++){
			dist[i][i] = 0;
		}
		int src,des,tempDist;
		String str;
		for(int i=0;i<K;i++){
			str = in.next();
			if(str.charAt(0) == 'G'){
				src = Integer.parseInt(str.substring(1));
				src += N;
			}else{
				src = Integer.parseInt(str);
			}
			str = in.next();
			if(str.charAt(0) == 'G'){
				des = Integer.parseInt(str.substring(1));
				des += N;
			}else{
				des = Integer.parseInt(str);
			}
			tempDist = in.nextInt();
			dist[src][des] = tempDist < dist[src][des] ? tempDist : dist[src][des];
			dist[des][src] = dist[src][des];
		}
		
		int i,j;
		for(int k=1;k<=N+M;k++){
			for(i=1;i<=N+M;i++){
				for(j=1;j<=N+M;j++){
					if(dist[i][j] > (dist[i][k] + dist[k][j])){
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		
		boolean isFind = false,isOk;
		int minDist = 0,tempMinDist,index=0;
		int sumDist = 100000,tempSumDist;
		for(i=N+1;i<=N+M;i++){
			tempSumDist = 0;
			isOk = true;
			tempMinDist = 100000;
			for(j=1;j<=N;j++){
				if(dist[i][j] > ds){
					isOk = false;
					break;
				}else{
					tempSumDist += dist[i][j];
					if(dist[i][j] < tempMinDist){
						tempMinDist = dist[i][j];
					}
				}
			}
			
			if(isOk){
				if(tempMinDist > minDist){
					isFind = true;
					index = i;
					minDist = tempMinDist;
					sumDist = tempSumDist;
				}else if(tempMinDist == minDist && tempSumDist < sumDist){
					isFind = true;
					index = i;
					sumDist = tempSumDist;
				}
			}
		}
		
		
		if(isFind){
			double avg = sumDist*1.0/N;
			System.out.printf("G%d\n%d.0 %.1f\n",index-N,minDist,avg);
		}else{
			System.out.println("No Solution");
		}
		
	}
	
}
