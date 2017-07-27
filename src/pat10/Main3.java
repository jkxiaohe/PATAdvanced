package pat10;

import java.util.*;

/**
 * @author gljg
 * 题目解析：和乙级的德才论一样，这里为了节省内存，使用了sign标志来表明该学生属于第几批，这样只就不需要使用4个集合来存储4类学生了；
 *       输出的时候按照批次一次输出即可。
 */
public class Main3 {

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
			if(df>=H && cf>=H){
				list.add(new Student(id,df,cf,1));
			}
			else if(cf<L || df<L){
				continue;
			}
			else if(cf<H && df>=H){
				list.add(new Student(id,df,cf,2));
			}
			else if(cf<H && df<=H && df>=cf){
				list.add(new Student(id,df,cf,3));
			}
			else{
				list.add(new Student(id,df,cf,4));
			}
		}
		
		System.out.println(list.size());
		
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();
		StringBuilder sb4 = new StringBuilder();

		Iterator<Student> it = list.iterator();
		while(it.hasNext()){
			Student tmp = it.next();
			if(tmp.sign == 1){
				sb1.append(tmp.id + " " + tmp.df + " " + tmp.cf + "\n");
			}else if(tmp.sign == 2){
				sb2.append(tmp.id + " " + tmp.df + " " + tmp.cf + "\n");
			}else if(tmp.sign == 3){
				sb3.append(tmp.id + " " + tmp.df + " " + tmp.cf + "\n");
			}else{
				sb4.append(tmp.id + " " + tmp.df + " " + tmp.cf + "\n");
			}
		}
		
		System.out.println(sb1.toString() + sb2.toString() + sb3.toString() + sb4.toString());
	}
	
	static class Student implements Comparable<Student>{
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
	
}
