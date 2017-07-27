package pat10;

import java.util.Scanner;
import java.util.TreeSet;

public class Main4 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int l = in.nextInt();
		int h = in.nextInt();
		TreeSet<Stu> t1 = new TreeSet<Stu>();
		TreeSet<Stu> t2 = new TreeSet<Stu>();
		TreeSet<Stu> t3 = new TreeSet<Stu>();
		TreeSet<Stu> t4 = new TreeSet<Stu>();
		
		while(n != 0){
			Stu s = new Stu(in.nextInt(),in.nextInt(),in.nextInt());
			if(s.d<l || s.c<l){
				n--;
				continue;
			}
			if(s.d>=h && s.c>=h){
				t1.add(s);
			}else if(s.d>=h){
				t2.add(s);
			}else if(s.d>=s.c){
				t3.add(s);
			}else{
				t4.add(s);
			}
			n--;
		}
		System.out.println(t1.size()+t2.size()+t3.size()+t4.size());
		print(t1);
		print(t2);
		print(t3);
		print(t4);
		in.close();
		
	}
	
	static void print(TreeSet<Stu> tree){
		for(Stu s:tree){
			System.out.println(s.id+" "+s.d+" "+s.c);
		}
	}
}

class Stu implements Comparable<Stu>{
	int id,d,c,sum;
	Stu(int id,int d,int c){
		this.id = id;
		this.d = d;
		this.c = c;
		this.sum = d+c;
	}
	@Override
	public int compareTo(Stu o) {
		if(o.sum != this.sum){
			return o.sum - this.sum; 
		}else{
			if(o.d != this.d){
				return o.d - this.d;
			}else{
				return this.id - o.id;
			}
		}
	}
}
