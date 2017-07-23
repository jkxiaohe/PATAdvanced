package pat8;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * 题意解析：根据玩家的总数N录入没对玩家的信息，没对玩家的信息包括：
 *       HH:MM:SS(到达时间) P(玩耍时间) Tag(VIP标识符)
 *       倒数第二行包括2个数字，K:所有的桌子，M:VIP桌子的数目，
 *       最后一行显示M个VIP桌子的编号
 *   题目中的条件有很多，要分开分情况进行判断，由于考虑等每队人都要按序等待，每张桌子也都要按序分配，所以使用PriorityQueue队列来存储
 *   PriorityQueue队列的特点：队列中的元素按照自然顺序进行排序，如果在构造函数中指定了比较器，那么会按照指定的比较器进行排序
 *             peek():该方法获取但不移除此队列的头元素，
 *             poll():获取并移除此队列的头元素；
 */
public class Main8 {

	/**
	 * 新建5个队列，即这里需要5个队列来满足所有需要
	 * usuaPer:普通用户队列，存储的是每一个普通用户的信息，类型为Node;
	 * VIPPer:VIP用户队列，存储的是每一个VIP用户的信息，类型为Node;
	 * VIPtable:VIP桌子，存放VIP桌子的编号，泛型为Integer;
	 * usuatable:普通用户桌子，存放普通用户的编号，泛型为Integer;
	 * q:初始的一个队列，首先接受所有用户的输入，然后再后边依次将他们归类到不同类型的数组中
	 */
	Queue<Node> usuaPer = new PriorityQueue();
	Queue<Node> VIPPer = new PriorityQueue();
	Queue<Integer> VIPtable = new PriorityQueue();
	Queue<Integer> usuatable = new PriorityQueue();
	Queue<Node> q = new PriorityQueue();
	
	/**
	 * 场馆营业的时间为：08:00:00 -- 21:00:00,21*3600代表结束时间，并且是以秒为单位的
	 * isVIPPer 数组记录当前玩家是不是VIP
	 * isVIPTab  记录某个桌子是不是VIP桌子
	 * play  该数组记录每对玩家玩耍的时间，int类型
	 * nums  存储当前所有的桌子编号
	 */
	final int close = 21 * 3600;  
	boolean[] isVIPPer;
	boolean[] isVIPTab;
	int[] play;
	int[] nums;
	
	public static void main(String[] args) {
		new Main8().store();  //函数的处理是从store方法开始的
	}
	
	//入口函数
	public void store(){
		Scanner in = new Scanner(System.in);
		int num = Integer.parseInt(in.nextLine().trim());  //num接受所有玩家总数目
		play = new int[num];  //根据玩家数量构造play和isVIPPer数组
		isVIPPer = new boolean[num];
		//通过for循环接收num个玩家的数据
		for(int i=0;i<num;i++){
			String[] strs = in.nextLine().split(" "); //strs存储了一对玩家的所有数据
			String[] strs1 = strs[0].split(":");  //strs1将到达时间进行分割，分别存储时：分：秒
			Node n = new Node();   //新建一个Node节点存储玩家的相关信息
			n.id = i;  //给每个玩家的id赋值，用i来标识即可
			//将每个玩家的入场时间用秒来表示，可以直接根据time的大小比较其达到的顺序
			n.time = (Integer.parseInt(strs1[0]))*3600 + (Integer.parseInt(strs1[1]))*60 + Integer.parseInt(strs1[2]);
			//Node.type : 1:当前还未为其分配桌子  , 0:当前已经为其分配了桌子
			//即type表示当前该玩家有没有占用桌子
			n.type = 1;  
			//play数组存储当前每个玩家的玩耍时间，要注意的是：最多玩2个小时
			play[i] = Math.min(Integer.parseInt(strs[1]), 120);
			play[i] *= 60;  //将play中的玩耍时间改为秒为单位
			n.needTime = play[i];  //放入Node节点的needTime中
			isVIPPer[i] = strs[2].equals("1");  //isVIPPer数组用来存储对应下标处的用户是不是VIP
			n.line = strs[0]+" " + strs[1]+" " + strs[2];
			q.add(n);  //放入到队列中
		}
        int tables = in.nextInt();   //当前所有的桌子数
        isVIPTab = new boolean[tables];  //VIPTab桌子的标识数组
        nums = new int[tables];   //nums存储桌子的数目
        int VIPtabs = in.nextInt();  //VIP桌子的数目，以及根据VIP桌子的数目循环将VIP桌子的下标放入到isVIPTab数组中并标识为true
        for(int i=0;i<VIPtabs;i++){
        	isVIPTab[in.nextInt()-1] = true;
        }
        //将所有的桌子的编号按照其类型(普通和VIP)分别放入到对应的不同的队列中
        for(int i=0;i<tables;i++){
        	if(isVIPTab[i]){
        		VIPtable.add(i);
        	}else{
        		usuatable.add(i);
        	}
        }
        done();  //由done函数处理接下来的分配问题，store()函数已经准备好了所有的数据
	}
	
	public void done(){
		//只要q队列不为空，就不断地从队列中取出每一对玩家
		while(!q.isEmpty()){
			Node temp = q.poll();  //弹出优先级队列排序后的头部玩家
			int now = temp.time;  //now=当前玩家的到达时间
			//特殊情况：如果当前玩家的到达时间已经超过了21:00:00,取出下一个玩家的信息
			if(now >= close)break;   
			//如果type==0，说明已经为该对玩家分配了桌子，里面处理的是要不要释放当前桌子
			if(temp.type == 0){
				freeTable(temp.id);   //根据为用户分配的桌子的编号，释放该张桌子为可用状态
				//如果当前时间仍然有其他用户使用完了桌子，也要进行回收，直到q队列中顶部元素没有被分配桌子为止
				while(!q.isEmpty() && q.peek().time == now && q.peek().type == 0){
					freeTable(q.poll().id);
				}
				//回收完桌子后，进行重新选择时，首先对VIP用户及对应的VIP桌子进行选择，当然，他们必须有值存在时才能够选择
				while(!VIPPer.isEmpty() && !VIPtable.isEmpty()){
					Node n1 = VIPPer.poll();    //从VIP用户队列中弹出顶部对象
					temp.time = now + n1.needTime;  //将时间轴进行累加，只想该用户完成后的事件点
					prints(n1.time,now);   //输出已分配出的用户的信息
					temp.id = VIPtable.poll();  //从VIP桌子中弹出队列头部的id编号给temp.id，方便下次从队列中释放该对应编号的该桌子
					nums[temp.id]++;  //桌子的编号位++,说明已经被使用了
					temp.type = 0;  //type=0说明已经分配上了桌子
					q.add(temp);  //将该人加入到队列总
				}
				//VIP用户和VIP桌子单独判断完成后，接下来的所有情况处理方法都是一致的
				while((!VIPPer.isEmpty() || !usuaPer.isEmpty()) && (!VIPtable.isEmpty() || !usuatable.isEmpty())){
					Node n2 = getnod();
					temp.time = now + n2.needTime;
					prints(n2.time,now);
					temp.id = getTable();  //只要桌子是空的就可以使用，不论是不是VIP桌子
					nums[temp.id]++;
					temp.type = 0;
					q.add(temp);
				}
			}
			//在没有为玩家分配桌子的情况下，优先考虑VIP队列中的每个成员，此处用来判断当前用户是不是一个VIP，如果是VIP，那么首先为VIP分配桌子
			//如果没有，那么将VIP用户放入到VIP等待队列中
			else if(isVIPPer[temp.id]){  
				if(!VIPtable.isEmpty()){
					temp.time = now + play[temp.id];
					prints(now,now);
					temp.id = VIPtable.poll();
					nums[temp.id]++;
					temp.type = 0;
					q.add(temp);
				}
				else if(!usuatable.isEmpty()){
					temp.time = now + play[temp.id];
					prints(now,now);
					temp.id = usuatable.poll();
					nums[temp.id]++;
					temp.type = 0;
					q.add(temp);
				}else{
					VIPPer.add(temp);
				}
			}
			//能运行到这里，说明是一个普通玩家，判断桌子有没有空的，如果有空桌子就给他分配(这里的空桌子包括普通桌子和VIP桌子)
			else if(!usuatable.isEmpty() || !VIPtable.isEmpty()){
				temp.time = now + play[temp.id];
				prints(now,now);
				temp.id = getTable();
				nums[temp.id]++;
				temp.type = 0;
				q.add(temp);
			}
			//能运行到这里，说明没有桌子可以使用，放入到队列中等待，继续从q队列中取出判断
			else{
				usuaPer.add(temp);
			}
		}
		String sss = "";
		for(int i=0;i<nums.length;i++){
			sss = sss + nums[i] + " ";
		}
		System.out.println(sss.trim());
	}
	
	//根据指定的编号释放指定的桌子
	private void freeTable(int id){
		//释放桌子的时候，需要判断一下当前该编号的桌子是VIP桌子还是普通的桌子，判断出来后，将当前桌子的编号重新放入到可用的队列中
		if(isVIPTab[id]){
			VIPtable.add(id);
		}else{
			usuatable.add(id);
		}
	}
	
	/**
	 * 根据用户指定的参数输出当前用户的数据，输出 格式为：到达时间：服务时间：等待时间
	 * 由于在整个过程中为了方便比较，时间都是以秒为单位的，所以需要将时间转为指定的格式，
	 * 时：/3600 ; 分:%3600求余后剩下的分钟和秒数之和，/60剩下的分钟数，可以将秒数过滤掉; 秒：%60,剩下的是<60的数，即当前的秒数
	 * y是开始分配的时间，也需要将描述转换为 HH:MM:SS的格式
	 * y-x：开始服务的时间-到达的时间，+30是为了四舍五入，因为最后的结果要求只保留到分部门，/60变得到当前等待的分钟数
	 * 
	 */
	private void prints(int x,int y){
		System.out.printf("%02d:%02d:%02d %02d:%02d:%02d %d\n",x/3600,x%3600/60,x%60,y/3600,y%3600/60,y%60,(y-x+30)/60);
	}
	
	/**
	 * 获取当前的空闲的桌子,有以下几种情况需要考虑：
	 * 1.如果普通用户的桌子为空，从VIP桌子队列里找
	 * 2.如果VIP桌子为空，从普通用户桌子队列里找；
	 * 3.如果2边的桌子都有空的时候，选择编号最小的那个桌子进行分配
	 */
	private int getTable(){
		int r;
		if(usuatable.isEmpty()){
			r = VIPtable.poll();
			return r;
		}
		if(VIPtable.isEmpty()){
			r = usuatable.poll();
			return r;
		}
		if(usuatable.peek() < VIPtable.peek()){
			r = usuatable.poll();
		}else{
			r = VIPtable.poll();
		}
		return r;
	}
	
	/**
	 *  从队列中获取还未分配的用户： 
	 */
	private Node getnod(){
		Node n;
		if(usuaPer.isEmpty()){
			n = VIPPer.poll();
			return n;
		}
		if(VIPPer.isEmpty()){
			n = usuaPer.poll();
			return n;
		}
		if(usuaPer.peek().time < VIPPer.peek().time){
			n = usuaPer.poll();
		}else{
			n = VIPPer.poll();
		}
		return n;
	}
	
	
}
/**
 *Node节点用来存储每对玩家的信息,包括：id:玩家的编号，time:玩家到达的时间，
 *     needTime : 该玩家需要等待的时间，  type: 玩家的类型（VIP or !VIP）
 *  实现了Comparable接口，这样在PriorityQueue优先级队列中存储时，PriorityQueue队列就可以根据指定的比较器对Node元素进行排序了
 *  compareTo中比较方法的解释：
 *     首先根据每个人的到达时间进行排序，该因素优先级最高，
 */
class Node implements Comparable<Node>{
	int id;
	int time;
	int needTime;
	int type;
	String line;
	
	@Override
	public int compareTo(Node o) {
		return this.time > o.time ? 1 :
				this.time < o.time ? -1 :
				this.type > o.type ? -1 :
				this.type < o.type ? 1 : 0;
	}
	
}