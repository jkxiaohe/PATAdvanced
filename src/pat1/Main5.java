package pat1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//分析：三维空间的BFS。如果某个位置为1，就要对其三维空间（前后上下左右），将相邻的1都找出来，如果找出来的这一片区域中的1的个数不小于所给值，那么就把这一片的值加入最终结果。
public class Main5 {

	static int tensor[][][];  //用来记录用户输入的三维空间上的每一个值
	static int volum;  //记录当前的体积，用于最后的结果输出
	static int cnt;  //记录当前位置的相邻域1的个数
	static int m,n,l,t;  //m,n:平面的大小，l:平面的厚度，t:相邻域中1的阈值
	//符合要求的三维空间的节点值，记录了与该节点相连的所有节点为1的下标值
	static int dir[][] = {{0,1,0},{1,0,0},{0,0,1},{-1,0,0},{0,-1,0},{0,0,-1}}; 
	public static void main(String[] args) throws IOException{
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		while((str=buff.readLine()) != null){
			String[] arr = str.split(" ");
			m = Integer.parseInt(arr[0]);
			n = Integer.parseInt(arr[1]);
			l = Integer.parseInt(arr[2]);
			t = Integer.parseInt(arr[3]);
			tensor = new int[l+2][m+2][n+2];
			for(int i=1;i<=l;++i){
				for(int j=1;j<=m;++j){
					str = buff.readLine();
					arr = str.split(" ");
					for(int k=1;k<=n;++k){
						tensor[i][j][k]=Integer.parseInt(arr[k-1]);
					}
				}
			}
			volum = 0;
			for(int i=1;i<=l;++i){
				for(int j=1;j<=m;++j){
					for(int k=1;k<=n;++k){
						if(tensor[i][j][k]!=0){
							cnt =1;
							tensor[i][j][k]=0;
							bfs(i,j,k);
							if(cnt>=t){
								volum+=cnt;
							}
						}
					}
				}
			}
			System.out.println(volum);
		}
	}
	public static void bfs(int x,int y,int z){
		Queue<Nodeas> queue = new LinkedList<Nodeas>();
		Nodeas node = new Nodeas(x,y,z);
		queue.add(node);
		while(!queue.isEmpty()){
			Nodeas temp = queue.poll();
			for(int i=0;i<6;++i){
				int idx = dir[i][0] + temp.x;
				int idy = dir[i][1] + temp.y;
				int idz = dir[i][2] + temp.z;
				if(idx>0 && idx<l+1 && idy>0 && idy<m+1 && idz>0 && idz<n+1){
					if(tensor[idx][idy][idz]==1){
						cnt++;
						tensor[idx][idy][idz]=0;
						queue.add(new Nodeas(idx,idy,idz));
					}
				}
			}
		}
		return;
	}
}
class Nodeas{
	int x,y,z;
	Nodeas(int x,int y,int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
