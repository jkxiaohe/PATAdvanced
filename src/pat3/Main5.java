package pat3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author dell
 *题目解析：该题要求解从零售商那里可以获取到的商品价格的最大值
 *每一行的下标代表其父节点，题目给出的数据是：个数，后面每个数据的父节点都是对应那一行的下标值
 */
public class Main5 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();  //总人数
		double p = in.nextFloat();  //原价
		double r = in.nextFloat();  //增长率
		
		int[] suppliers = new int[n];  //总共有n个人，那么供应商的人数肯定<=n
		List<Retailer> retailers = new ArrayList<>();   //使用List集合存放每一个零售商
		//对这n个所有的人进行遍历，将每个人分别放入对应的供应商数组和零售商集合中
		for(int i=0;i<n;i++){
			int k = in.nextInt();
			if(k == 0){   //Kj为0说明是零售商，放入零售商对应的下标和产品数量
				retailers.add(new Retailer(i, in.nextInt()));
			}
			else{  //Ki不为0说明是供应商，k代表后面有几个供应商都是下标为i的子节点
				for(int j=0;j<k;j++){
					//数组的格式为：suppliers[经销商]=供应商，后面可以通过suppliers[下标]找到对应的上一级父节点，直至根节点0
					suppliers[in.nextInt()] = i;
				}
			}
		}
		
		double total = 0;  //total总共的价格
		double rate = 1+(r/100);  //增长率
		//对零售商进行遍历，找出所有零售商所卖产品的最大价钱
		for(Retailer retailer : retailers){
			int level = 0;  //当前所在层数默认为0，逐渐向上遍历，level++
			int id = retailer.id;  //可以根据id,到suppliers数组中找出对应的上一级节点
			while(id != 0){  //id==0时说明到了根节点的位置，退出
				level++;
				id = suppliers[id];
			}
			//计算公式是：数量*原价*(增长率)平方数
			total += retailer.amount * p * Math.pow(rate, level);
		}
		System.out.printf("%.1f",total);
		
	}
	
	//构造一个类对象，专门存储零售商的信息，包括零售商的下标编号(即所在那一行的行号)和出售的产品总数量
	private static class Retailer{
		int id;
		int amount;
		Retailer(int id,int amount){
			this.id = id;
			this.amount = amount;
		}
	}
}
