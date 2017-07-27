package pat9;

import java.util.Scanner;

/**
 * 题意解析：输入一串字符串，找出这个字符串最长回文串的长度
 *   思路：从这个字符串的第一个字符开始，逐个遍历这个字符串中的所有字符，对于当前遍历的字符，同时向前和向后比较他们的字符是否相等，记录当前
 *      以该字符为起点的最大回文串长度；
 *      接下来到下一个字符，对该字符进行相同的操作，找出该字符的最大回文串，最后只要找出所有字符的最大回文的那个串就可以了；
 *      需要注意的是：最终回文串的长度时偶数和奇数时情况不同，由于结果是未知的，所以需要对奇偶的结果都进行判断，找出最大的那个回文串
 *              如果是奇数，那么从查找的字符左右两边同时开始相加减就可以了；
 *              如果是偶数，那么就没有哪个恰好的位置，此时要么对左边的数+1,要么对右边的数-1，这样才能够保证2边是对称的。
 */
public class Main9 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		int len = str.length();  //该字符串的长度
		int maxLen = 0; //最大回文长度
        int tmpLen;   //字符串中某个字符的回文长度
        
    	/**
    	 * 奇数时，从左右2边的第1个数开始比较，如果相同，逐渐向2边扩大查找
    	 * i-temLen是左边对称的字符，i+tmpLen是右边对称的字符，
    	 * >=0确保左边的字符正确，<len确保右边的字符在当前字符串范围内，
    	 * 如果在2边找到一对新的字符相等，那么tmpLen++;
    	 * 注意tmpLen的初值为1，代表的是奇数中的那个中间的数，该数左右2边共享，所以单独成1
    	 * 最后更新回文串的长度时，要-1
    	 */
        for(int i=0;i<len;i++){
        	tmpLen = 1;   
        	while((i-tmpLen)>=0 && (i+tmpLen)<len){
        		if(str.charAt(i-tmpLen) == str.charAt(i+tmpLen)){
        			tmpLen++;
        		}else{
        			break;
        		}
        	}
        	if((2*tmpLen-1) > maxLen){
        		maxLen = 2*tmpLen - 1;
        	}
        }
        
    	/**
    	 * 偶数时，要使2边对称，左右有一方必须改变，在这里使左边+1,使得2边能够保持对称,
    	 * tmpLen初值为1，最后需要-2
    	 */
        for(int i=0;i<len;i++){
        	tmpLen = 1;
        	while((i-tmpLen+1)>=0 && (i+tmpLen)<len){
        		if(str.charAt(i-tmpLen+1) == str.charAt(i+tmpLen)){
        			tmpLen++;
        		}else{
        			break;
        		}
        	}
        	if((2*tmpLen-2) > maxLen){
        		maxLen = 2*tmpLen-2;
        	}
        }
        
        System.out.println(maxLen);		
	}

}
