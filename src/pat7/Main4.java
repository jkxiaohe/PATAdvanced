package pat7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main4 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int studentSum = in.nextInt();
		int courseSum = in.nextInt();
		ArrayList<ArrayList<String>> course = new ArrayList<ArrayList<String>>(courseSum);
		for(int i=0;i<courseSum;i++){
			course.add(new ArrayList<String>());
		}
		
		int sno;
		for(int i=0;i<studentSum;i++){
			String name = in.next();
			int chooseSum = in.nextInt();
			for(int j=0;j<chooseSum;j++){
				sno = in.nextInt();
				//根据每个课程的编号，将该学生的名字加入到对应的课程下去
				course.get(sno-1).add(name);
			}
		}
		
		for(int i=0;i<courseSum;i++){
			//对该课程下的所有学生的名字进行排序，默认为字典序
			Collections.sort(course.get(i));
			int size = course.get(i).size();
			System.out.println((i+1)+" "+size);
/*			Iterator<String> it = course.get(i).iterator();
			while(it.hasNext()){
				System.out.println(it.next());
			}*/
			for(int j=0;j<size;j++){
				System.out.println(course.get(i).get(j));
			}
		}
		
	}
	
}
