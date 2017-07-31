package sortAlgorithm;

//归并排序
public class Test2 {

	public static void main(String[] args) {
		int[] b = { 3,2,1,6,5,4,8,7,9,0 };
	    mergeSort(b,0,b.length-1);
	    for(int i:b){
	    	System.out.print(i + " ");
	    }
	}
	
	static void mergeSort(int[] a,int start,int end){
		if(start < end){
			int mid = (start+end)/2;
			mergeSort(a,start,mid);
			mergeSort(a,mid+1,end);
			merge(a,start,mid,end);
		}
	}
	
	public static void merge(int[] a,int start,int mid,int end){
		int[] tmp = new int[a.length];
		int i=start,j=mid+1,k=start;
		while(i!=mid+1 && j!=end+1){
			if(a[i]<a[j]){
				tmp[k++] = a[i++];
			}else{
				tmp[k++] = a[j++];
			}
		}
		while(i!=mid+1){
			tmp[k++] = a[i++];
		}
		while(j!= end+1){
			tmp[k++] = a[j++];
		}
		for(i=start;i<=end;i++){
			a[i] = tmp[i];
		}
	}
	
	
}
