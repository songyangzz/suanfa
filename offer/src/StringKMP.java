class StringKMP
{
	public static int[] getNext(StringBuilder T)
	{
		int i=1,j=0;
		int next[]=new int [T.length()];
		next[0]=0;
		while(i<T.length())
		{
			if(j==0||T.charAt(i-1)==T.charAt(j-1))
			{
				i++;
				j++;
				if(T.charAt(i-1)==T.charAt(j-1))//nextvalÓÅ»¯
					next[i-1]=next[j-1];
				else next[i-1]=j;
				
			}
			else j=next[j-1];
		}
		return next;
	}
	public static int kmp(StringBuilder t,StringBuilder t1)
	{
		int i=0,j=0;
		int []next=getNext(t1);
		while(i<t.length()&&j<t1.length())
		{
			if(j==0||t.charAt(i)==t1.charAt(j))
			{
				i++;
				j++;
			}
			else
				j=next[j-1];
		}
		if(j==t1.length())
			return i;
		else return 0;
	}
	public static void main(String arg[])
	{
		StringBuilder tBuilder=new StringBuilder("ababa");
		StringBuilder tBuilder2=new StringBuilder("afsdfkasdgjsababasfgetwoth");
		System.out.println(kmp(tBuilder2,tBuilder));
	}
}