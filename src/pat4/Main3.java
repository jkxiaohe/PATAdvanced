package pat4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		System.out.print(n + "=");
		//处理特殊的情况：1.如果是1，由于1不是质数，直接输出；2.如果输入的数据本来就是一个质数，也是直接输出
		if(n==1 || isPrime(n)){
			System.out.println(n);
			return;
		}
		
		int prime = 2;  //质数因子从最小值2开始测试
		//这里有2个List集合，第一个存储质因子，第二个存储指数
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		//当n被所有的质因子表示时，此时相除的结果一定是1，通过N当前是否为1判断n是否已经被质因子表示完
		while(n != 1){
			//总共分了3中质因子的情况：1.当前数不是质数，并且prime是他的一个质因子；
			//2.当前数本数本身就是一个质数了，此时质因子即为它本身，将n放入到list集合中，并退出循环，此时已经获得了所有的质因子
			//3.当前prime不是n的质因子，并且n仍然是一个合数，说明它还存在质因子，此时prime指到下一个质数的位置，指到找到合数n的下一个质因子为止
			if(n%prime == 0){   
				n /= prime;  //找到一个质因子后，n除以该数得到商
				//2种情况：(1).刚开始的时候，list1中什么质因子都没有，此时应该直接加入，
				//(2)List1是按照质因子从小到大的顺序排的，所以判断当前list存储的最后一个质因子是不是和prime一样，此时又分2中情况：
				//<1>质因子是不同的，将新的质因子加入到List1集合中，并在List2中相应位置上在添加1，说明当前该质因子有1个
				//<2>质因子是相同的，此时要更新List2对应质因子位置上的指数，此时应该讲对应位置上的指数+1
				if(list1.isEmpty() || list1.get(list1.size()-1)!=prime){
					list1.add(prime);
					list2.add(1);
				}else if(list1.get(list1.size()-1) == prime){
					list2.set(list2.size()-1, list2.get(list2.size()-1)+1);
				}
			}else if(isPrime(n)){
				list1.add(n);
				list2.add(1);
				break;
			}else{
				prime = next(prime);
			}
		}
		
		for(int j=0;j<list1.size();j++){
			System.out.print(list1.get(j));
			if(list2.get(j)>1)
				System.out.print("^" + list2.get(j));
			if(j+1 != list1.size()){
				System.out.print("*");
			}
		}
		
	}
	
	//从2开始，循环递增查找下一个质数
	private static int next(int p){
		p++;
		while(!isPrime(p)){
			p++;
		}
		return p;
	}
	
	private static boolean isPrime(int a){
		int limit = (int) Math.sqrt(a);
		for(int i=2;i<=limit;i++){
			if(a%i == 0){
				return false;
			}
		}
		return true;
	}
	
	
}
