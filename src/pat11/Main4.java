package pat11;

//1060
import java.util.Scanner;

/**
 * @author gljg
 * 题目解析：有理数的四则运算，注意将每个数都化为最简形式，假分数化为带分数的形式；
 *   思路：有理数四则运算只要按数学方法来算就好了，关键是对每个分数进行化简，这是最难的地方；
 *      可以使用一个专门化简分数的函数，这里是format;
 *      format需要考虑的问题有： 1.分子的正负，用于判断是否需要给分数加括号；
 *         2.分子为0时情况特殊，直接返回0；  
 *         3.将分子分母进行约分，化为最简形式；
 *         4.如果是假分数->带分数；如果是真分数->录入；
 */

public class Main4 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[] temp1 = in.next().split("/");
		int afz = Integer.parseInt(temp1[0]);
		int afm = Integer.parseInt(temp1[1]);
		String[] temp2 = in.next().split("/");
		int bfz = Integer.parseInt(temp2[0]);
		int bfm = Integer.parseInt(temp2[1]);
		
		int cfz = 0;
		int cfm = 0;
		
		String first = format(afz,afm);
		String second = format(bfz,bfm);
		String result = "";
		//+
		if(afm == bfm){
			cfz = afz + bfz;
			cfm = afm;
			result = format(cfz,cfm);
		}else{
			cfz = afz*bfm + bfz*afm;
			cfm = afm * bfm;
			result = format(cfz,cfm);
		}
		System.out.println(first + " + " + second + " = " + result);
		
		//-
		if(afm == bfm){
			cfz = afz - bfz;
			cfm = afm;
			result = format(cfz,cfm);
		}else{
			cfz = afz*bfm - bfz * afm;
			cfm = afm * bfm;
			result = format(cfz,cfm);
		}
		System.out.println(first + " - " + second + " = " + result);
		
		//*
		cfz = afz * bfz;
		cfm = afm * bfm;
		result = format(cfz,cfm);
		System.out.println(first + " * " + second + " = " + result);
		
		//除
		//除数为0的特殊情况
		if(second.equals("0")){
			result = "Inf";
		}else{
			//需要考虑负号的安放位置
			cfz = afz * bfm;
			cfm = afm * bfz;
			if(cfm < 0){
				cfm = -cfm;
				cfz = -cfz;
			}
			result = format(cfz,cfm);
		}
		System.out.println(first + " / " + second + " = " + result);
				
	}
	
	static String format(int fz,int fm){
		StringBuilder sb = new StringBuilder();
		//判断正负,以及分子为0的特殊情况
		boolean minus = false;
		if(fz < 0){
			sb.append("(-");
			fz = -fz;
			minus = true;
		}else if(fz == 0){
			sb.append("0");
			return sb.toString();
		}
		//化为最简形式
		//注意点：从2开始化简，直到2不是分子和分母的公因子为止，否则将i++,但i的大小不能超过分子和分母中的任何一个
	    for(int i=2;i<=fz && i<=fm;){
	    	if(fz%i==0 && fm%i==0){
	    		fz /= i;
	    		fm /= i;
	    	}else{
	    		i++;
	    	}
	    }
	    //如果是假分数，化为带分数的形式
	    if(fz >= fm){
	    	int left = fz/fm;
	    	int rFz = fz%fm;
	    	if(rFz == 0){
	    		sb.append(left);
	    	}else{
	    		sb.append(left + " " + rFz + "/" + fm);
	    	}
	    }else{
	    	//如果不是假分数，直接加入；
	    	sb.append(fz + "/" + fm);
	    }
	    if(minus)sb.append(")");
		return sb.toString();
	}
	
}
