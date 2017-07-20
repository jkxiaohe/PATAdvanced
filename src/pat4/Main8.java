package pat4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author gljg
 *题意分析：根据用户输入的数字，构造出一颗avl树
 *AVL树的介绍：1.首先是一颗二叉搜索树，每个节点的左右子树高度之差的绝对值（即平衡因子）最大为1；
 *         当不为1时，可以通过LL,RR,LR,RL的方法旋转成为一颗AVL树
 */
public class Main8 {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer;
		int n = Integer.parseInt(reader.readLine());
		AvlNode root = null;
		tokenizer = new StringTokenizer(reader.readLine());
		for(int i=0;i<n;i++){
			int val = Integer.parseInt(tokenizer.nextToken());
			root = insert(val,root);
		}
		System.out.println(root.val);
		
	}
	
	/**
	 * LL:左旋，将根节点的左孩子作为新的根节点，
	 * 在调整AVL树的过程中，左孩子的右节点重新指向了根节点，根节点对应的左孩子应该重新指向左孩子的右节点值，最后返回新的根节点
	 */
	private static AvlNode rotateLeft(AvlNode node){
		AvlNode tmp = node.left;
		node.left = tmp.right;
		tmp.right = node;
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		tmp.height = Math.max(height(tmp.left), height(tmp.right)) + 1;
		return tmp;
	}
	
	/**
	 * RR旋转：将根节点的右孩子作为新的根节点，
	 * 在调整AVL树的过程中，右孩子的左节点重新指向了根节点，根节点的右孩子应该重新指向那个右孩子的左节点，即相互转换的操作，最后返回根节点
	 */
	private static AvlNode rotateRight(AvlNode node){
		AvlNode tmp = node.right;
		node.right = tmp.left;
		tmp.left = node;
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		tmp.height = Math.max(height(tmp.left), height(tmp.right)) + 1;
		return tmp;
	}
	
	private static AvlNode doubleLeft(AvlNode node){
		node.left = rotateRight(node.left);
		return rotateLeft(node);
	}
	
	private static AvlNode doubleRight(AvlNode node){
		node.right = rotateLeft(node.right);
		return rotateRight(node);
	}
	
	/**
	 * 计算每个节点的高度值：由于刚开始是一个节点一个节点插入的，故之后的节点高度值可以在它的左右孩子最大的高度值上+1
	 * 对于那些刚开始插入的节点，计算其高度值得方法：
	 * 刚开始只有一个，它的标号应该为0，对于某个节点当它的孩子不存在时，返回-1,并且每次计算高度时会+1,所以总体上是从0开始计数的，
	 * 至于返回-1的原因，假设某个节点的右孩子超过高度差2时，此时那个孩子节点高度为1,另一边如果没有节点，那么应该为-1,这样左右节点的高度差才能>=2,会触发AVL树的调整
	 * 如果遇到的是叶子节点，他们的高度默认值便是0，上一层节点的height会在他们的基础上+1
	 */
	private static int height(AvlNode node){
		return node == null ? -1 : node.height;
	}
	
	/**
	 * 判断传入的节点对象其左右孩子节点高度相差是否<=1,通过height函数计算左右孩子节点的高度值
	 */
	private static boolean isBalanced(AvlNode node){
		return Math.abs(height(node.left) - height(node.right)) < 2;
	}
	
	/**
	 * 该方法是实现AVL树最关键的部分：由它来负责插入每一个节点，每次插入后对平衡因子进行判断，如果不平衡要重新进行调整，之后要重新更新每个节点的高度值
	 * 该方法必须要用到的递归，因为不知道每个节点的下面还有多少个孩子节点，每插入一个新的节点，需要对根节点进行一次遍历,又因为每次插入的元素需要从所有的节点中找出最合适的插入位置，
	 *   即遵循左小右大的原则，也需要对树的每个节点进行遍历，即递归查找
	 */
	private static AvlNode insert(int val,AvlNode root){
		//root==null 有2中原因：1.是第一个节点，由主函数传过来的Node即为null,它应该立马被插入到树中；
		//2.寻找到了正确的插入点，即新加入的元素肯定是插入到当前树的所有节点的某一个孩子节点下，可以肯定的是该孩子节点肯定是null的，
		//所以在遍历到最后发现该位置为空后，可以将该值立马插入到这个位置上
		if(root == null){
			return new AvlNode(val);
		}
		//当准备插入一个新的数字时，遵循的原则是：<=root,插入左边;>root,插入右边；实际上，题目输入的数据不会是2个相同的数字
		if(val > root.val){
			//该中情况下为n>root,应该插入到右边去，这里需要对右边进行遍历，并且每次遍历的结构都是一样的，最后一定能找到一个正确的位置插入
			root.right = insert(val,root.right);
			//在插入新的节点后，重新判断该树是否是一颗AVL树
			if(!isBalanced(root)){
				/**
				 * 如果插入新的节点后不符合AVL树的要求，要调用指定的方法进行重新调整
				 * val>root.right.val时，说明新节点插入了根节点的右子树上，那么右子树肯定会比左子树要高，此时应该RR右旋，即将当前跟的右节点调整到根的位置上
				 * vao<root.right.val时，是另一种特殊的情况：
				 * 此时该节点调用RR右旋之后仍然会造成平衡因子>1，只不过是将高的那一支节点转移到了左边而已，要根本性的解决这个问题，需要用到LR旋转，即将根节点的右孩子进行一个
				 * 左旋，在对当前的根节点进行右旋
				 */
				root = val > root.right.val ? rotateRight(root) : doubleRight(root);
			}
		}else{
			/**
			 * 该情况是val<root时，应该插入到左边，方法与插入右边的方法互相换位一下即可
			 */
			root.left = insert(val,root.left);
			if(!isBalanced(root)){
				root = val < root.left.val ? rotateLeft(root) : doubleLeft(root);
			}
		}
		root.height = Math.max(height(root.left), height(root.right)) + 1;
		return root;
	}
	
	/**
	 *该类对象用于存储每一个节点的信息，包括：
	 *val : 节点值；
	 *left : 左孩子，默认情况下为null；
	 *right : 右孩子,默认情况下为Null；
	 *height : 该节点所在的层次高度，用于确定当前树的每个节点是否符合AVL树高度相差<=1的要求,默认情况下为0，即每一层的根节点是从0开始计数的，最下层的为第0层
	 */
	private static class AvlNode{
		int val;
		AvlNode left;
		AvlNode right;
		int height;
		public AvlNode(int val){
			this.val = val;
		}
	}
	
}
