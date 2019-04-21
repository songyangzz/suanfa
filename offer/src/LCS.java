

class LCS{
	private StringBuilder s1;
	
	private int[][] arclenth;
	private int[][]  charchild;
    public StringBuilder getS1() {
		return s1;
	}

	public void setS1(StringBuilder s1) {
		this.s1 = s1;
	}

	public StringBuilder getS2() {
		return s2;
	}

	public void setS2(StringBuilder s2) {
		this.s2 = s2;
	}

	private	StringBuilder s2;
	
	private StringBuilder LCSstring;

	public StringBuilder getLCSstring() {
		return LCSstring;
	}

	public void setLCSstring(StringBuilder lCSstring) {
		LCSstring = lCSstring;
	}
	public void GetLCS() 
	{
	    for(int i=1;i<s1.length()+1;i++)
	    	for(int j=1;j<s2.length()+1;j++)
	    	{
	    		{
	    			if(s1.charAt(i-1)==s2.charAt(j-1))
	    			{
	    					arclenth[i][j]=arclenth[i-1][j-1]+1;
	    			}
	    			else
	    			{
	    			
	    					arclenth[i][j]=arclenth[i-1][j]>arclenth[i][j-1]
	    							?arclenth[i-1][j]:arclenth[i][j-1];
	    			}
	    		}
	    	}
	}
	public void printmaxchar()
	{
		  int l1=s1.length();
		    int l2=s2.length();
		    while(l1>=1&&l2>=1)
		    {
				if(s1.charAt(l1-1)==s2.charAt(l2-1))
				{
					
					System.out.println(s1.charAt(l1-1));
					l1--;
					l2--;
				}
				else
				{
					if(arclenth[l1][l2]==arclenth[l1-1][l2])
					   l1--;
					else
					   l2--;
				}
		    }
	}
	public static void main(String arg[])
	{
		LCS lcs=new LCS();
		lcs.s1=new StringBuilder("ABCBDBDAB");
		lcs.s2=new StringBuilder("BDCABA");
		lcs.arclenth=new int[lcs.s1.length()+1][lcs.s2.length()+1];
		lcs.GetLCS();
		System.out.println(lcs.arclenth[lcs.s1.length()][lcs.s2.length()]);
		lcs.printmaxchar();
	}
}