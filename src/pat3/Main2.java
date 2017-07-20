package pat3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author dell
 *题目的意思是：第一行：接收用户的总数目N，接收L层内（包括L层）粉丝数量转发的数目
 *接下来总共有N+1行：
 *N行：每行都代表一个用户，如：用户1，用户2,***，用户N： 第一个数字代表该用户关注了几个用户，后面依次列出这几个用户的下标
 *N+1行：所要查询的用户数目，再给出所要查询的用户的下标位置
 */
public class Main2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); //用户数目
		int l = in.nextInt(); //转发的层数
		User[] users = new User[n]; //使用数组存放N个用户对象，再使用一个循环对这些用户进行初始化
		for(int i=0;i<n;i++)    
			users[i] = new User();
		
		//在这里用户的下标是从0开始，所以需要对每次接收到的数字-1
		for(int i=0;i<n;i++){
			int m = in.nextInt();  //m为用户i关注的对象个数，即用户i为后面这些人的一个粉丝
			for(int j=0;j<m;j++){
				users[in.nextInt()-1].addFollower(i);  //将用户i的下标加入到这些人的粉丝列表中
			}
		}
		
		int k = in.nextInt();  //所要查询的用户数目
		int[] userIds = new int[k];  //存储所要查询的这些用户的下标
		for(int i=0;i<k;i++)      
			userIds[i] = in.nextInt()-1;
		
		boolean[] visited = new boolean[n];  //判断该用户有没有转发过了，转发过的不再转发
		for(int i=0;i<k;i++){      
			Arrays.fill(visited, false);  //将visited数组中的每个值都初始化为false,表示没有被转发过
			int userId = userIds[i];
			List<Integer>  forwards = new ArrayList<>();  //forwards列表存储从第一层到第L层所有转发过的数目
			Queue<Integer> queue = new LinkedList<>();  //queue表示当前层转发的用户，用来读取每层下面一层的粉丝
			int level = 0; //level默认为0层，所以遍历的时候需要level<L即可
			queue.add(userId);  //首先将当前需要遍历的用户的id放入到queue队列中，再依次取出它的下一层所有的粉丝数
			visited[userId] = true;   //标记该用户已经被访问过，在这里是使用数字下标来标记每一个用户的
			while(level++ < l && !queue.isEmpty()){  //只要当前没有遍历到第L层，并且当前节点下面让然有其他节点，即queue队列不为空
				int size = queue.size();  //获取队列的大小，即当前层的粉丝数量
				while(size-- > 0){  //对每个粉丝进行遍历，判断在它的下面有没有其他的粉丝了
					List<Integer> f = users[queue.poll()].followers;  //弹出队列中当前节点的下标，并使用users数组获取该下标对应的用户，获取到它的粉丝列表
					for(int j : f){  //对对该用户下的所有粉丝列表进行遍历，将每个粉丝对应的下标值放入forwards数组和queue队列中
						queue.add(j);
						//这里有一个坑，一个人的粉丝可能也是另一个人的粉丝，所以要对重复的重复的节点进行一个判断，即使用visited有没有被访问过进行判断
						//如果该节点下的用户已经转发过，或者为根节点，那么不会计数
						if(!visited[j]){
							forwards.add(j);
							visited[j] = true;
						}
					}
				}
			}
			System.out.println(forwards.size());  //由于是在while循环中，会遍历输出每个用户所有L层内粉丝转发数量的大小
		}
		
	}
	
	//使用类对象保存每一个用户的信息：包括了该用户可能拥有的粉丝的数量，在这里使用一个列表List来保存所有的粉丝
	private static class User{
		List<Integer> followers = new ArrayList<>();
		public void addFollower(int userId){
			followers.add(userId);
		}
	}
}
