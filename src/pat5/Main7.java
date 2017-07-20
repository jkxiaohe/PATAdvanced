package pat5;

import java.util.Scanner;

public class Main7 {

	static final int INF = 1000000007;   
	static int m,n,ds,k;    
	static int Matrix[][] = new int[1024][1024];  
	static boolean[] vis = new boolean[1024]; 
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		ds = in.nextInt();
		String str1,str2;
		int len,s,t;
		
		init();
		
		for(int i=0;i<k;i++){
			str1 = in.next();  
			str2 = in.next();
			len = in.nextInt();
			if(str1.charAt(0) == 'G'){
				if(str1.length() == 3)
					s = n+10;
				else
					s = n + str1.charAt(1) - '0';
			}else{
				s = Integer.parseInt(str1);
			}
			
			if(str2.charAt(0) == 'G'){
				if(str2.length() == 3)
					t = n + 10;
				else
					t = n + str2.charAt(1) - '0';
			}else{
				t = Integer.parseInt(str2);
			}
			
			if(s == t){
				continue;
			}
			
			if(Matrix[s][t] > len)
				Matrix[s][t] = Matrix[t][s] = len;
		}
		
		for(int i=1;i<=m;i++){
			vis[n+i] = true;  
			dij(n+i);  
		}
		
		double avgdis = INF;  
		int dis = -1;  
		int idx = -1; 
		for(int i=n+1;i<=m+n;i++){
			int Min = INF;    //
			boolean flag = true;
			double avg = 0;
			
			for(int j = 1;j <= n;j++){
				if(Matrix[i][j] > ds){  
					flag = false;
					break;
				}
				avg += (double)Matrix[i][j]/n;
				Min = Math.min(Matrix[i][j], Min);
			}
			if(!flag)
				continue;
			if(Min > dis){
				dis = Min;  
				avgdis = avg;
				idx = i - n;  
			}else if(Min == dis && avg < avgdis){
				dis = Min;
				avgdis = avg;
				idx = i - n;
			}
		}
		
		if(idx == -1){
			System.out.println("No Solution");
		}
		else{
			System.out.println("G" + idx);
			System.out.printf("%d.0 %.1f\n",dis,avgdis);
		}
		
	}
	
	static boolean dij(int s){
		for(int i=1;i<m+n;i++){  
			int idx = -1,len = INF;  
			for(int j=1;j<=m+n;j++){  
				if(vis[j])   
					continue;
				if(Matrix[s][j] < len){
					len = Matrix[s][j];
					idx = j;
				}
			}
			if(idx == -1)
				return false;
			vis[idx] = true;
			for(int j=1;j<=m+n;j++){
				if(vis[j])   
					continue;
				if(Matrix[s][j] > Matrix[s][idx]+Matrix[idx][j]){
					Matrix[s][j] = Matrix[j][s] = Matrix[s][idx]+Matrix[idx][j];
				}
			}
		}
		return true;
	}
	
	static void init(){
		for(int i=0;i<1024;i++){
			for(int j=0;j<1024;j++){
				Matrix[i][j] = INF;
			}
		}
	}
}
