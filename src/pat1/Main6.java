package pat1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main6 {

	public static boolean[][][] pixels;   //记录该分片的值  1为true,0为false
	public static boolean[][][] visited;  //判断该节点有没有被访问过
	public static int count;  //记录当前结点的volum值
	public static int m;  //平面大小
	public static int n;  //平面大小
	public static int l;  //三维空间的厚度
	//x,y,z :这三个数组用来记录符合要求的三维空间数组的所有节点的坐标值，x{} y{} z{} 将节点的坐标值按x,y,z的方向区分开来，方便判断
	public static int[] adjustX = {-1,1,0,0,0,0};  
	public static int[] adjustY = {0,0,-1,1,0,0};
	public static int[] adjustZ = {0,0,0,0,-1,1};
	public static Queue<Node> queue;  //使用队列来存放节点，及该节点的相邻节点
	
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  //使用字符流读取一行数据
		StringTokenizer tokenizer;   //StringTokenizer可以将读取的一行按照指定的分隔符分隔开
		tokenizer = new StringTokenizer(reader.readLine());  //将读取的字符串放入分割标记中
		m = Integer.parseInt(tokenizer.nextToken());  
		n = Integer.parseInt(tokenizer.nextToken());
		l = Integer.parseInt(tokenizer.nextToken());
		int t = Integer.parseInt(tokenizer.nextToken());
		pixels = new boolean [l][m][n];  //读取完成后，即获得了三维空间的三个维度值：x,y,z;用这3个变量构造一个三维的数组，Pixels[]表示该节点的boolean
		visited = new boolean[l][m][n];  //该三位数组表示该节点有没有被判断过
		
		//使用3个for循环接收用户输入的数据
		for(int i=0;i!=l;++i){
			for(int j=0;j!=m;++j){
				tokenizer = new StringTokenizer(reader.readLine());
				for(int k=0;k!=n;++k){
					pixels[i][j][k] = Integer.parseInt(tokenizer.nextToken()) == 1;  //跟1比较的原因是判断该位置上的值的真假
				}
			}
		}
		
		//开始进行运算
		int sum = 0;  //代表所有满足要求的节点的volum总和
		for(int i=0;i!=l;++i){
			for(int j=0;j!=m;++j){
				for(int k=0;k!=n;++k){
					count = 0;  //初始时，该节点周围符合要求的节点为0个
					if(!visited[i][j][k] && pixels[i][j][k])  //当该节点未判断过并且为true时,说明它可能满足题目中给出的要求，在这里使用dfs算法依次迭代判断
						dfs(i,j,k);  //深度搜索遍历
					if(count>=t)   //判断完成后，计算符合要求的节点的count值是否满足最小值要求的t,如果满足，将该volum值相加
						sum += count; //sum用来计算总的volum
				}
			}
		}
		System.out.println(sum);
		reader.close();
	}
	
	public static void dfs(int i,int j,int k){
		queue = new LinkedList<Node>();   //使用队列来存放每个节点
		visited[i][j][k] = true;  //当前函数是要搜索该节点下所有满足要求的对象，所以将该结点标记为true,代表已经对其遍历过，不会再进行第二次遍历
		queue.offer(new Node(i,j,k));  //插入队列
		while(!queue.isEmpty()){  
			Node node = queue.poll();  //取出队列中的元素
			count++;   //当前节点满足要求，计数值+1
			//6次循环迭代，一次判断前后，左右，上下 这6个节点是否满足要求
			for(int r=0;r!=6;++r){
				//取出这6个节点的坐标值
				int x = node.x + adjustX[r];
				int y = node.y + adjustY[r];
				int z = node.z + adjustZ[r];
				//对这6个节点的合法性进行判断
				//1.对于在x轴上的节点，对应的是 l轴  2. y--m   3. z--n
				//visited判断该节点有没有被判断过，如果已经有了就不会再将其置为true,把它加入队列中（这样做是重复判断）
				//pixels用来判断该位置上的值是否满足要求
				if(x<l && x>=0 && y<m && y>=0 &&z<n && z>=0 &&
						!visited[x][y][z] && pixels[x][y][z]){
					//如果满足要求，将该位置上的节点标记为已经判断过，并将其加入到队列中等待判断
					visited[x][y][z] = true;
					queue.offer(new Node(x,y,z));
				}
			}
		}
	}
}
//该类用来表示题目中的一个一个脑细胞
class Node{
	public int x,y,z;
	public Node(int x,int y,int z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
}
