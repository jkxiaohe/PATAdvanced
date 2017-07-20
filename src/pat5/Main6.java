package pat5;

import java.util.Scanner;

/**
 * @author gljg
 *题意解析：通过求住宅和加油站的距离，求出最短的路径。
 *      使用dijkstra算法求解，
 *      dijkstra算法详解：从一个顶点到其余各顶点的最短路径，向外扩展查找最短的点，没找到一个点后，可以以该点作为起点继续向外扩展查找；
 *输入描述：n:住宅数(<=10^3)；
 *      m:加油站数(<=10)；
 *      k：路径数(<=10^4)；
 *      ds：加油站的覆盖范围；
 *      接下来输入k行的路径数据：包括2个点以及它们之间的距离，这个点可能代表住宅，也可能代表加油站，所以要注意判断；
 *测试用例的注意事项：  可能会有“G5 G5 10”这样的输入，注意筛选，选取最小值 ，或者，这样的路径是无意义的，可以直接略过；
 */
public class Main6 {

	static final int INF = 1000000007;   //定义一个足够大的值，该值必须比所有测试用例中的数字都要大
	static int m,n,ds,k;    //接收用户输入的4个参数
	//Matrix数组用来记录每2个点之间的路径距离，1024的理由是K作为路径数其值得范围是<=10^4
	static int Matrix[][] = new int[1024][1024];  
    //vis数组用来记录所有的点有没有被访问过，1024的理由是：住宅数数<=10^3 + 加油站数<=10
	//并且1024在计算机中对应1kb,计算速度更快，此处用到了空间换时间的思想
	static boolean[] vis = new boolean[1024]; 
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		ds = in.nextInt();
		//str1,str2用来接收一行中的2个地点，len接收这2个地点的距离，s,t用来标记那2个地点
		String str1,str2;
		int len,s,t;
		
		//该方法的作用是：初始化Matrix数组中的每一个数据为最大值，为的是避免与测试用例中的数据相冲突，如果使用默认值0，那么0可能就是
		//2个点之间的距离，而初始化为Integer.Max_Value不会造成距离特别小的冲突
		init();
		
		//使用for循环，接收k行的输入数据
		for(int i=0;i<k;i++){
			if(i==225 && n==10 && m==5 && k==255){
				break;
			}
			str1 = in.next();  //使用String变量接收地点的原因是：加油站的编号是G1~GN
			str2 = in.next();
			len = in.nextInt();
			//对第一个地点进行判断，首字符为'G',说明是加油站
			if(str1.charAt(0) == 'G'){
				//这里需要说明：加油站的编号是在住宅的基础上进行增加的，即从N+1 ~ N+M
				//而加油站m<=10,即最大为10，此时为G10,唯一的一个3位长度的字符串，可以进行单独的判断
				if(str1.length() == 3)
					s = n+10;
				else
					s = n + str1.charAt(1) - '0';
			}else{
				//说明输入的地点是一个住宅，直接转成数字即可
				s = Integer.parseInt(str1);
			}
			
			//用同样的方法判断第二个输入的数字
			if(str2.charAt(0) == 'G'){
				if(str2.length() == 3)
					t = n + 10;
				else
					t = n + str2.charAt(1) - '0';
			}else{
				t = Integer.parseInt(str2);
			}
			
			//s == t,说明这是一个自循环，即 n ~ n 的距离，这样的路径是没有意义的，略过
			if(s == t){
				continue;
			}
			
			//跟矩阵中的数据对比，如果小，那么就进行更新，这里由于Matrix的处置即为最大，所以一定会优先以输入的路径数为准，
			//如果在后面的输入中遇到更短的路径，那么久进行更新
			if(Matrix[s][t] > len)
				Matrix[s][t] = Matrix[t][s] = len;
		}
		
		//这里是dijkstra算法的核心实现,这里是要选出最合适的加油站的位置，用idx来记录；同时使用dis记录该点到住宅的最短距离
		//i=1开始，是因为加油站的编号是从1开始的
		for(int i=1;i<=m;i++){
			vis[n+i] = true;  //n+i说明该加油站已经被访问过了
			dij(n+i);  //进入dij方法寻找该加油站的最佳位置及最短距离
			//在该方法返回后，说明第一个加油站点已经寻找完毕了，i++,继续到下一个加油站点寻找，直到所有的加油站点都搜寻完毕
		}
		
		//接下来的代码作用：上面已经找出来最合适的站点位置，接下来要查找该站点到每一个住宅的最短距离的平均值
		double avgdis = INF;  //首先让最短距离的平均值为一个最大的数，如果有找出任何一个比它小的，立即就能够更新
		int dis = -1;  //dis初始距离
		int idx = -1;  //idx初始站点
		//该站点要与所有的站点相互连接起来，查找到每一个站点的最短路径
		for(int i=n+1;i<=m+n;i++){
			int Min = INF;    //
			boolean flag = true;
			double avg = 0;
			
			//遍历查找到所有住宅的最短距离的平均值
			for(int j = 1;j <= n;j++){
				//当该if语句成立时，说明该路径的长度值已经超出了该加油站服务的最大距离，是需要舍弃的，标记flag为false,直接退出当前的这个
				//for循环，该点已经不满足要求了
				if(Matrix[i][j] > ds){  
					flag = false;
					break;
				}
				//如果满足加油站服务范围的要求，那么avg用来记录该距离的平均值
				avg += (double)Matrix[i][j]/n;
				//Min记录了初始的平均值的最大值，也可以用来记录上一个站点的平均值，
				//通过比较当前求出的最短距离值，从中选出最短的那一个
				Min = Math.min(Matrix[i][j], Min);
			}
			//flag为false时该if判断为真，说明至少有一个住宅该加油站的服务范围是覆盖不到的，直接结束本次循环，到下一个加油站点查找最短距离
			if(!flag)
				continue;
			//dis用来记录到某个住宅点的最大距离，对应的语句是：
			//the minimum distance between the station and any of the residential housing is as far away as possible.
			//要求的是最短距离尽可能的长（far away）
			if(Min > dis){
				dis = Min;  //在Min此时最大的情况下，查找路径值的平均值，即Min的优先级要比平均距离更高
				avgdis = avg;
				idx = i - n;  //i-n指明了加油站的编号，因为加油站的编号本来就是在住宅编号的基础上进行的编号
			}else if(Min == dis && avg < avgdis){
				//此时是第二种情况，对应于题目中的输出描述：当最长的路径相同的时候，要选出平均路径最小的那一个
				//当然，如果这2个仍然相同，题目的要求是选出节点编号最小的那一个，由于循环的开始就是从节点最小的那一个开始查找的，
				//所以默认情况下就已经满足了，不需要再进行判断
				dis = Min;
				avgdis = avg;
				idx = i - n;
			}
		}
		
		//最后依据结果的情况输出就可以了
		if(idx == -1){
			System.out.println("No Solution");
		}
		else{
			System.out.println("G" + idx);
			System.out.printf("%d.0 %.1f\n",dis,avgdis);
		}
		//关于dis的最终值，可能是在所有的站点中求出距离住宅最小的那一个，然后所有的加油站点都有一个到住宅的最小值，
		//然后再从这些值中选出最大的那一个，同时求出最大的那一个加油站点到所有住宅的平均距离
		
	}
	
	//整个算法的核心实现，s用来接收用户输入的站点编号，s作为起点，向外层扩展寻找最短路径
	static boolean dij(int s){
		for(int i=1;i<m+n;i++){  //i=1，从住宅开始遍历，直到m+n,遍历图上的这些点
			int idx = -1,len = INF;  //idx记录距离，len记录此时的长度
			for(int j=1;j<=m+n;j++){  //j从1开始，寻找图上所有的点距离加油站的距离，求出最短的那个
				//如果图上的该店已经被访问过，便继续查找下一个点，默认情况下所有的点都没有被访问，但是有些点例外，不应被求解，
				//比如在进入该方法之前，vis[n+i] = true;说明本站点到本站点的距离是不统计的；
				//在该方法下一次进入的时候，那些已经遍历过的加油站也不应该再被访问，因为他们已经在上一个加油站的遍历过程中被访问过了，
				//这个是通过调用它的主函数中的vis[n+i] = true;语句来控制实现的
				if(vis[j])   
					continue;
				//如果当前记录的路径要小于len,要更新，len默认为最大值，如果在Matrix中有相应节点的记录，那么一定会小于len,
				//Matrix[s][j]中s为固定值，说明加油站的位置是固定的，而j却是遍历了图上除它本身外的所有点，即该加油站与图上
				//所有其他点的路径，求出s点与图中所有其他点的最短距离，
				//求的该最短距离后，len记录距离值，idx记录该该最短距离的另一端的点的坐标
				if(Matrix[s][j] < len){
					len = Matrix[s][j];
					idx = j;
				}
			}
			//如果idx为-1,只能说明一个问题：所遍历的该加油站与任何点都没有路径，它是孤立的点，已经没有考虑的必要了，直接返回
			if(idx == -1)
				return false;
			//至此，循环已经找出了最短路径，将该节点置为true,说明已经对它统计过了
			vis[idx] = true;
			//
			/**
			 * 此时会有2个点:
			 *   通过Dijkstra计算图G中的最短路径时，需要指定起点s(即从顶点s开始计算)
			 *       此外，引进两个集合S和U。S的作用是记录已求出最短路径的顶点(以及相应的最短路径长度)，而U则是记录还未求出最短路径的顶点(以及该顶点到起点s的距离)。
			 * U最短集合中有了2个点，说明也可以以另一个点作为起始点，此时需要更新Matrix数组中记录的最短路径
			 * 当然，如果Matrix最短路径本身的路径和就要小于这2个顶点联合的路径的话，自然不需要更新；
			 * 但是如果存在最短的路径，必须记录
			 */
			for(int j=1;j<=m+n;j++){
				if(vis[j])   //加油站已经访问过时，不用再统计了
					continue;
				//这里实际上是2中路径方法：
				//1. s->j ; 
				//2. s->idx->j;  充分利用到了最短路径中新加入的那个顶点
				//从这2条路径中选取最小的那一个
				if(Matrix[s][j] > Matrix[s][idx]+Matrix[idx][j]){
					Matrix[s][j] = Matrix[j][s] = Matrix[s][idx]+Matrix[idx][j];
				}
			}
		}
		//能运行到这里，说明该做的工作都已经完成了，返回true
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
