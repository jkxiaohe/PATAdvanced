package pat5;

import java.util.Scanner;

public class Main4 {

	public static final int NSIZE = 1012;
	public static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		int n,m,k,r,dist;
		int[][] distance = new int[NSIZE][NSIZE];
//		char[] p1 = new char[5];
//		char[] p2 = new char[5];
		String p1,p2;
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		r = in.nextInt();
		for(int i=0;i<n+m;i++){
			for(int j=0;j<n+m;j++){
				distance[i][j] = INF;
			}
			distance[i][i] = 0;
		}
		for(int i=0;i<k;i++){
//			p1 = in.next().toCharArray();
//			p2 = in.next().toCharArray();
			p1 = in.next();
			p2 = in.next();
			dist = in.nextInt();
			int a = parseNumber(p1,m);
			int b = parseNumber(p2,m);
			distance[a][b] = distance[b][a] = min(dist,distance[a][b]);
		}
		
		int minDist = INF,sumDist = 0,mi = -1;
		int cmDist,csDist,f = 1;
		int min,z;
		for(int i=0;i<m;i++){
			boolean[] flag = new boolean[NSIZE];
			while(true){
				min = INF;
				z = -1;
				for(int j=0;j<n+m;j++){
					if(!flag[j] && distance[i][j] != INF && distance[i][j]<min){
						min = distance[i][j];
						z = j;
					}
				}
				if(z == -1)
					break;
				flag[z] = true;
				for(int j=i+1;j<n+m;j++){
					if(!flag[j] && distance[j][z]!=INF && distance[i][j]>distance[i][z]+distance[j][z]){
						distance[i][j] = distance[i][z] + distance[j][z];
						distance[j][i] = distance[i][j];
					}
				}
			}
			cmDist = INF;
			csDist = 0;
			for(int j=m;j<n+m;j++){
				if(distance[i][j] == INF || distance[i][j] > r){
					f = 0;
					break;
				}
				if(f!=0 && (minDist == INF || cmDist > minDist || (cmDist == minDist && csDist < sumDist))){
					mi = i;
					minDist = cmDist;
					sumDist = csDist;
				}
			}
			
			if(mi == -1)
				System.out.println("No Solution");
			else{
				System.out.println("G" + (mi+1));
				System.out.printf("%d.0 %.1f",minDist,(sumDist*1.0)/n);
			}
			
		}
		
		
	}
	
	private static int min(int i,int j){
		return i < j ? i : j;
	}
	
	private static int parseNumber(String p,int m){
		if(p.charAt(0) == 'G')
			return Integer.parseInt(p.substring(1)) - 1;
		else
			return Integer.parseInt(p) + m - 1;
	}
	
}
