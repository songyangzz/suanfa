/**
 * 动态规划算法之钢条切割问题
 * @author sy
 *
 */

public class CutSteal
{
	public int Lenth=0;
	
	public int[] Price;
	
	public void CutWay()
	{
		int[] r=new int[Lenth+1];
		int[] s=new int[Lenth+1];
		for(int i=0;i<Lenth+1;i++)
		{
			r[i]=0;
			s[i]=0;
		}
		for(int i=0;i<Lenth;i++)
		{
			int q=-99999;
			for(int j=0;j<=i;j++)
			{
				if(q<Price[j]+r[i-j])
				{
					q=Price[j]+r[i-j];
					s[i+1]=j+1;
				}
				
			}
			r[i+1]=q;
		}
		
		int n=Lenth;
		System.out.println(r[n]+":	");
		while(n>1)
		{
			System.out.println(s[n]);
			n=n-s[n];
		}
	}
	public CutSteal(int l)
	{
		Lenth=l;
	}
	
}