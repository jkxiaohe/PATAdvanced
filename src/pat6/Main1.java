package pat6;

import java.util.*;

/**
 * @author gljg
 * 题目解析：该题的关键是要理解清楚题目的意思，以及输入输出的要求
 * 输入描述：第1行：N：用户总数；K：问题总数；M：提交总数；
 *            用户的编号为5位的数字，00001 ~ N;问题的编号为1 ~ K;
 *       第2行：K个正整数，p[i] ~ p[k],p[i]对应第i个问题的满分是多少
 *       接下来有M行的数据，格式为：
 *           用户编号   问题编号    获得的分数
 *           其中得分有3中情况：(1)没提交，-1;
 *                       (2)提交没通过编译，-1;
 *                       (3)提交并通过了编译，(0 ~ 满分);
 * 输出描述：
 *      rank:等级，从1开始编号，按照用户的成绩总分记性排序，如果总分相同那么并列排名；
 *      user_id : 用户编号；
 *      total_score:该用户的总成绩；
 *      s[1] ~ s[i]:s[i]对应用户在第i道题中的得分；
 *                  如果用户没有提交，用"-"表示；
 *                  如果用户提交并通过编译了多次，取成绩最高的那一次；
 *      关于rank的排序：如果总分相同，按照满分题目的个数多少排序；如果仍然相同，按照编号升序；
 */
public class Main1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int K = scan.nextInt();
		int M = scan.nextInt();
		int[] fullS = new int[K];  //fullS数组用来接收K道题每个的满分是多少
		for(int i=0;i<K;i++)
			fullS[i] = scan.nextInt();
		
		grade[] g = new grade[N]; //grade数组用来保存每个用户的成绩
		//该for循环用来初始化每个用户的成绩对象，其中i代表的是用户的下标，K用于初始化成绩对象中s数组的大小
		for(int i=0;i<N;i++)
			g[i] = new grade(i, K);
		
		//接收用户输入的M行提交数据
		for(int i=0;i<M;i++){
			int id = scan.nextInt(); //id为用户编号
			int t = scan.nextInt();  //t为问题编号
			int s = scan.nextInt();  //s为得分
			//s!=-1说明用户的分数是要被统计的，将该用户的总分sum由-1改为0,也说明了要开始统计该用户的分数，
			//nofs++说明该用户的成绩最终是要显示在排行榜上的，nofs之后的值>0
			if(s != -1){
				if(g[id-1].nofs == 0)
					g[id-1].sum = 0;
				g[id-1].nofs++;
			}
			//提交但是没有通过编译的题目，记为0分
			if(g[id-1].s[t-1] == -1 && s == -1)
				g[id-1].s[t-1] = 0;
			//当该次的提交分数大于该用户的成绩数组对应的该问题时，需要进行的操作：
			//1.更新该用户在该问题下的最高得分；2.更新用户的总分
			if(s > g[id-1].s[t-1]){
				int cha = s;   //cha用来求该次得分和上次得分的差值
				//!=-1是为了判断用户在该问题有没有作答，如果有提交过，那么cha用来表示分数的新增值
				if(g[id-1].s[t-1] != -1)
					cha = s-g[id-1].s[t-1];
				g[id-1].sum += cha;  //更新该用户下的总得分
				g[id-1].s[t-1] = s;  //更新该用户在该问题下的得分
				//该if的作用是判断用户每次提交的问题是否达到了对应问题的满分，
				//如果是一个满分，那么p++用来记录满分题目的数目
				if(g[id-1].s[t-1] == fullS[t-1])
					g[id-1].p++;
			}
		}
		
		//使用指定的比较器对数组进行排序
		Arrays.sort(g, new Comparator<grade>(){
			@Override
			public int compare(grade g1, grade g2) {
				//在这里定义了排序的规则
				//首先根据总分进行排序，当总分不相等时，返回2者的差值，g2在前的原因是使最后的结果是升序的
				if(g1.sum != g2.sum)
					return g2.sum - g1.sum;
				//如果总分相同，那么根据满分题目的数目进行排序，同样g2在前，代表了最后结果是升序
				if(g1.p != g2.p)
					return g2.p - g1.p;
				//如果上面的2个if仍然没有判断出来，那么根据用户的编号进行排序
				return g1.id.compareTo(g2.id);
			}
		});
		
		
		int r=1,temp=g[0].sum;  //r代表的是等级，默认从1开始，temp代表当前用户的总分
		for(int i=0;i<N;i++){  //输出N个用户的总分
			//nofs<1，说明nofs是用户成绩初始化时候的0值，也说明了用户在M此提交中，都没有通过编译，不进入排行榜
			if(g[i].nofs < 1)
				continue;
			//由于是按总分排序的，总分相同的在同一等级，总分不同的r+1,到下一个等级中排序
			//同时temp记录当前等级的总分是多少
			if(temp != g[i].sum){
				r = i+1;
				temp = g[i].sum;
			}
			System.out.print(r + " ");  //格式化输出，首先是等级r
			System.out.print(g[i].id + " " + g[i].sum); //接着输出用户的id和总分
			for(int j=0;j<g[i].s.length;j++){  //输出用户K个问题的得分情况
				//得分有可能有记录，也有可能为-1,此时输出的是"-"
				if(g[i].s[j] != -1){   
					System.out.print(" " + g[i].s[j]);
				}else{
					System.out.print(" -");
				}
			}
			System.out.println();  //输出完一个用户的成绩后换行
		}
		
		scan.close();
	}
	
	//每个用户对应的成绩描述
	static class grade{
		String id;  //记录用户id
		int[] s;    //记录所有问题对应的分数
		int p = 0;  //记录满分题目的数目，默认为0
		int nofs = 0;  //记录该用户有没有通过的题目，如果没有，0说明该用户的不会进入到排行榜中
		int sum = -1;  //总分默认为-1,代表一道题都没有通过；如果有，立即更新为0，代表是有分数的
		//初始化用户成绩对象，包括接收用户的id,K用来初始化成绩数组s的大小
		grade(int i,int K){
			//将用户的id转换为5位的字符串，%05d,指定转为5位数，不够时用0填充
			this.id = String.format("%05d", i+1);
			s = new int[K];
			//将用户在每个问题下的默认值初始化为-1,代表此时没有任何通过的提交
			for(int j=0;j<K;j++){
				s[j] = -1;
			}
		}
	}
}


