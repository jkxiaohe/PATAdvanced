package pat3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author dell
 *题意解析：首先每个学生有2个成绩，一个是统考成绩Ge,一个是面试成绩Gi,按照总分从高到低进行排序，当总分相同时，按照Ge进行排序，如果仍然相同，那么这两个学生都要被录取
 *第一行有3个数字：N:申请人数，M:院校数目，K:可以填报志愿的数目
 *第二行：分别给出这M个学校录取的学生人数，这M个学校分别按照下标的顺序从0开始编号，0->M-1
 *接下来N行的数据：代表了N个学生，行号就相当于每个学生的标识符，0->N-1
 *N行数据的结构为：Ge分数，Gi分数，志愿1，志愿2，志愿3
 *最后的结果是要按照学校的编号顺序输出每个学校录取的学生，即：每一行的行号默认代表的是学校的编号，学生的编号使用行号来标志
 *
 */
public class Main6 {

	public static void main(String[] args) throws IOException {
		//使用BufferedReader可以加速读取每一行的数据，而StringTokenizer可以按照分隔符顺序读取每一行中的每一个数据
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer;
		tokenizer = new StringTokenizer(reader.readLine());
		
		//接收第一行输入的所有参数
		int n = Integer.parseInt(tokenizer.nextToken());
		int m = Integer.parseInt(tokenizer.nextToken());
		int k = Integer.parseInt(tokenizer.nextToken());
		
		//接收第二行：每个学校的相关信息，并把学校的所有信息都存放到allSchool数组中
		School[] allSchool = new School[m];
		tokenizer = new StringTokenizer(reader.readLine());
		for(int i=0;i!=m;i++){
			allSchool[i] = new School(i, Integer.parseInt(tokenizer.nextToken()));
		}
		
		//使用allApp数组来保存每一个申请人的信息，这里数组存储的是每一个申请人的对象
		Applicant[] allApp = new Applicant[n];
		for(int i=0;i != n;i++){
			tokenizer = new StringTokenizer(reader.readLine());
			allApp[i] = new Applicant(i, k, tokenizer);
		}
		
		Applicant[] sorted = new Applicant[n];  //sorted数组用来按照指定的排名方法对所有的人进行分数排名
		System.arraycopy(allApp, 0, sorted, 0, n);  //添加数据，并使用Arrays对象提供的方法快速排序
		Arrays.sort(sorted);
		School cur;     //cur用来临时存储当前取出来的学校对象
		//对所有的申请人进行遍历
		for(Applicant applicant : sorted){
			//根据学生填报的志愿，挨个取出他填报的学校，然后对学校进行判断
			for(int s : applicant.choice){  
				cur = allSchool[s];
				//如果当前学校的配额已超额，或者：配额不仅为0，并且该学生的成绩和该学校录取的最后一名的成绩也不相等（说明肯定比最后一名还小，因为学生的成绩已经进行了将序排序）
				if(cur.quota<0 || (cur.quota==0 && allApp[cur.lastAdmissionNum()].compareTo(applicant)!=0))
					continue;
				//能走到这里，能够说明的是：1.该学校的配额仍然有；2.配额虽然没有，但是接下来的这名学生的成绩和该校录取的最后一名同学的成绩相同，所以此时必须超额录取
				allSchool[s].admission.add(applicant.num);
				//没分配一个名额，就--
				if(cur.quota>0){
					allSchool[s].quota -= 1;
				}
				//该学生分配完成后退出循环，不再看他后面填报的志愿，因为他已经被录取了
				break;
			}
		}
		
		//按照学校的顺序，按需输出每个学校录取的学生即可
		for(School school : allSchool){
			System.out.println(school.outputAdmission());
		}
		reader.close();
		
	}
	
	//该类结构用于存储每个申请人的相关信息，这里实现了Comparable接口，目的是为了方便对每个类对象进行排序，类中的compareTo方法定义了排序时比较的规则
	public static class Applicant implements Comparable<Applicant>{

		public int num;  //学生的编号
		public int ge;  //Ge成绩
		public int gi;  //Gi成绩
		public int gf;  //Ge+Gi 的成绩和
		public int[] choice;  //填报的所有志愿
		
		//接收了3个参数，Num：学生的编号，k:志愿数目，tokenizer：接收了学生输入的所有数据，可以通过它顺序取出
		public Applicant(int num,int k,StringTokenizer tokenizer){
			this.num = num;
			choice = new int[k];
			ge = Integer.parseInt(tokenizer.nextToken());
			gi = Integer.parseInt(tokenizer.nextToken());
			gf = ge + gi;
			for(int i=0;i!=k;i++){
				choice[i] = Integer.parseInt(tokenizer.nextToken());
			}
		}
		
		@Override
		public int compareTo(Applicant o) {
			//定义了每个学生排名的方法：1.如果总成绩不同，那么便按照总成绩进行排序；2.否则，就按照Ge统考成绩进行排名
			if(gf != o.gf){
				return o.gf - gf;
			}
			return o.ge - ge;
		}
		
	}
	
	//使用该类来表示每个学校的结构
	public static class School{
		public int num;  //学校所在的下标
		public int quota;  //学校招生的名额
		public List<Integer> admission;  //学校最后录取的学生的编号
		
		public School(int num,int quota){  //初始化每个学校时，将学校的下标标志，名额这些变量全部添加进去，并初始化admission
			this.num = num;
			this.quota = quota;
			admission = new LinkedList<Integer>();
		}
		//默认情况下按照由高到低的顺序将每个学生添加进list集合中，为了处理并列排名的情况，这里要取出集合中存储的最后一个学生的下标，他的成绩也是该校录取的最低分数线
		public int lastAdmissionNum(){  
			return admission.get(admission.size()-1);
		}
		//最后的结果要在每一行输出每个学校录取的所有学生，这里便是为了处理这种情况
		public String outputAdmission(){
			String output = "";
			if(admission.isEmpty())   //如果当前学校没有录取学生，立马返回一个空的字符串
				return output;
			//有学生时，输出时要按照学生的标识升序挨个输出
			Collections.sort(admission);
			for(int i : admission){
				output += (" " + i);
			}
			return output.substring(1);
		}
	}
}
