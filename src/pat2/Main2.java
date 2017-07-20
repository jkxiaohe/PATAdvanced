package pat2;

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); //接收用户输入的数据
		int limit = (int)Math.sqrt(n); //最大比较到该数时停止比较
		int maxlen = 0; //记录连续因子的最大长度
		int s = 0; //记录连续因子的第一个
		boolean flag = true;  //如果当前数字不存连续的因子，那么是一种特殊的情况，单独输出
		//从数字2开始计数，并且最大值是sqrt(n),从sqrt(n)向后一定会超过n
		for(int i=2;i<=limit;++i){
			//如果当前改数存在因子，那么将标志flag标记为false
			if(n%i==0){    
				flag = false;
			}
			//start,len,temp都为临时变量，用来记录因子的开始，长度，数值
			int start = i;
			int len = 0;
			double temp = n;
			//使用该循环一直连续向后迭代，并记录迭代的数据
			while(temp%start == 0){
				len++;
				temp/=start;
				start++;
			}
			//判断迭代的对象与全局记录的大小关系，如果临时变量记录的更大，那么更新全局的记录
			if(len > maxlen){
				maxlen = len;
				s = i;
			}
		}
		if(flag){
			System.out.println(1);
			System.out.println(n);
		}else{
			System.out.println(maxlen);
			System.out.print(s++);
			for(int i=1;i<maxlen;++i,++s){
				System.out.print("*"+s);
			}
		}
	}
}
