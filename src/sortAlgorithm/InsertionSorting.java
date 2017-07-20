package sortAlgorithm;

public class InsertionSorting {

	public static void main(String[] args) {
//		 int a[] = {3,1,5,7,2,4,9,6,10,8}; 
		int a[] = {3,1,5,7,2,4,9,6,10,8,11,22,33,14}; 
		 InsertionSorting obj = new InsertionSorting();
		 System.out.println("初始值：");
		 obj.print(a);
		 obj.insertSort(a);
		 System.out.println("\n排序后：");
		 obj.print(a);

	}
	
	public void print(int[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i] + " ");
		}
	}
	
	//插入排序,假设刚开始的第一个数字是有序的，将后面的数字按顺序插入到前面的有序队列中
	public void insertSort(int[] a){
		for(int i=1;i<a.length;i++){  //将第一个数字作为有序序列，把后面的数字一个一个插入到已排好序的列表中
			//每次比较的都是后一个和前一个的大小，前面的数字代表的是有序序列，后面的那一个数字是准备要插入到有序序列中，
			//首先判断该数字应该插入的位置，如果a[i]>=a[i-1]，那么说明它在有序序列中时最大的一个数字或者和序列中的最后一个数字大小时相等的，
			//此时它的位置不变，i++,到下一个数字继续排序
			if(a[i] < a[i-1]){  //如果if成立，说明该数字需要插入到前面的有序数字中
				int j ;  //j用来记录新数字插入点的下标
				int x = a[i];  //使用x存放该数字的值，找到插入的下标位置后，可以从x取出该数字的值
//				a[i] = a[i-1];  //将前一个位置的值向后移动一位
				//使用该for循环将前面的有序序列的数字循环向后移动，直到找到比x小的元素后，停止查找
				//j=i-1,从有序序列的最后一个位置开始移动，直到移动到首位置或找到比x小或相等的元素，停止查找,在此过程中，j--说明不断向前递归
				for(j=i-1;j>=0 && x<a[j];j--){  
					a[j+1] = a[j];
				}
				//找到插入位置后需要j+1,应为上面的j--将j指向了空白处的前一个元素
				a[j+1] = x;
			}
		}
	}
	
}
