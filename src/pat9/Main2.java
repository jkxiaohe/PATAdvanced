package pat9;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 题意解析：一辆汽车从杭州出发到目的城市，中间有N个加油站，汽车的油箱容量有限，问求到目的城市最便宜的加油的方案。
 * 思路：将加油站到杭州的距离按序放入到TreeSet集合中，把汽车的(油价，油量)最为一对数据存储，尽可能加便宜的油用，
 *     换一种方式可以理解为假设油箱是满的，里面有各种不同价格的油及其对应的油量，首先用那些便宜的油，贵的油可以不用，不计入最后的总费用，就相当于没加就好啦，
 *     每次新到一个加油站，都把油箱加满，判断油箱里不同价格的油和当前加油站的油价哪个更便宜，要进行替换，这就相当于是在整个过程中用最便宜的油跑到目的地，
 *     并且每次的油刚好够用，反正多的那些贵的油没有用到，不计入最后的总价中。
 */
public class Main2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		double tank = in.nextInt();   //油箱容量，将邮箱最大容量放入到totalTank中，tank用来存储临时的油量
		double totalTank = tank;    
		double distance = in.nextDouble();  //2个城市之间的距离
		double avg = in.nextDouble();  //每单位的油量可以跑的距离
		int stations = in.nextInt();  //加油站总数
		
		//使用TreeSet来保存所有加油站的信息，注意的是：将目的地也放入到集合中，它代表了最后的终点
		TreeSet<Station> set = new TreeSet<Station>();
		for(int i=0;i<stations;i++){
			set.add(new Station(in.nextDouble(), in.nextInt()));
		}
		set.add(new Station(Double.MAX_VALUE, distance));
		
		//新建一个优先级队列，里面存储油箱对应的不同价格的油量,该队列会使用Pair中自带的比较器进行排序
		Queue<Pair> queue = new PriorityQueue<Pair>();
		Iterator<Station> it = set.iterator();  //获取其迭代器
		//从set集合中弹出最近的一个加油站，放入到队列中，包括油价，油箱容量
		Station lastStation = it.next();  
		queue.add(new Pair(lastStation.price, totalTank));
		
		double totalPrice = 0.0;  //总共的花费
		double totalDistance = 0.0;  //总共行驶的距离
		double eps = 1e-6;  //浮点数的大小不能直接比较，使用一个特别小的值进行比较
		
		//只要没有到达目的地，就不停的向下遍历，it里面按距离存放了所有的加油站及最后的终点
		while(it.hasNext()){
			Station cur = it.next();  //cur代表的是当前的加油站
			double needDis = cur.dis - lastStation.dis;  //needDis是相邻的2个加油站之间的距离
			
			Iterator<Pair> pairIt = queue.iterator();  //从queue队列取出所有的(油价，油量)迭代器
			//对油箱中的油类进行遍历，同时判断needDis是不是>0,needDis代表到下一个站点的距离，如果<=0,说明已经到终点了
			while(pairIt.hasNext() && needDis>0){
				double needGas = needDis/avg;  //needGas是到达下一个加油站所需要的油量
				Pair pair = pairIt.next();   //从pair队列中取出每对油看是不是满足要求
				double canRun = pair.gas * avg;  //当前该价格及其对应的油量可以跑的距离
				
				//如果当前价格的油量足以跑到下一个加油站，那么对整体的数值进行一个更新
				if(canRun >= needDis){   
					totalPrice += needGas*pair.price;  //总价更新，加上当前油价的花费
					//对该档次的油价能跑的距离进行判断，因为该价格的油量可能全部用完也可能只是用掉了一部分
					if(canRun == needDis){   //此种情况该价格的油全部用完
						tank -= pair.gas + eps;  //更新油箱中的油量，eps是为了确保浮点数相加减时的精度问题
						pair.gas = 0;  //该价格的油油量=0
					}else{
						//此种情况说明该价格的油只是用掉了一部分，needGas即是用掉的部分
						tank -= needGas + eps;  
						pair.gas -= needGas;
					}
					totalDistance += needDis;  //更新行驶的距离
					needDis = 0;   //此时已经到达了下一个加油站，更新距离为0
					
					//到达一个新的加油站后，要重新判断邮箱里的油价和当前加油站的油价哪个更便宜，如果当前加油站的油价更便宜，那么要将油箱里贵的油替换掉
					//pair中的油价是排过序的，如果当前最低的油价都>=cur.price,那么后面的油价更是，更新油箱里的油
					if(pair.price >= cur.price){
						queue.clear();   
						queue.add(new Pair(cur.price, totalTank));
						tank = totalTank;
						break;
					}else{
						//当前档次油价是更便宜的，那么就向后查找，如果后面有贵的，那么就只更新后面贵的油
						while(pairIt.hasNext()){
							pair = pairIt.next();
							if(pair.price >= cur.price){
								tank -= pair.gas + eps;
								pairIt.remove();
							}
						}
						//将那些比当前油价贵的油都移除出队列后，将当前加油站的油全部放满加入到queue队列中
						if(tank < totalTank){
							queue.add(new Pair(cur.price, totalTank - tank + eps));
							tank = totalTank;
						}
					}
					break;  //退出当前循环，因为现在这段距离已经跑完了
				}else{
					//如果当前油价的油量不足以支撑走完该段路，那么取出下一个油价的油继续跑，并把当前用完的这有移除
					totalPrice += pair.gas * pair.price;
					tank -= pair.gas + eps;
					needDis -= canRun;
					totalDistance += canRun;
					pairIt.remove();
				}
			}
			
			//如果最后满油状态下仍然没有走到目的地，输出当前跑的总路程
			if(needDis > 0){
				System.out.printf("The maximum travel distance = %.2f\n",totalDistance);
				return;
			}
			lastStation = cur;  //lastStaion代表当前所在的加油站处，每路过一个，更新一次
		}
		//输出即可
		System.out.printf("%.2f\n",totalPrice);
		
	}
	
	/**
	 *类节点对象，代表每一个加油站，包括的信息有：油价(price),距离(dis)
	 *  需要自定义实现一个比较器，比较器因素的优先级：
	 *  dis优先级最高，代表了当前最近的加油站；
	 *  price的优先级次之，在路径相同的情况下，优先选用便宜的油价；
	 */
	
	static class Station implements Comparable<Station>{
		double price;
		double dis;
		Station(double price,double dis){
			this.price = price;
			this.dis = dis;
		}
		
		@Override
		public int compareTo(Station o) {
			if(this.dis > o.dis){
				return 1;
			}else if(this.dis == o.dis){
				return (int) (this.price - o.price);
			}
			return -1;
		}
	}
	
	/**
	 *  将油箱中的油进行分块：每块对应的内容有(油价，油量)
	 *  即在车行驶的过程中，优先把低油价的油量给用掉，如果此时无法走到下一个加油站，或下一个加油站的油更贵，那么从油箱中
	 *  取出下一个更便宜的油来使用
	 *  该类也要实现比较器，优先级为油价price
	 */
	static class Pair implements Comparable<Pair>{
		double price;
		double gas;
		Pair(double price,double gas){
			this.price = price;
			this.gas = gas;
		}
		
		@Override
		public int compareTo(Pair o) {
			if(this.price > o.price){
				return 1;
			}else if(this.price == o.price){
				return 0;
			}
			return -1;
		}
	}
	
}
