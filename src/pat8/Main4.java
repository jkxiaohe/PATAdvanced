package pat8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Main4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) {
		String[] temp = getInput().split(" ");
		int N = Integer.parseInt(temp[0]);
		String start = temp[1];
		//特殊情况的输出
		if(Integer.parseInt(start) == -1){
			System.out.println("0 -1");
			return;
		}
		
		//使用3个map键值对，由于每个节点的格式为：地址(address)  值(key)  下一地址(next)
		//所以需要使用2个map来完全存储一个节点的值
		//allMap,nextMap用来存储节点的地址，值，     节点的地址，下一跳地址
		//addressMap用来存储最后的结果，这里使用的实现类是TreeMap，因为TreeMap能够根据key按照字典序进行自然排序，所以将Integer放在key的位置
		//addressMap存放的格式为：值(key),地址(address) ,会自动根据key值从小到大排序
		Map<String,Integer> allMap = new HashMap<String,Integer>();
		Map<Integer,String> addressMap = new TreeMap<Integer,String>();
		Map<String,String> nextMap = new HashMap<String,String>();
		
		//接收N个节点，用2个map存放这些数据
		for(int i=0;i<N;i++){
			String[] temp2 = getInput().split(" ");
			int key = Integer.parseInt(temp2[1]);
			String address = temp2[0];
			String next = temp2[2];
			allMap.put(address, key);
			nextMap.put(address, next)
;		}
		
		//count用来统计当前链表的节点总数
		int count = 0;  
		//此while遍历allMap,将其中的所有节点数据取出，按照值：地址的方式放入到addressMap中
		while(nextMap.get(start) != null){
			count++;
			int key = allMap.get(start);
			addressMap.put(allMap.get(start), start);
			start = nextMap.get(start);
		}
		
		//对于排好序的链表，有一个特点：
		//当前节点的next是下一个节点的address
		//这里使用iterator3来获链表头结点的地址,iterator2来获取下一个节点，iterator获取当前节点
		Iterator<Entry<Integer, String>> iterator3 = addressMap.entrySet().iterator();
		Entry<Integer, String> mapEntry = iterator3.next();
		System.out.println(count + " " + mapEntry.getValue());
		Iterator<Entry<Integer, String>> iterator = addressMap.entrySet().iterator();
		Iterator<Entry<Integer, String>> iterator2 = addressMap.entrySet().iterator();
		iterator2.next();
		while(iterator.hasNext()){
			Entry<Integer, String> address = iterator.next();
			if(iterator2.hasNext()){
				Entry<Integer, String> next = iterator2.next();
				System.out.println(address.getValue() + " " + address.getKey() + " " + next.getValue());
			}else{
				System.out.println(address.getValue() + " " + address.getKey() + " -1");
			}
			
			
		}
		
		
		
	}
	
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
