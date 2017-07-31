package sortAlgorithm;

//插入排序
public class Test1 {

	public static void main(String[] args) {
		int a[] = {3,1,5,7,2,4,9,6,10,8}; 
		insertSort(a);
		for(Integer i : a){
			System.out.print(i + " ");
		}
	}
	
	//升序
	static void insertSort(int[] a){
		for(int i=1;i<a.length;i++){
			if(a[i] < a[i-1]){
				int tmp = a[i];
				//寻找tmp应该插入的位置
				int j;
				for(j=i-1;j>=0 && tmp < a[j];j--){
					a[j+1] = a[j];
				}
				a[j+1] = tmp; 
			}
			
		}
	}
	
}
