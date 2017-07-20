package pat2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Main6 {

	/**
	 * 
	 * @param args
	 * 整体上使用数组来存取每个节点的信息，通过使用地址address作为下标，将以address的信息存储到每一个数组中，最后分别从这些数组中取值
	 */
	//使用字符流缓冲流快速读取用户输入的数据，一行一行的读取
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
	static int[] key;  //保存用户输入的key
	static int[] next;  //保存下一个地址
	static int[] isExist = new int[10005];  //
	static int start;  //开始的节点
	static int totalNode;  //总共的节点数目
	//保留下来的链表相关的信息
	static int[] L1address;
	static int[] L1key;
	static int[] L1next;
	//移除链表的相关信息
	static int[] L2address;
	static int[] L2key;
	static int[] L2next;
	//countL1 和 countL2分别用来计算2个链表的下一个节点指向
	static int countL1 = 0;
	static int countL2 = 0;
	
	public static void main(String[] args) {
		//对第一行读取到的字符串分隔，分别获取到开始节点和总共的节点数目
		String[] temp1 = getInput().split(" ");  
		start = Integer.parseInt(temp1[0]);
		totalNode = Integer.parseInt(temp1[1]);
		
		key = new int[1000000];
		next = new int[1000000];
		L1address = new int[totalNode];
		L1key = new int[totalNode];
		L1next = new int[totalNode];
		L2address = new int[totalNode];
		L2key = new int[totalNode];
		L2next = new int[totalNode];
		
		//循环接收用户输入的每个节点的信息，用address来表示其在数组下标中的位置，每个key对应的下一个节点都对应在next数组中
		for(int i=0;i<totalNode;i++){
			String[] temp2 = getInput().split(" ");
			int address = Integer.parseInt(temp2[0]);
			key[address] = Integer.parseInt(temp2[1]);
			next[address] = Integer.parseInt(temp2[2]);
		}
		
		process();
		
		DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
		String pattern = "00000";  //指定输出的数字格式为5位数
		decimalFormat.applyPattern(pattern);  //将指定的格式化规范应用于此模式
		//countL1-1代表最后一个节点
		for(int i=0;i<countL1-1;++i){
			L1next[i] = L1address[i+1];
			System.out.println(decimalFormat.format(L1address[i])+" "+L1key[i]+" "+decimalFormat.format(L1next[i]));
		}
		//最后一个节点的输出格式不同于其他的节点，所以要单独处理
		System.out.println(decimalFormat.format(L1address[countL1-1])+" "+L1key[countL1-1]+" -1");
		
		for(int i=0;i<countL2-1;++i){
			L2next[i] = L2address[i+1];
			System.out.println(decimalFormat.format(L2address[i])+" "+L2key[i]+" "+decimalFormat.format(L2next[i]));
		}
		System.out.println(decimalFormat.format(L2address[countL2-1])+" "+L2key[countL2-1]+" -1");
	}
	
	private static void process(){
		int address = start;
	    //isExist以每个节点的key值作为下标地址，默认情况下都为0，如果为0，说明该节点的值未被记录过，将该值++，说明已经被记录过了
		//isExist再遇到一个相同的key值时，key下标上对应的数组值已经不是0，说明已经被访问过了
		while(true){
			if(isExist[Math.abs(key[address])]==0){
				L1address[countL1] = address;
				L1key[countL1] = key[address];
				countL1++;
				isExist[Math.abs(key[address])]++;  //!=0,用来表示已经被访问过了
				address = next[address];
			}else{
				L2address[countL2] = address;
				L2key[countL2] = key[address];
				countL2++;
				isExist[Math.abs(key[address])]++;
				address = next[address];
			}
			if(address==-1)
				break;
		}
	}
	//用来接收用户输入的每一行数据，返回接收到的字符串
	public static String getInput(){
		String string = "";
		try {
			string = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
	}
}
