package pat10;

import java.util.*;

/**
 * @author gljg
 * 题目解析：和乙级的德才论一样，这里为了节省内存，使用了sign标志来表明该学生属于第几批，这样只就不需要使用4个集合来存储4类学生了；
 *       输出的时候按照批次一次输出即可。
 */
public class Main2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int L = in.nextInt();
		int H = in.nextInt();
		Set<Student> list = new TreeSet<Student>();
		
		for(int i=0;i<N;++i){
			int id = in.nextInt();
			int df = in.nextInt();
			int cf = in.nextInt();
			if(cf<L || df<L){
				continue;
			}
			else if(df>=H && cf>=H){
				list.add(new Student(id,df,cf,1));
			}
			else if(df>=H){
				list.add(new Student(id,df,cf,2));
			}
			else if(cf<H && df>=cf){
				list.add(new Student(id,df,cf,3));
			}
			else{
				list.add(new Student(id,df,cf,4));
			}
		}
		
		System.out.println(list.size());
		
		for(int i=1;i<=4;++i){
			Iterator<Student> it = list.iterator();
			while(it.hasNext()){
				Student tmp = it.next();
				if(tmp.sign == i){
					System.out.printf("%d %d %d\n",tmp.id,tmp.df,tmp.cf);
				}
			}
		}
	}
}

class Student implements Comparable<Student>{
	int id;
	int df;
	int cf;
	int sum;
	int sign;
	Student(int id,int df,int cf,int sign){
		this.id = id;
		this.df = df;
		this.cf = cf;
		this.sign = sign;
		this.sum = df + cf;
	}
	@Override
	public int compareTo(Student o) {
		if(this.sum != o.sum){
			return o.sum - this.sum;
		}else{
			if(this.df != o.df){
				return o.df - this.df;
			}else{
				return this.id - o.id;
			}
		}
	}
}
