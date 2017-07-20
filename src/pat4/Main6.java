package pat4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

/**
 * 
 * @author gljg
 *题意解析：用户输入一串数字，将这串数字按照完全二叉搜索树的方式进行排序，然后层序遍历输出排序后的结果
 *思路：完全二叉搜索树的特点：左子树<根节点<=右子树
 *   假设根节点编号为0，那么他的左子树为2n+1 , 右子树为2n+2
 *   另一种情况，当根节点编号是从1开始的时候，左子树为2n,右子树为2n+1
 *   对于用户输入的一串数字，可以很方便的进行升序排列，这时候需要考虑的是如何将这些数字按照从小到大的顺序依次放入到二叉树中对应的位置上
 *   对于二叉树位置的编号，这里使用了数组来存储，并且数组的下标对应的是二叉树层序遍历的下标，通过下标来找到对应的节点位置，然后再存放用户的数字值
 *使用数组存放二叉树的好处是：二叉树的根节点默认从0开始，对应数组的首个数字是下标0，这样就可以用数组对应的下标来找到二叉树相应的位置
 */
public class Main6 {

	public static void main(String[] args) throws IOException {
		//使用缓冲字符读入流，可以快速的读取每一行的数据，最后只要将这些输入的数据依次存放到指定的位置即可
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		int[] nums = new int[n];
		String[] strs = reader.readLine().split(" ");
		for(int i=0;i<n;i++){
			nums[i] = Integer.parseInt(strs[i]);
		}
		Arrays.sort(nums);  //这里保证了nums数组中的数字是按序存放的
		
		//利用栈的特点先进后出，后进先出的特点来存储树的每一个节点
		Stack<Node> stack = new Stack<Node>();  
		int aindex = 0;   //标记当前已经存储到了第几个数了，即从nums原数组中将最小的数字挨个取出来
		int[] result = new int[n];  //对应二叉搜索树的每一个节点的下标，它用来存储每一个下标上对应的数值
		stack.push(new Node(0,false)); //根节点的下标为0，并且初始时没有被赋值，所以将它放入栈中，并标记为false
		while(!stack.isEmpty()){  //只要栈中有节点存在，就不断的将它取出来
			/**
			 * 这里是最难理解的地方：首先弹出栈中的当前节点，默认为根节点，并且没有被赋值，由于要按照从小到大的方式给每一个节点赋值，故此时要去寻找
			 * 树中存储最小节点的下标，false,true的意义就在这里
			 * 只要visited没有被赋值，那么便不断的取出他的孩子节点，首先取出右节点（2n+2），并将它标记为false,该节点等会会放入到栈底，
			 * 接着将该根节点入栈，并标记为true,因为它的孩子节点已经取出来了，轮到它了（左根右的顺序赋值的）
			 * 最后取出该根的左节点(2n+1),该节点此时放入栈顶，从栈中取值时会首先将它弹出，一定意义上说明了它是最左边的值，当然也就代表了最小的值
			 * 接着就是循环取出这颗树的值了，此时不管是左节点还是右节点，都将它弹出，再将它作为根节点，循环取出它的孩子节点
			 * 这样子最后会遍历到最左端的节点位置，此时该节点是叶子节点，它的没有孩子节点，自然也不会入栈，此时入栈的就只有它自己了，并标记为true,说明可以进行赋值了
			 * 此时该节点的val值是它在二叉树中的真正下标的位置（因为Node的Val值是自己手工new上去的），挨个将将最小的值赋值就行了
			 * 没给一个节点赋完值，该节点就从栈中弹出了，这样子节点越来越少，最后就只剩一个根节点了，根节点一旦也赋完值，stack也就空了
			 */
			Node node = stack.pop();
			int index = node.val;
			if(node.visited){
				result[index] = nums[aindex++];
			}else{
				if(2*index+2 < n){
					stack.push(new Node(2*index+2,false));
				}
				stack.push(new Node(index,true));
				if(2*index+1 < n)
					stack.push(new Node(2*index+1,false));
			}
		}
		
		for(int i=0;i<n-1;i++)
			System.out.print(result[i] + " ");
		System.out.println(result[n-1]);
	}
	
	/**
	 *使用一个Node类对象来描述一个节点的结构，包括：
	 * 1.该节点的值；
	 * 2.该节点有没有被访问过，在本题中的作用是判断该位置对应的节点有没有被赋值，因为本题从头到尾是要构造出一颗二叉搜索树来
	 */
	private static class Node{
		int val;
		boolean visited;
		public Node(int val,boolean vis){
			this.val = val;
			this.visited = vis;
		}
	}
	
}

