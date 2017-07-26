package pat9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *  题意解析：根据所有的通话记录，判断出所有的团伙，以及团花的头目，需要对通话记录统计
 *  
 */
public class Main3 {

	static List<Person> persons = new ArrayList<Person>();
	
	public static void main(String[] args) throws IOException {
		//使用缓冲输入流一次性读取数据
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] temp1 = reader.readLine().split(" ");
		int N = Integer.parseInt(temp1[0]);   //通话记录总数
		int K = Integer.parseInt(temp1[1]);   //团伙通话总时长的最低时间
		
		//link用于存储每个人的名字对应的编号，groups用于存放每个人所属组的信息
		Map<String,Integer> link = new HashMap<String,Integer>();
		List<Group> groups = new ArrayList<Group>();
		//first,second代表第一个人，第二个人通话的名字，weight通话时长，idx1,idx2每个人对应的编号
		String first,second;  
		int weight,idx1,idx2;
		
		//循环接收N对通话信息
		while(N-- != 0){
			String[] temp2 = reader.readLine().split(" ");
			first = temp2[0];
			second = temp2[1];
			weight = Integer.parseInt(temp2[2]);
			
			//按照first和second是否已经被记录分为4中情况
			//第一种情况：first和second已经都被记录过了
			if(link.get(first)!=null && link.get(second)!=null){
				idx1 = link.get(first);  //从Link中取出first对应的编号
				idx2 = link.get(second);  //从Link中取出second对应的编号
				//从persons集合中取出first对象已经拥有的权重值并进行累加，因为当前通话的对象中包含first,second同样也要对通话时长进行累加
				persons.get(idx1).weight += weight;   
				persons.get(idx2).weight += weight;
			}
			//第2中情况：first有被记录，second没有被记录(没有被记录时，link中不存在该键值对)
			else if(link.get(first)!=null && link.get(second)==null){
				idx1 = link.get(first);  //首先取出first键对应的值
				//persons集合记录当前存储的所有的人，如果有新的人需要被记录，那么其编号应该按序排列，persons.size()代表了下一个数字编号，
				//将该人的名字及其编号idx2放入到Link中进行记录
				idx2 = persons.size();
				link.put(second, idx2);  //在Link中加入second这个人的名字及编号
				//在persons集合中加入这个人的相关信息，second是其名字，weight是当前通话联系中这个人的通话时长，idx2是其编号
				persons.add(new Person(second,weight,idx2));  
				groups.add(new Group());  //每当有一个新人加入时，在group中存储该对象所属的组
				persons.get(idx1).weight += weight;   //对第一个人的权重和进行累加
			}
			//第3中情况：第一个人未被记录，第二个人有记录了，同上
			else if(link.get(first)==null && link.get(second)!=null){
				idx1 = persons.size();
				idx2 = link.get(second);
				link.put(first, idx1);
				persons.add(new Person(first, weight, idx1));
				groups.add(new Group());
				persons.get(idx2).weight += weight;
			}
			//第4中情况：2个人均未被记录
			else{
				//先取出persons记录的下一个人的编号，对其进行记录，第2个人在第1个人的基础上编号+1
				idx1 = persons.size(); 
				link.put(first, idx1);
				idx2 = idx1 + 1;
				link.put(second, idx2);
				//将这2个人分别添加到persons集合中，在group对这2个人各自的所属组进行创建
				persons.add(new Person(first, weight, idx1));
				persons.add(new Person(second, weight, idx2));
				groups.add(new Group());
				groups.add(new Group());
			}
			unionSet(idx1,idx2);
			groups.get(idx1).sumWeight += weight;
		}
		
		int tf;
		int num = persons.size();
		for(int i=0;i<num;i++){
			tf = getFather(i);
			groups.get(tf).numOfPerson++;
			if(tf != i){
				groups.get(tf).sumWeight += groups.get(i).sumWeight;
			}
			if(groups.get(tf).maxWeight < persons.get(i).weight){
				groups.get(tf).maxWeight = persons.get(i).weight;
				groups.get(tf).head = persons.get(i).name;
			}
		}
		
		Set<Group> result = new HashSet<Group>();
		for(int i=0;i<num;i++){
			if(groups.get(i).sumWeight > K && groups.get(i).numOfPerson > 2){
				result.add(groups.get(i));
			}
		}
        
		System.out.println(result.size());
		Iterator<Group> it = result.iterator();
		while(it.hasNext()){
			Group temp = it.next();
			System.out.println(temp.head + " " + temp.numOfPerson);
		}
/*		for(int i=0;i<result.size();i++){
			System.out.println(result.get(i).head + " " + result.get(i).numOfPerson);
		}*/
		
	}
	
	static void unionSet(int x,int y){
		x = getFather(x);
		y = getFather(y);
		if(x != y){
			persons.get(x).father = y;
		}
	}
	
	static int getFather(int x){
		if(x != persons.get(x).father){
			persons.get(x).father = getFather(persons.get(x).father);
		}
		return persons.get(x).father;
	}
	
	/**
	 * 每个通话的对象的信息，包括：名字(name),weight(该人通话的总时长)，father(该组团花里的根对象,如果根相同，说明这些人是在同一个团伙里)
	 */
	static class Person{
		String name;
		int weight;
		int father;
		Person(String name,int weight,int father){
			this.name = name;
			this.weight = weight;
			this.father = father;
		}
	}
	
	/**
	 * 组对象：存储的信息有，该组的头目(head),该组所有的人数(numOfPerson),
	 *     最大通话时长(maxWeight),所有的通话时长(sumWeight)
	 *     每次在创建Group类时，要对所有的这些成员进行初始化为0
	 */
	static class Group{
		String head;
		int numOfPerson;
		int maxWeight;
		int sumWeight;
		Group(){
			numOfPerson = 0;
			maxWeight = 0;
			sumWeight = 0;
		}
	}
	
}
