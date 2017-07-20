package pat7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author gljg
 *题意解析：求出每门课程的选修人数，以及选修的学生名单，学生的姓名要根据字典序进行排序
 *      在这里关键是对学生的名字进行一个唯一的升序方式的编号，由于学生的名字由4个字符组成，前3个是大写字母，后1个是数字
 *      所以可以用数字的方式来唯一的表示一个学生，并且是按照字典序排列的，方法是：
 *      字母 字母 字母 数字，数字的单位是10，而字母有26种可能
 *      第1个字母的数字表示：*26*26*10，    第2个字母的数字表示:*26*10
 *      第3个字母的数字表示：*10，                       第4个数字的表示：字面值
 *      
 *      同时，要存储每门课程下选修的学生名单，在这里使用了ArrayList<LinkedList<Integer>> course集合来存放的，
 *    首先外部的集合是ArrayList，用来存放所有的课程，
 *    内部的类型是一个LinkedList<Integer>,用来存放所有选修了该课程的学生
 */
public class Main3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int studentSum,courseSum;
		studentSum = in.nextInt();
		courseSum = in.nextInt();
		char[] name = new char[4];  
		ArrayList<LinkedList<Integer>> course = new ArrayList<LinkedList<Integer>>(courseSum);
		//初始化每门课程的LinkedList集合
		for(int i=0;i<courseSum;i++){
			course.add(new LinkedList<Integer>());
		}
		//courseIdx用来存放没门课程的值，
//		int[] courseIdx = new int[20];
		int nameInt;  //学生名字
		int chooseSum;  //选修的课程数目
		
		//对每个学生进行遍历
		for(int i=0;i<studentSum;i++){
			name = in.next().toCharArray();
			//对学生的名字按照字典序的方式生成一个唯一的数字值
			nameInt = (name[0]-'A')*26*26*10 + (name[1]-'A')*26*10 + (name[2]-'A')*10 + (name[3]-'0');
			chooseSum = in.nextInt();  
			//将每门课的学生名字的数字编号放入集合中
			for(int j=0;j<chooseSum;j++){
//				courseIdx[j] = in.nextInt();
//			    course.get(courseIdx[j]-1).push(nameInt);
				course.get(in.nextInt()-1).push(nameInt);
			}
		}
		
		for(int i=0;i<course.size();i++){
			System.out.printf("%d %d\n",i+1,course.get(i).size());
			//对集合进行排序，传入制定的比较器
/*			course.get(i).sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1-o2;
				}
			});*/
			Collections.sort(course.get(i));
			Iterator<Integer> iterator = course.get(i).iterator();
			while(iterator.hasNext()){
				//将存储的数字还原为字符
				Integer ite = iterator.next();
				name[3] = (char) (ite%10 + '0');
				name[2] = (char) ((ite/10)%26 + 'A');
				name[1] = (char) ((ite)/10/26%26 + 'A');
				name[0] = (char) (ite/10/26/26 + 'A');
				System.out.println(name);
			}
		}
	}
	
}
