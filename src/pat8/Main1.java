package pat8;

import java.util.Scanner;
import java.util.Stack;

/**
 * 题意解析：根据栈的大小以及压入的栈的序列长度，判断给出的出栈序列是不是该入栈序列的某一种情况
 *      入栈元素的特点是：根据输入的数字N,将1,2,--N一次压入到栈中
 * 解题方法：
 *      
 */
public class Main1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m  = in.nextInt();
		int n = in.nextInt();
		int k = in.nextInt();
		while(k-- != 0){
			boolean can = true;
			Stack<Integer> s = new Stack<Integer>();
            for(int i=1,j=0;j<n;++j){
            	int x = in.nextInt();
            	if(can){
            		if(i > x){
            			if(s.isEmpty() || (s.peek()!=x)){
            				can = false;
            			}else{
            				s.pop();
            			}
            		}else{
            			for(;(i<=x)&&(i<=n)&&(s.size()<=m);++i){
            				s.push(i);
            			}
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
