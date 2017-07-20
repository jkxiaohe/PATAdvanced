package pat3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();  //节点总个数
		Node[] nodes = new Node[n];  //构造包括n个节点的数组
		//使用循环对这n个节点对象的数组进行初始化
		for(int i=0;i<n;i++){
			nodes[i] = new Node();
		}
		//接收每个节点的左子树与右子树的值，并将其赋值到节点对象中，默认情况下，新加入的这对左右子树值是以当前节点对象为参照的，他们是当前节点的孩子节点
		for(int i=0;i<n;i++){
			int l = in.nextInt();
			int r = in.nextInt();
			//nodes[i]此时为父节点，分别将其左右子树分别赋值
			nodes[i].left = l;  
			nodes[i].right = r;
			//对左右子树分别进行判断，如果他们不为null,那么将他们的父节点赋值
			if(l != -1){
				nodes[l].parent = i;
			}
			if(r != -1){
				nodes[r].parent = i;
			}
		}
		int[] key = new int[n];   //key数组用来保存用户输入的一串数字序列
		for(int i=0;i<n;i++){
			key[i] = in.nextInt();
		}
		
		Arrays.sort(key);  //对这串数字进行升序
		
		Node tmp;  //tmp是一个临时节点，用来存储遍历过程中的变量值
		int index = 0;  //index用来记录当前节点的下标位置
		int k = 0;  //用来记录当前数字的位置
		//此循环的作用是将升序数组中的每一个数字赋给树种相应位置上的变量，结构是：
		//首先判断当前节点有没有被访问过，默认情况下都没有被访问过，然后判断他的左子树是不是null或有被访问过，如果是的话就说明这棵数
		//不再有左孩子了，它便是最左的节点，将升序数组中的值按key的顺序赋值给它，
		//如果当前这个节点仍然有左节点，那么便将它的左节点给了Index，index用来记录每次最小节点的位置，然后从key数组中依次取出最小的值进行赋值
		while(k<n){   //k<n作用是将key数组中的所有数字都遍历
			tmp = nodes[index];  //tmp用来存储当前节点的位置
			if(tmp.visited){   //如果当前节点被访问过了，那么判断它的右节点
				if(tmp.right != -1 && !nodes[tmp.right].visited){  //如果右节点不为空且没有被访问过，说明index应该先去右边进行判断
					index = tmp.right;   //将Index的位置指向当前节点的右子树
				}else{   //说明右节点已经被访问过或为null,不用再进行判断，直间将Index指向当前节点的父节点
					index = tmp.parent;
				}
			}else if(tmp.left == -1 || nodes[tmp.left].visited){  
				//当前节点的左子树为null或已被访问过，不用再进行左边的判断，由于树的特点是左小，右大，所以应将当前key数组的值赋给index的位置
				tmp.key = key[k];  //对该节点的位置进行赋值
				tmp.visited = true;  //并标记该节点以及被访问过
				k++; //k指向下一个数字
				if(tmp.right != -1 && !nodes[tmp.right].visited){ //判断当前节点的右孩子有没有被访问过，如果没有就将Index指向右边的节点
					index = tmp.right;
				}else{  //如果右孩子已经被访问过了，就将index指向上一层的节点
					index = tmp.parent;
				}
			}else{   //如果当前节点让然有左孩子，就继续将index指向左边，左边的节点永远是比较小的值
				index = tmp.left;
			}
		}
		
		StringBuilder builder = new StringBuilder();  //使用StringBuilder来存储层序遍历输出的结果
		Queue<Integer> queue = new LinkedList<>();  //使用队列可以方便的存取树的每个节点值
		queue.add(0);  //树的根节点为0，所以首先将位置0存入到队列中
		while(queue.size()>0){  //只要队列中有值存在，那么便进行循环判断
			int i = queue.poll();  //弹出当前存储的节点的下标位置
			tmp = nodes[i];  //tmp存放该节点对象
			builder.append(tmp.key);  //将节点值放入builder中
			builder.append(" ");
			//判断它的左子树与右子树是否存在，若存在就将它们放入到队列中，并且应该先放入左孩子，再放入右孩子，这样下次从队列中取数据时，是按照从左到右的顺序取值的
			if(tmp.left != -1)
				queue.add(tmp.left);
			if(tmp.right != -1)
				queue.add(tmp.right);
		}
		System.out.println(builder.toString().trim());
	}
	
	//使用一个类单独存储每个节点的数据信息，包括：节点存储的数据值，左子树，右子树，父节点，有没有被访问过（作用是判断有没有判断该位置被赋值）
	private static class Node{
		int key;
		int left;
		int right;
		int parent = -1;
		boolean visited = false;
	}
}
