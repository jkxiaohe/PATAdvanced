package sortAlgorithm;

public class ShellSort {

	public static void main(String[] args) {
		int a[] = {3,1,5,7,2,4,9,6,10,8};    
		ShellSort obj = new ShellSort();
		System.out.println("初始值：");
		obj.print(a);
		obj.shellSort(a);
		System.out.println("\n排序后：");
		obj.print(a);
	}
	
	//该方法用来产生增量因子，增量因子的计算方法为数组长度/2,之后每次增量因子都/2，直到增量因子为1
	//每次产生一个增量因子后，都要调用排序算法按照指定的增量因子进行排序
	private void shellSort(int[] a){
		int dk = a.length/2;  //初始的时候为数组长度/2
		//只要增量因子>1,边排序边更改增量因子的大小,dk==1时，说明是最后一次排序
		while(dk>=1){  
			ShellInsertSort(a,dk);
			dk /= 2;
		}
	}
	
	//按照指定的增量因子对数组进行排序，希尔排序是建立在直接插入排序的基础上的
	private void ShellInsertSort(int[] a,int dk){
		//从第2个数开始排序，dk代表的是每次的增量，i++,说明了在每次增量迭代完后会进行对下一个数进行相同大小增量的排序
		for(int i=dk;i<a.length;i+=dk){
			//将后一个数和前一个数进行比较，如果>=前一个数，说明该数字插入到前面有序序列中的位置不变；
			//如果<前一个数，说明要对前面的有序序列的数字进行移动，找到合适的插入位置
			//a[i]==a[dk]是该组有序序列中的第二个位置，a[i-dk]是该组有序序列中的前一个位置，每次i++,有序序列都会发生改变
			//直到最后dk==1时，有序序列会只剩下一个
			if(a[i] < a[i-dk]){  
				int j;  //记录插入点的下标
				int x = a[i];  //x临时存储该新数字
				//从有序序列的最后一个数字开始，循环向后移动，直到找到该数字应该插入的下标
				//判断条件j>=0时为了保证最末到首元素位置，x<a[j]说明还没有找到待插入的位置;j-=dk继续向前寻找比x小的或相等的元素
				for(j=i-dk;j>=0 && x<a[j];j-=dk){
					a[j+dk] = a[j];
				}
				a[j+dk] = x;  //找到之后进行一个插入
			}
		}
	}
	
	public void print(int[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i] + " ");
		}
	}
	
}
