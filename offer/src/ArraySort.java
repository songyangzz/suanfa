import java.util.ArrayList;
import java.util.Currency;



/**
 * @author sy
 *  排序算法总结workcopy
 */
public class ArraySort {


      public int[] aint;
      public int lenth;
      public void swap(int i,int j)
      {
    	  int temp=aint[i];
    	  aint[i]=aint[j];
    	  aint[j]=temp;
      }
      public ArraySort(int l)
      {
    	  aint=new int[l+1];
    	  lenth=l+1;
      }
      public void BubbleSort()//冒泡排序
      {
    	  boolean s=true;
    	  for(int i=1;i<lenth&&s;i++)
    	  {
    		  s=false;
    		  for(int j=1;j<lenth-i;j++)
    		  {
    			  
    			  if(aint[j]>aint[j+1])
    			  {
    				  swap(j, j+1);
    				  s=true;
    			  }
    		  }
    	  }
      }
      
      public void PrintArray()//打印数组
      {
    	  for(int i=1;i<lenth;i++)
    	  {
    		  System.out.print(aint[i]+",");
    	  }
    	  System.out.print("\n");
      }
      /**
       * 选择排序
       */
    public void SelectSort()
    {																	
    	for(int i=1;i<lenth;i++)
    	{
    		int min=i;
    		for(int j=i;j<lenth;j++)
    		{
    			if(aint[j]<aint[min])
    				min=j;
    		}//选出i开始，最小的和i交换
    		if(i!=min)//如果等于就说明i就是最小值不用交换
    		swap(i, min);
    		
    	}
    }
    /**
     * 插入排序
     */
    public void InertSort()
    {
    	for(int i=2;i<lenth;i++)
    	{
    		
    		int min=i;
    		if(aint[i]<aint[i-1])//优化只有要插入的小于已排好序的最后一项才需要插入
    		{
    			//int temp=aint[i];//保存要插入的值
    			aint[0]=aint[i];//用a[0]保存节省空间
	    		for(int j=i-1;j>0;j--)
	    		{
	    			
	    			if(aint[0]<aint[j])
	    			{
	    				aint[j+1]=aint[j];//整体后移
	    				min=j;
	    			}
	    		}
	    		aint[min]=aint[0];
    		}
    	}
    }
    /**
     * 希尔排序
     */
    public void ShellSort()
    {
    	int increment=lenth;
    	while(increment>1)//间隔为1时最后一次排序整个数组就有序了
    	{
    		increment=increment/3+1;//增量间隔
	    	for(int i=increment+1;i<lenth;i++)
	    	{
	    		if(aint[i]<aint[i-increment])
	    		{
	    			aint[0]=aint[i];
	    			int min=i;
	    			for(int j=i-increment;j>0;j-=increment)//对间隔的数组进行插入排序
	    			{
	    				if(aint[j]>aint[0])
	    				{
	    					aint[j+increment]=aint[j];
	    					min=j;
	    				}
	    					
	    			}
	    			aint[min]=aint[0];
	    		}
	    	}
    	}
    }

    public void HeapSort()
    {
    	for(int i=lenth/2;i>=1;i--)
    	{
    		Max_Heap(i,lenth);
    	}//建堆
    	for(int i=1;i<lenth;i++)
    	{
    		swap(1, lenth-i);
    		Max_Heap(1, lenth-i);
    	}//排序
    	
    }
    public void Max_Heap(int i,int size)
    {
    	int max=i;
		int left=2*i;
		int right=2*i+1;
		if(left<size&&aint[left]>aint[i])
			max=left;
		if(right<size&&aint[right]>aint[max])
			max=right;    		
	    if(i!=max)
	    {
	    	swap(i, max);
	    	Max_Heap(max,size);
	    }
    }

    public void MergeSort()//归并排序递归
    {
    	
    	Msort(1,lenth-1);
    }
    public void MergeSort2()//归并排序非递归
    {
    	int k=1;
    	while(k<lenth)
    	{
    		int begin=1;
    		
    		int mid=begin+k-1;
    		int end=mid+k;
    		while(end<lenth)
    		{
    			System.out.println(begin+","+mid+","+end+":"+k);
    			Merge(begin, mid, end);
    			begin+=2*k;
    			mid+=2*k;
    			end+=2*k;
    			
    		}
    		if(begin<lenth-1)
    			if(mid<lenth-1)
    			Merge(begin, mid, lenth-1);
    			
    		PrintArray();
    		k=k*2;
    		
    	}
    }
    
   
    /**
     * 对一个数组a,起始地址begin进行拆分排序后再归并
     * @param a
     * @param begin
     */
    public void Msort(int begin,int end)
    {
    	
    	if(end-begin>=1)//长度大于等于2继续分割
    	{	
    		
    		int mid=(end+begin)/2;
    		/*System.out.println(begin+","+mid);
    		System.out.println((mid+1)+","+end);*/
    		Msort(begin,mid);
    		Msort(mid+1,end);
    	
    		Merge(begin,mid,end);
    	}
    }
    
    public void Merge( int begin,int mid, int end)
    {
    	int temp[]=new int[lenth];//临时数组，避免递归中一直开辟空间
    	int i=begin;
    	int j=mid+1;
    	int k=1;
    	for(;i<=mid&&j<=end;k++)
    	{
    		if(aint[i]<aint[j])
    		{
    			
    			temp[k]=aint[i];
    			i++;
    		}
    		else 
    		{
    			temp[k]=aint[j];
    			j++;
    		}
    	}
    	while(i<=mid	)
		{
			temp[k++]=aint[i++];
		}
    	while(j<=end)
		{
			temp[k++]=aint[j++];
		}
    	int t=1;
		while(begin<=end)
		{
			aint[begin++]=temp[t++];
		}
		//PrintArray();
    }
    
    public void QuickSort()
    {
    	QSort1(1,lenth-1);
    }
    
    /**
     * 快速排序递归写法
     * @param begin
     * @param end
     */
    public void QSort(int begin,int end)
    {
    	int positon;
    	if(begin<end)
    	{
    		positon=Partition2(begin, end);
    		QSort(begin, positon);
    		QSort(positon+2, end);
    	}
    	
    }
    /**
     * 快速排序优化递归
     * @param begin
     * @param end
     */
    public void QSort1(int begin,int end)
    {
    	int positon;
    	while(begin<end)
    	{
    		positon=Partition2(begin, end);
    		QSort1(begin, positon);
    		begin=positon+2;
    	}
    	
    }
    
    public int Partition(int begin,int end)//左右结点
    {
    	int position=aint[(begin+end)/2];
    	int i=begin,j=end;
    	swap((begin+end)/2, end);
    	//PrintArray();
    	for(;i<j;)
    	{
    		while(i<j&&aint[i]<=position)
    		{   			
    			i++;
    		}//从左边找到大于基值的值
    		while(i<j&&aint[j]>=position)
    		{    			
    			j--;
    		}//从右边找到小于基值的值
    		
    		swap(i, j); //交换两个
    	}
    		swap(end, i);
    	
    	return i-1;
    }
    public int Partition1(int begin,int end)//挖坑
    {
    	int position=aint[(begin+end)/2];
    	int i=begin,j=end;
    	swap((begin+end)/2, end);//把基准值交换到数组最后
    	for(;i<j;)
    	{
    		
    		while(i<j&&aint[i]<=position)
    		{   			
    			i++;
    		}
    		swap(i, j);  //从左边找到大于基准和坑交换。
    		while(i<j&&aint[j]>=position)
    		{    			
    			j--;
    		}
    		swap(j, i);//从右边开始找到小于基准的值和坑交换
    		//temp=j;
    		
    	}
    	    PrintArray();
    		//aint[i]=position;//坑里一直都是position不需要
    		
    	return i-1;
    }
    public int Partition3(int begin,int end)//挖坑优化算法
    {
    	int position=aint[(begin+end)/2];
    	int i=begin,j=end;
    	swap((begin+end)/2, end);//把基准值交换到数组最后即所谓的坑
    	for(;i<j;)
    	{
    		
    		while(i<j&&aint[i]<=position)
    		{   			
    			i++;
    		}
    		aint[j]=aint[i];  //在j坑里放入i，此时i为坑，优化交换
    		while(i<j&&aint[j]>=position)
    		{    			
    			j--;
    		}
    		aint[i]=aint[j];//在i坑里放入j
    		//temp=j;
    		
    	}
    	    //PrintArray();
    		aint[j]=position;//最后将基值填入坑
    		
    	return i-1;
    }
    
    /**
     * 保持前后两个指针中间的值是大于基值的，两个指针左边的值时钟小于基值，
     * @param begin
     * @param end
     * @return
     */
    public int Partition2(int begin,int end)//前后指针
    {
    	int position=aint[(begin+end)/2];
    	int i=begin,j=begin-1;
    	swap((begin+end)/2, end);
        while(i<end)
    	{
            if(aint[i]<position)
            {    
            	j++;
            	if(i-j>0)
        		swap(i, j);
            	//PrintArray();
            }
            i++;
    	}
    		swap(j+1, end);
    		//PrintArray();
    	return j;
    }
  	public static void main(String arg[])
  	{
  	    ArraySort aSort=new ArraySort(15);
  	    for(int i=1;i<aSort.lenth;i++)
  	    {
  	    	aSort.aint[i]= (int)(Math.random()*10);
  	    }
  	    aSort.PrintArray();
  	    aSort.QuickSort();
  	    aSort.PrintArray();
  	}
      
}
