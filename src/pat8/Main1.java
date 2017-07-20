package pat8;

import java.util.Scanner;
import java.util.Stack;

/**
 * 题意解析：根据栈的大小以及压入的栈的序列长度，判断给出的出栈序列是不是该入栈序列的某一种情况
 *      入栈元素的特点是：根据输入的数字N,将1,2,--N一次压入到栈中
 * 解题方法：由于栈中的数字是按照从小到大的顺序输入的，所以需要判断当前栈中数字i和出栈序列的数字x的大小关系，
 *       i>x,说明出栈的数字x已经在栈中了，此时如果再压栈，那么最后仍然需要先弹出x上面的元素，才能够将x弹出，所以此时
 *           需要尽快的将x弹出，再判断下一个出栈的数字是需要继续压栈还是出栈；
 *       i<=x,说明此时出栈的元素仍然没有进入到栈中，或者就是栈顶元素
 *            如果是栈顶元素，直接弹出即可，判断下一个，
 *            如果不是，说明当前出栈的元素还未进入到栈中，继续压栈
 *      
 */
public class Main1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m  = in.nextInt();  // 栈大小
		int n = in.nextInt();   //压入序列的长度
		int k = in.nextInt();   //出栈序列的个数
		//按照顺序进行判断，每次读取一个出栈序列，判断有没有可能是入栈的某种情况
		while(k-- != 0){     
			boolean can = true;  //通过can来判断有没有
			Stack<Integer> s = new Stack<Integer>();  //使用Stack来模拟栈的情况
            for(int i=1,j=0;j<n;++j){    //i代表入栈的数字，j表示当前入栈的个数
            	int x = in.nextInt();    //接收当前出栈的数字，判断是哪个数字的出栈情况
            	if(can){  //can默认为真，判断当前出栈的数字如果不可能的话，置为false,即当前序列不可能是任何一种出栈的情况
            		//i>x,说明此时的x已经在栈中了，如果当前栈为null,或者栈顶的元素不为x,说明该出栈序列是无法得到的，
            		//can=false，不再判断
            		//否则，说明当前元素是x,弹出当前栈顶元素，继续判断下一个元素
            		if(i > x){  
            			if(s.isEmpty() || (s.peek()!=x)){
            				can = false;
            			}else{
            				s.pop();
            			}
            		}else{
            			//说明i<=x,此时要把当前的数字i压入到栈中
            			//在压入栈的过程中，需要判断i<=x满足压栈的要求，不能够超过x,否则就不是最佳情况了
            			//i<=n，当前压入的数字没有超过压入序列的最大长度，并且栈的大小没有超过m
            			//只要当前栈的大小和序列的长度都满足要求，就不断的将元素压入栈中，直到溢出或将==x的元素压入栈 为止
            			for(;(i<=x)&&(i<=n)&&(s.size()<=m);++i){
            				s.push(i);
            			}
            			//当把一个新的数字压入栈后，对栈空和栈大小和栈顶的元素进行判断，
            			if((!s.isEmpty())&&(s.size()<=m)&&(s.peek()==x)){
            				s.pop();
            			}else{
            				can = false;
            			}
            		}
            	}
            }
			System.out.println(can ? "YES" : "NO");
		}
		
	}
	
}
