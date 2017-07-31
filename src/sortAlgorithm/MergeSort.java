package sortAlgorithm;

//归并排序
public class MergeSort {

	public static void main(String[] args) {
//		int[] a = { 3,2,1,6,5,4,8,7,9,0 };
		int[] b = {3,1,2,8,7,5,9,4,0,6,};
		mergeSort(b,0,b.length-1);
//		mergeSort(a,0,a.length-1);
		for(int i : b){
			System.out.print(i + " ");
		}
	}
	
	//将序列不断地分为左右2部分，直到每部分只有1个数字为止
	static void mergeSort(int[] a,int start,int end){
		if(start < end){  //start<end，说明当前子序列中不止一个数字，应该继续划分为更小的子序列
			int mid = (start+end)/2;  //划分左右子序列时的中间线
			mergeSort(a,start,mid);  //对左边的子序列进行递归，判断它能不能再划分
			mergeSort(a,mid+1,end);  //对右边的子序列进行递归，判断它能不能再划分
			//左右2边的子序列都排好序后，调用merge函数进行归并，由于上面2步会出现多次递归，所以最后返回
			//到这里的时候，已经有多个子序列都归并好了
			merge(a,start,mid,end);  
		}
	}
	
	//归并函数，对左右2个子序列进行归并,start是开始位置，mid是左右2个子序列的边界，end是序列的末尾
	static void merge(int[] a,int start,int mid,int end){
		int[] tmp = new int[a.length];  //使用tmp数组存放归并后的值
		int i = start;  //记录左边序列的头
		int j = mid+1;  //记录右边节点的开始
		int k = start;  //k为tmp数组中的位置，tmp数组用于存放2个序列合并后的有序序列，其对应的开始和结束位置必须和这2个子序列一致
		//从这2个子序列中不断取出最小的那个值放入到tmp数组中
		while(i<=mid && j<=end){
			if(a[i] < a[j]){
				tmp[k++] = a[i++];
			}else{
				tmp[k++] = a[j++];
			}
		}
		//当将其中一个子序列遍历完成时，会走到这里，此时需要对另一个没有遍历完的子序列中的数字全部添加到tmp数组中
		while(i <= mid){
			tmp[k++] = a[i++];
		}
		while(j <= end){
			tmp[k++] = a[j++];
		}
		//将start-end范围内排好序的数字放回到原数组中去
		for(i=start;i<=end;i++){
			a[i] = tmp[i];
		}
		for(int q : a){
			System.out.print(q + " ");
		}
		System.out.println();
		
	}
	
}
