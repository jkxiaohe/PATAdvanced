package pat6;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @author gljg
 *题意分析：输入一段数字，这段数字是某刻二叉搜索树的前序遍历或二叉树的镜像，将这颗树还原，并判断是不是二叉搜索树，如果是二叉搜索树，
 *      输出其后续遍历，否则不是。
 *
 */
public class Main6 {

	public static void main(String[] args) {
		//BufferedInputStream是一个缓冲读入流，在有多个数字需要读取时，可以提高效率
		Scanner in =  new Scanner(new BufferedInputStream(System.in));
		int n = in.nextInt();  //接收用户输入的数字个数
		int[] a = new int[n];  //新建一个数组a,存放前序遍历的数字结果
		for(int i=0;i<n;i++){  
			a[i] = in.nextInt();
		}
		
		StringBuilder sb = new StringBuilder(); //StringBuilder存放后续遍历的结果
		boolean result = recur(a,0,n,sb,a[0],false);  //recur用来对后续遍历的结果进行处理，返回的boolean用来判断该串数字是不是前序遍历
		
		if(result){
			System.out.println("YES");
			System.out.println(sb.toString().trim());
		}else{
			sb = new StringBuilder();
			result = recurMirror(a,0,n,sb,a[0],false);
			if(result){
				System.out.println("YES");
				System.out.println(sb.toString().trim());
			}else{
				System.out.println("NO");
			}
		}
		
		
	}
	
	/**
	 * 该方法处理前序遍历的结果
	 * @param a  :代表用户输入的数组，其中存放了用户输入的所有的数字
	 * @param i  :当前查找到了数组a中的第几个数字，默认从第1个开始，即下标为0，a[0] = 8
	 * @param j  ：j为右节点，即i所在的节点为根时，j查找对应根下的右节点，在循环中使用rightRootIndex代表，每次给j的值也是rightRootIndex,而它就是当前节点的右节点
	 * @param sb :接收后序遍历的结果
	 * @param min : 存储每次遍历的那个节点的父节点，min始中代表的是当前遍历对象的父节点，当向下遍历的时候，由a[i]向下传递下去
	 * @param oneRight ：只有一个地方是true,result = recur(a,rightRootIndex,j,sb,a[i],true);用来
	 *                  将右节点的值加入到StringBuilder中
	 * @return :根据不同的情况返回的值来判断
	 */
	static boolean recur(int[] a,int i,int j ,StringBuilder sb,int min,boolean oneRight){
		//i>=j,说明此时已经遍历到了最后的数字，返回，已经没有数字了
		//需要说明的是，i在第一次进入recur时值为0，指向第一个数字，在方法中每次递归的时候都会+1,指向数组中的下一个数字
		//而j根据i的变化调整当前节点的根值，因为序列是前序遍历的，左右根节点要么相邻，要么只有一个
		if(i >= j)
			return true;
		//oneRight=true说明了是右节点的位置，a[i]此时指向的是右节点，min代表的是当前节点的根
		//如果a[i]<min,即右节点的值<根节点的值，说明不是一个二叉搜索树，返回false
		if(oneRight && a[i]<min)
			return false;
		//该if判断是为了将左子树的节点值放入到sb中，
		//前序遍历的特点：根左右，即左节点的下标值只比根节点多1，如果i == j-1,说明此时i下标所指的值为左节点，将其放入到sb中，并返回true，说明成功读入
		if(i == j-1){
			sb.append(a[i] + " ");
			return true;
		}
		//如果上述if都没有返回，走到了这里，说明：当前节点不是左子树，并且符合二叉搜索树的条件，那么将当前节点值作为根节点
		int root = a[i];  
		int rightRootIndex = i;  //记录当前根节点的下标
		//k从i后面的数字开始查找，找一个>=根节点的值,即查询当前根节点是否有右节点存在
		for(int k=i+1;k<j;k++){
			if(a[k] >= root){  //如果当前根节点有右子树，那么rightRootIndex记录右子树的下标，退出循环
				rightRootIndex = k;
				break;
			}
		}
		//该if成立，说明当前根节点是没有右子树的
		//将j的值给力rightRootIndex，j指向的是当前该孩子节点的父节点
		if(rightRootIndex == i)  
			rightRootIndex = j;    
		//通过该recur递归去查找左子树，i+1指向下一个数字，min是当前的根,onerRight为false
		boolean result = recur(a,i+1,rightRootIndex,sb,min,oneRight);
		if(!result)
			return false;
		result = recur(a,rightRootIndex,j,sb,a[i],true);
		if(!result)
			return false;
		sb.append(a[i] + " ");
		
		return true;
	}
	
	static boolean recurMirror(int[] a,int i,int j,StringBuilder sb,int max,boolean oneRight){
		if(i >= j)
			return true;
		if(oneRight && a[i]>=max)
			return false;
		if(i == j-1){
			sb.append(a[i] + " ");
			return true;
		}
		int root = a[i];
		int rightRootIndex = i;
		for(int k=i+1;k<j;k++){
			if(a[k] < root){
				rightRootIndex = k;
				break;
			}
		}
		if(rightRootIndex == i)
			rightRootIndex = j;
		
		boolean result = recurMirror(a,i+1,rightRootIndex,sb,max,oneRight);
		if(!result)
			return false;
	    result = recurMirror(a,rightRootIndex,j,sb,a[i],true);
	    if(!result)
	    	return false;
	    sb.append(a[i] + " ");
		
		return true;
	}
	
}
