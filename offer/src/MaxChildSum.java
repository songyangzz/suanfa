class MaxChildSum {
    public int maxSubArray(int[] nums) {
		
    	int n=nums.length;
    	int low=0;
    	int high=n-1;
       int result[]=getmaxsum( low, high, nums);
    	
    	return result[2];
        
        
    }
    public int[] getmaxsum(int low,int high,int nums[])//����һ����СΪ3�����飬0λ��Ϊ��λ�±꣬1λ��Ϊ��λ�±꣬2λ��Ϊ��
    {
    	int result[] =new int[3];
    	if(low==high)//�߽�
    	{
    		result[0]=low;
    		result[1]=low;
    		result[2]=nums[low];
            return result;
    	}
    	
    	int left[]=getmaxsum(low, (low+high)/2, nums);//�󴮵ݹ�    	
    	int right[]=getmaxsum((low+high)/2+1, high, nums);//�Ҵ��ݹ�
    	
    	int mid[]=FindMidMaxSum(low, high, (low+high)/2, nums);
    	
    	
    	if(left[2]>right[2])
    	{
    		if(left[2]>mid[2])
    			return left;
    		else
    			return mid;
    	}
    	else
    	{
    		if(right[2]>mid[2])
    			return right;
    		else
    			return mid;
    	}

    }
    public int[] FindMidMaxSum(int low,int high,int mid, int nums[])
    {
    	int sum=0;
    	int result[]=new int[3];
    	result[2]=Integer.MIN_VALUE;
    	for(int i=mid;i>=low;i--)//����Ѱ�����
    	{
    		sum+=nums[i];
    		if(sum>result[2])
    		{
    			result[2]=sum;
    			result[0]=i;
    		}
    	}
    	sum=0;
    	int result1[]=new int[3];
    	result1[2]=Integer.MIN_VALUE;
    	for(int i=mid;i<=high;i++)//����Ѱ�����
    	{
    		sum+=nums[i];
    		if(sum>result1[2])
    		{
    			result1[2]=sum;
    			result1[1]=i;
    		}
    	}
    	System.out.println(mid+"-"+result[2]+","+result1[2]);
    	result[1]=result1[1];
    	result[2]+=result1[2];
    	result[2]-=nums[mid];
    	return result;
    }
	public static void main(String arg[])
	{
		MaxChildSum solution=new MaxChildSum();
	  int  nums[]=new int[] {-2,1,-3,4,-1,2,1,-5,4}	;
	  System.out.println( solution.maxSubArray(nums));
	}
	}