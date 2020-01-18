import java.util.ArrayList;
import java.util.Currency;



/**
 * @author sy
 *  �����㷨�ܽ�workcopy
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
      public void BubbleSort()//ð������
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
      
      public void PrintArray()//��ӡ����
      {
    	  for(int i=1;i<lenth;i++)
    	  {
    		  System.out.print(aint[i]+",");
    	  }
    	  System.out.print("\n");
      }
      /**
       * ѡ������
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
    		}//ѡ��i��ʼ����С�ĺ�i����
    		if(i!=min)//������ھ�˵��i������Сֵ���ý���
    		swap(i, min);
    		
    	}
    }
    /**
     * ��������
     */
    public void InertSort()
    {
    	for(int i=2;i<lenth;i++)
    	{
    		
    		int min=i;
    		if(aint[i]<aint[i-1])//�Ż�ֻ��Ҫ�����С�����ź�������һ�����Ҫ����
    		{
    			//int temp=aint[i];//����Ҫ�����ֵ
    			aint[0]=aint[i];//��a[0]�����ʡ�ռ�
	    		for(int j=i-1;j>0;j--)
	    		{
	    			
	    			if(aint[0]<aint[j])
	    			{
	    				aint[j+1]=aint[j];//�������
	    				min=j;
	    			}
	    		}
	    		aint[min]=aint[0];
    		}
    	}
    }
    /**
     * ϣ������
     */
    public void ShellSort()
    {
    	int increment=lenth;
    	while(increment>1)//���Ϊ1ʱ���һ���������������������
    	{
    		increment=increment/3+1;//�������
	    	for(int i=increment+1;i<lenth;i++)
	    	{
	    		if(aint[i]<aint[i-increment])
	    		{
	    			aint[0]=aint[i];
	    			int min=i;
	    			for(int j=i-increment;j>0;j-=increment)//�Լ����������в�������
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
    	}//����
    	for(int i=1;i<lenth;i++)
    	{
    		swap(1, lenth-i);
    		Max_Heap(1, lenth-i);
    	}//����
    	
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

    public void MergeSort()//�鲢����ݹ�
    {
    	
    	Msort(1,lenth-1);
    }
    public void MergeSort2()//�鲢����ǵݹ�
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
     * ��һ������a,��ʼ��ַbegin���в��������ٹ鲢
     * @param a
     * @param begin
     */
    public void Msort(int begin,int end)
    {
    	
    	if(end-begin>=1)//���ȴ��ڵ���2�����ָ�
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
    	int temp[]=new int[lenth];//��ʱ���飬����ݹ���һֱ���ٿռ�
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
     * ��������ݹ�д��
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
     * ���������Ż��ݹ�
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
    
    public int Partition(int begin,int end)//���ҽ��
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
    		}//������ҵ����ڻ�ֵ��ֵ
    		while(i<j&&aint[j]>=position)
    		{    			
    			j--;
    		}//���ұ��ҵ�С�ڻ�ֵ��ֵ
    		
    		swap(i, j); //��������
    	}
    		swap(end, i);
    	
    	return i-1;
    }
    public int Partition1(int begin,int end)//�ڿ�
    {
    	int position=aint[(begin+end)/2];
    	int i=begin,j=end;
    	swap((begin+end)/2, end);//�ѻ�׼ֵ�������������
    	for(;i<j;)
    	{
    		
    		while(i<j&&aint[i]<=position)
    		{   			
    			i++;
    		}
    		swap(i, j);  //������ҵ����ڻ�׼�Ϳӽ�����
    		while(i<j&&aint[j]>=position)
    		{    			
    			j--;
    		}
    		swap(j, i);//���ұ߿�ʼ�ҵ�С�ڻ�׼��ֵ�Ϳӽ���
    		//temp=j;
    		
    	}
    	    PrintArray();
    		//aint[i]=position;//����һֱ����position����Ҫ
    		
    	return i-1;
    }
    public int Partition3(int begin,int end)//�ڿ��Ż��㷨
    {
    	int position=aint[(begin+end)/2];
    	int i=begin,j=end;
    	swap((begin+end)/2, end);//�ѻ�׼ֵ���������������ν�Ŀ�
    	for(;i<j;)
    	{
    		
    		while(i<j&&aint[i]<=position)
    		{   			
    			i++;
    		}
    		aint[j]=aint[i];  //��j�������i����ʱiΪ�ӣ��Ż�����
    		while(i<j&&aint[j]>=position)
    		{    			
    			j--;
    		}
    		aint[i]=aint[j];//��i�������j
    		//temp=j;
    		
    	}
    	    //PrintArray();
    		aint[j]=position;//��󽫻�ֵ�����
    		
    	return i-1;
    }
    
    /**
     * ����ǰ������ָ���м��ֵ�Ǵ��ڻ�ֵ�ģ�����ָ����ߵ�ֵʱ��С�ڻ�ֵ��
     * @param begin
     * @param end
     * @return
     */
    public int Partition2(int begin,int end)//ǰ��ָ��
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
