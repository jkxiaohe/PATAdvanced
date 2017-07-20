package pat4;

import java.util.*;

/**
 * 
 * @author xiaohe
 *题意解析：从一个堆栈中输出中间的数，并且该数是栈中的数按照从小到大排列中的那个位置上的最小的数
 *对于奇数：(n+1)/2  ,  对于偶数： n/2
 *经过分析可得，奇数和偶数都可以使用公式：(n+1)/2,偶数+1除2的结果是一样的
 *
 */
public class Main1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);  
		int n = in.nextInt();   //元素的总数
		Stack<Integer> stack = new Stack<Integer>();  //由于题目的要求是先进后出，后进先出，所以考虑用堆对象来存储,里面存储的是数字 
		int size = 0;     //记录当前栈中有多少个元素
		//使用数组来存放栈中的每一个对象，使用数组的下标可以方便的对栈中的每个数据进行排序，数组的值对应该位置上有没有数
		int[] count = new int[100001]; 
		for(int i=0;i<n;i++){
			//循环接收用户输入的每一行数据，有3中类型：push ,pop ,peekMedian,  可以通过字符串第2个位置上的字符进行区分：u,o,e
			String command = in.next();
			//push，将数字放入栈中，数组相应位置上的值++,代表该位置有值
			if(command.charAt(1) == 'u'){
				int num = in.nextInt();
				stack.add(num);
				size++;
				count[num]++;
			}else if(command.charAt(1) == 'o'){
				//pop，2中情况：栈为空，输出为"Invalid" ;  弹出栈顶的元素，并将数组中相应位置上的该元素--,说明没有值
				if(size == 0){
					System.out.println("Invalid");
				}else{
					int m = stack.pop();
					size--;
					count[m]--;
					System.out.println(m);
				}
			}else{
				//peekMedian , 2中情况：栈为空，输出"Invalid" ; 查找当前栈中所有数据最小值得那个中间数，这里通过getMid方法来查找
				if(size == 0){
					System.out.println("Invalid");
				}else{
					System.out.println(getMid(count,(size+1)/2));
				}
			}
			
		}
		
	}
	
	//用于查找栈中的那个中间数，Mid为中间数的位置，对于count数组，由于其下标是升序排序的，并且数组值是否为1可以知道该下标对应的数有没有在栈中，所以
	//可以直接对count数组进行循环，只要找到的元素位置在mid处，便将该数返回（栈中的每一个数对应的都是count数组的下标，而count数组的值是用来标记该数在不在栈中的）
	private static int getMid(int[] count,int mid){
		int sum = 0;
		for(int i=0;i<count.length;i++){
			sum += count[i];
			if(sum >= mid)
				return i;
		}
		return 0;
	}
	
}
