package pat2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main7 {
	/**
	 * 
	 * @param args
	 */
	static int[] data;   //存储用户输入的数据
	static int[] tempresult;   //存储中间某一过程的结果
	static int N;   //数据的个数
	static boolean isHeapInsert;  //标志位，判断是否为堆排序
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  //使用缓冲流加快读取速度

	public static void main(String[] args) {
		N = Integer.parseInt(getInput());  //获取用户输入的数字个数
		data = new int[N]; //使用data数组来保存原始的数据
		tempresult = new int[N];  //使用tempResult数组保存中间某一过程的排序
		isHeapInsert = false;  //默认不是堆排序
		String[] temp1 = getInput().split(" ");  //接受用户的数据，并使用“ ”分隔符将获取到的每一个数字保存到数组中
		//使用循环将用户的数据放入data数组中
		for(int i=0;i<N;i++){
			data[i] = Integer.parseInt(temp1[i]);
		}
		//使用相同的方法将结果的中间过程赋值给tempResult数组
		String[] temp2 = getInput().split(" ");
		for(int i=0;i<N;i++){
			tempresult[i] = Integer.parseInt(temp2[i]);
		}
		
		buildheap();
		heapSort();
		
		if(isHeapInsert){
			output();
		}else{
			int index = 0;
			for(int i=0;i<N-1;i++){
				if(tempresult[i]>tempresult[i+1]){
					index = i+1;
					break;
				}
			}
			for(int i=index;i>=1;i--){
				if(tempresult[i]>tempresult[i-1]){
					break;
				}else{
					int temp = tempresult[i];
					tempresult[i]=tempresult[i-1];
					tempresult[i-1]=temp;
				}
			}
			System.out.println("Insertion Sort");
			for(int i=0;i<N-1;i++){
				System.out.print(tempresult[i]+" ");
			}
			System.out.println(tempresult[N-1]);
		}
		
	}
	
	private static void output(){
		for(int i=0;i<N-1;i++)
			System.out.print(data[i]+" ");
		System.out.println(data[N-1]);
	}
	
	private static boolean isSame(){
		for(int i=0;i<N;i++){
			if(tempresult[i]!=data[i]){
				return false;
			}
		}
		return true;
	}
	
	private static void heapSort(){
		for(int i=0;i<data.length;i++){
			int temp = data[0];
			data[0]=data[data.length-1-i];
			data[data.length-1-i]=temp;
			MaxingHeap(data.length-1-i,0);
			if(isHeapInsert){
				break;
			}
			if(isSame()){
				System.out.println("Heap Sort");
				isHeapInsert=true;
			}
		}
	}
	
	private static void buildheap(){
		int start = 0;
		if(data.length%2 == 1){
			start = (data.length-3)/2;
		}else{
			start = (data.length-2)/2;
		}
		for(int i=start;i>=0;i--){
			MaxingHeap(data.length,i);
		}
	}
	
	private static void MaxingHeap(int length,int i){
		int left = i*2 + 1;
		int right = i*2 + 2;
		int parent = i;
		int largest = parent;
		if(left<length && data[parent]<data[left]){
			largest = left;
		}
		if(right<length && data[largest]<data[right])
			largest = right;
		if(largest!=parent){
			int temp = data[parent];
			data[parent]=data[largest];
			data[largest]=temp;
			MaxingHeap(length,largest);
		}
	}
	
	//使用字符缓冲输入流接受用户输入的一行数据，使用该方法专门读取
	private static String getInput(){
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
