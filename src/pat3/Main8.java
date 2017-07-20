package pat3;

import java.util.*;

/**
 * 
 * @author xiaohe
 *题意解析：第一行中，接收用户输入的总人数和每组人数的大小
 *第二行：按照下标的顺序给出每个人的权重
 *第三行：按照下标顺序给出每个人的出场顺序
 *注意：不够k人一组的单独作为一组，在每一轮循环中，按照k人一组的规则进行比较，每一组中选取出权重最大的那一个人进入下一轮的循环
 *当前循环留下的那些人等级都一样，并且他们的等级是：进入下一轮循环的总人数+1
 *那些晋级的人再按照相同的规则进行比较，他们的等级也是按照相同的规则进行累加
 */
public class Main8 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int num = in.nextInt(); //存储参赛的总人数
		int per = in.nextInt(); //每组的人数
		
		int[] ranks = new int[num];  //存储每个人的等级
		int[] weight = new int[num];  //按照每个人的下标存储每个人的权重
		for(int i=0;i<num;i++){
			weight[i] = in.nextInt();
		}
		
		Queue<Integer> q = new LinkedList<Integer>();  //q用来存储每个人的出场顺序，即用户输入的第三行数据
		Queue<Integer> qq = new LinkedList<Integer>();  //qq用来存储每一轮晋级的人
		
		for(int i=0;i<num;i++){
			q.add(in.nextInt());
		}
		
		
		Queue<Integer> q_copy = new LinkedList<Integer>(q); //queue队列用来存储当轮的所有人，用于将那些未晋级的人取出
		
		while(true){  //比赛会一直进行下去，内置退出的条件
			while(!q.isEmpty()){              //q存储所有参赛的人员，只要q不为空，那么便将比赛进行下去
				int max = 0,max_index = 0;    //max用来比较一组成员中谁的权重是最大的，max_index用来存储权重最大的那个人的下标
				for(int i=0;i<per && !q.isEmpty();i++){ //循环的条件是从队列中依次取出per个人作为一组，当超过per个人或队列已经取完时，退出
					int j = q.remove();    //从q队列中获取并移除此队列的头节点
					if(weight[j] > max){   //根据该人的下标，到weight数组中找出最大权重的值
						max = weight[j];
						max_index = j;
					}
				}
				qq.add(max_index);  //第一轮循环每个小组晋级的人都会放入到qq队列中
			}
			
			int len = qq.size();  //len存储了晋级的总人数
			for(int i:q_copy){  //对于q_copy中那些未晋级的人，他们的等级=晋级的人数+1
				if(!qq.contains(i)){
					ranks[i] = len + 1;
				}
			}
			
			//当晋级的总人数只剩下一个人时，此时这个人的等级为1，并且比赛结束
			if(qq.size() == 1){  
				ranks[qq.peek()] = 1;
				break;
			}
			
			//将当前晋级的总人数给了q,进行下一轮的比赛，同时qq中的所有数据要情况，它要用来存储新一轮的晋级人数，
			//q_copy是用来保存当轮的所有人数的，也要进行清空
			q = qq;   
			qq = new LinkedList<Integer>();
			q_copy = new LinkedList<Integer>(q);
		}
		
		for(int i=0;i<num-1;i++)
			System.out.print(ranks[i] + " ");
		System.out.println(ranks[num-1]);
	}
	
}
