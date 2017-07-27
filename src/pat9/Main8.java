package pat9;

import java.util.*;

/**
 * 题目解析：求每个学生的课程表
 * 思路：每个学生对应的有多个课程表，可以用Map来存储最终的结果，key为学生的名字，每个学生对应的有多门课程，所以需要用LinkedList来存储多门课程,
 *     最后求出LinkedList的size即为该学生选课的总数，对linkedList中存储的课程编号进行排序，按照字典序输出所选课程
 */
public class Main8 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Map<String,LinkedList<Integer>> map = new HashMap<String,LinkedList<Integer>>();
		int nOfStudent = in.nextInt();
		int nOfCourse = in.nextInt();
//		String[] Course = new String[nOfStudent];   
		/**
		 * 对所有课程进行遍历，根据每个课程的编号及学生的总数，将该课程的编号添加到每个学生对应的list集合中
		 * 如果某个学生对应的课程集合为Null，那么久新建后添加
		 */
		for(int i=0;i<nOfCourse;i++){
			int courseId = in.nextInt();
			int studentCount = in.nextInt();
			for(int j=0;j<studentCount;j++){
				String temp = in.next();
				if(map.get(temp) == null){
					map.put(temp, new LinkedList<Integer>());
				}
				map.get(temp).add(courseId);
			}
		}
		
		//对map中存储的所有学生依次输出
		for(int i=0;i<nOfStudent;i++){
			String temp = in.next();
			System.out.print(temp + " ");
			LinkedList<Integer> list = map.get(temp);
			if(list == null){
				System.out.println(0);
				continue;
			}
			System.out.print(list.size());
			Collections.sort(list);  //对课程的编号进行排序
			Iterator<Integer> it = list.iterator();
			while(it.hasNext()){
				System.out.print(" " + it.next());
			}
			System.out.println();
		}
		in.close();
	}
	
}
