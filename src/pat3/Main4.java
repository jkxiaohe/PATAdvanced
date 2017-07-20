package pat3;

import java.util.Scanner;

/**
 * 
 * @author dell
 *该题目的主要意思是使用给定的公式计算数字对应的下标位置
 *公式为：h = key % size;  h为结果，最后就是要输出每个key对应的h值
 */
public class Main4 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		//用户输入的表的大小，同时对用户输入的该表的大小进行质数的判断，如果不是质数，那么++,直到是质数为止
		int maxSize = in.nextInt();  
		int size = maxSize;
		while(!isPrime(size)){
			size++;
		}
		
		int n = in.nextInt();  //用户输入的数字个数，使用StringBuilder来接收最后的结果
		StringBuilder builder = new StringBuilder();
		/**
		 * h :保存数字对应的下标
		 * inserted数组：保存哈希表中的某个位置是否有值
		 * 在初始化这些变量的同时，顺便接收第一个数字，对这些变量进行一个初始化
		 */
		int h = in.nextInt() % size;
		builder.append(h);
		boolean[] inserted = new boolean[size];
		inserted[h] = true;
		for(int i=1;i<n;i++){
			int number = in.nextInt();
			number %= size; //使用公式对该数字求余
			//如果哈希表中的当前位置没有值，那么便可以将该数字放入表中中，位置为下标，同时使用inserted标记该位置以及有值了
			if(!inserted[number]){   
				builder.append(" ");
				builder.append(number);
				inserted[number] = true;
			}else{
				//如果当前位置以及有值了，Quadratic probing (with positive increments only) is used to solve the collisions.
				//题目的意思是使用二次探测（即平方的方法）来避免哈希表的冲突，所以有下面
				int j = 1;
				//j从1开始逐渐增大，判断二次探测后会不会重复，如果不重复，加入；否则，标记为"-",不合适
				for(;j<size;j++){
					h = (number+j*j) % size;  
					if(!inserted[h]){
						builder.append(" ");
						builder.append(h);
						inserted[h] = true;
						break;
					}
				}
				if(j>=size){
					builder.append(" -");
				}
			}
		}
		
		System.out.println(builder);
	}
	
	private static boolean isPrime(int number){
		if(number == 1)
			return false;
		if(number==2 || number==3 || number==5)
			return true;
		if(number % 2 == 0)
			return false;
		int max = (int) (Math.sqrt(number) + 1);
		for(int i=2;i<=max;i++){
			if(number % i == 0)
				return false;
		}
		return true;
	}
	
}
