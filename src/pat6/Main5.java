package pat6;

import java.util.Scanner;

/**
 * @author gljg
 *根据用户输入的指定的位置，将数字进行移动
 *形式为： 值：    S1   S1
 *     下标：1  ->36
 *     目标：36   5
 * 这里使用到了3个数组存放所需要的所有数据，sq存放目标地址，ans和res轮转存放值和下标，在这些数组中值是按照1-54的顺序存放的，
 * 最后只需要根据数组的值到str数组中找到对应的牌就可以了
 */
public class Main5 {

	public static void main(String[] args) {
		String[] str = new String[54];
		int ct = 0;
		//将所有的纸牌数字赋值给
		for(int i=0;i<54;i++){
			switch(i/13){
			case 0:
				str[ct++] = "S" + (i%13 + 1);
				break;
			case 1:
				str[ct++] = "H" + (i%13 + 1);
				break;
			case 2:
				str[ct++] = "C" + (i%13 + 1);
				break;
			case 3:
				str[ct++] = "D" + (i%13 + 1);
				break;
			}
		}
		
		str[52] = "J1";
		str[53] = "J2";
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] sq = new int[54];
		int[] res = new int[54];
		int[] ans = new int[54];
		//使用此for循环接收指定的排序数字，同时将res数组初始化为纸牌的序号1-54
		for(int i=0;i<54;i++){
			sq[i] = in.nextInt();
			res[i] = i+1;    
		}
		
		//进行N轮的洗牌，这里对奇偶进行判断实际上是为了区分res和ans中存放的是纸牌数值还是下标
		//方便在下一轮循环中进行变换，
		//sq中存放的是当前第j个数字放入到第sq[j]个位置上去，用ans来存放变换上去的数字，同时能够保证res中的第sq[j]个位置上的数没变
		//下一轮变换时，ans存放了上一轮排序后的数字，使用sq[j]再来变换时，将结果存放到另一个数组res中
		for(int i=0;i<N;i++){
			if(i%2 == 0){
				for(int j=0;j<54;j++){
					ans[sq[j]-1] = res[j];
				}
			}else{
				for(int j=0;j<54;j++){
					res[sq[j]-1] = ans[j];
				}
			}
		}
		
		//根据变换的次数得出此时最后的结果存放在ans和res中的那一个，再取出其中的值作为下标，到str数组中输出纸牌的值
		//res和ans最后的结果中存放的是纸牌的下标，且是从1开始编号的，而str是从0开始编号的，所以要从res或ans的值中-1，取得对应的纸牌值
		if(N%2 == 0){
			for(int i=0;i<53;i++){
				System.out.print(str[res[i]-1]+" ");
			}
			System.out.println(str[res[53]-1]);
		}else{
			for(int i=0;i<53;i++){
				System.out.print(str[ans[i]-1]+" ");
			}
			System.out.println(str[ans[53]-1]);
		}
		
	}
}
