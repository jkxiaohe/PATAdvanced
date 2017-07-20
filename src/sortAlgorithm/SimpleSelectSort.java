package sortAlgorithm;

public class SimpleSelectSort {

	public static void main(String[] args) {
		int a[] = {3,1,5,7,2,4,9,6,10,8};
		SimpleSelectSort obj = new SimpleSelectSort();
		System.out.println("初始值：");
		obj.print(a);
		obj.selectSort(a);
		System.out.println("\n排序后：");
		obj.print(a);
		
	}
	
	//简单选择排序的思想：从所有的数中选出最小的那个放到第一个位置上，接着再从剩下的数中选出最小的那个放到第2个位置上，按顺序放置
	//直到倒数第2个位置也被放上了数字为止，此时最后一个数已经被确定了，无需再比较了
	private void selectSort(int[] a){
		for(int i=0;i<a.length;i++){  //i从第1个元素开始比较，将后面最小的元素依次放到该位置上
			int k = i;  //k用来记录此时放置的位置
			//j从i后面的元素开始，查找最小的那个元素，并将那个最小元素的下标赋值给k
			for(int j=i+1;j<a.length;j++){ 
				if(a[k] > a[j])
					k = j;
			}
			swap(a,k,i);  //将最小元素的下标k赋值给i位置上的元素
		}
	}
	
	public void swap(int[] data,int i,int j){
		if(i == j)
			return;
		data[i] = data[i] + data[j];
		data[j] = data[i] - data[j];
		data[i] = data[i] - data[j];
	}
	
	public void print(int[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i] + " ");
		}
	}
	
}
