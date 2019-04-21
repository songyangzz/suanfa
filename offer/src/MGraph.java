import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;









public class MGraph
{
	final int maxnum=99999;
	public class Vex implements Comparable<Vex>
	{
		public int num;
		public boolean visiable=true;
		public int index=0;
		public  Vex(int num) {
			this.num=num;
		}
		@Override
		public int compareTo(Vex o) {
		    return o.num>num?-1:1;
		}
	}
	
	public class Edge
	{
	    public int lenth;
		public int begin;
		public int end;
		public Edge(int l,int b,int e)
		{
			lenth=l;
			begin=b;
			end=e;
			arc[b][e]=l;
			arc[e][b]=l;
		}
	}
	
	public  List<Vex> vexs=new ArrayList<>();
	
	public List<Edge> edges=new ArrayList<>();
	
	public Queue<Vex> qvexs=new PriorityQueue<>();
	int f[];//���鼯����
	public int[][] arc;
	public int number;
    public int etv[];//���緢��ʱ��
    public int ltv[];//������ʱ��
	Stack<Vex> sVexs;//��������	
	public MGraph(int num)
	{
	   number=num;	
	   
	   arc=new int[num][num];
	   for(int i=0;i<number;i++)
	   {
		   vexs.add(new Vex(i));
		   qvexs.add(new Vex(i));
		   for(int j=0;j<number;j++)
		   {
			   if(i==j)
				   arc[i][j]=0;
			   else
			   arc[i][j]=maxnum;
		   }
	   }
	}
	
	/**
	 * �����������
	 */
	public void BFS()
	{
		 Queue<Vex> qvexs=new PriorityQueue<>();
		 for(int i=0;i<number;i++)//��ͨͼ�Ļ�ֻ��ѭ��һ��
		 {
			 if( vexs.get(i).visiable)
			 {
				 vexs.get(i).visiable=false;//���Ϊ�Ѿ�����
				 System.out.println(vexs.get(i).num);//������ʵĽ��
					qvexs.add(vexs.get(i));//�������
				while(!qvexs.isEmpty())
				{
					 Vex v=qvexs.poll();//������
					 for(int j=0;j<number;j++)//�ҵ���������ֱ����ͨ��������㣬δ�����ʹ��ġ�
						{
							if(arc[v.num][j]>0&&arc[v.num][j]<maxnum&&vexs.get(j).visiable)
							{
								 System.out.println(vexs.get(j).num);
								 vexs.get(j).visiable=false;
								 qvexs.add(vexs.get(j));
							}
						}
				}
			 }
		 }
	}
	
	public void DFS(int i)
	{
		vexs.get(i).visiable=false;//��Ƿ���
		System.out.println(vexs.get(i).num);
		for(int j=0;j<number;j++)//�ҵ���������ÿ����ݹ��������
		{
			if(arc[i][j]>0&&arc[i][j]<maxnum&&vexs.get(j).visiable)
				DFS(j);//���������������������
		}
	}
	
	/**
	 * �����������
	 */
	public void DFS1()
	{
		
		for(int i=0;i<vexs.size();i++)
		{
			if(vexs.get(i).visiable)//��ÿ��û���ʹ��Ķ��㣬��ȱ���
				DFS(i);
		}
	}
	
	/**
	 * ���н��Ե����·����ʱ�临�Ӷ�O(n3)�ο��㷨����25.2��ʱû����
	 * @param v0
	 */
	public void getford(int v0)
	{
		
		int p[][]=new int[number][number];
		int d[][]=new int[number][number];
		for(int i=0;i<number;i++)
		{
		
			for(int j=0;j<number;j++)
			{
				p[i][j]=j;
				d[i][j]=arc[i][j];
			}
		}
		
		for(int m=0;m<number;m++)
		{
			for(int i=0;i<number;i++)
			{
			
				for(int j=0;j<number;j++)
				{
					
					   
						if(d[i][j]>d[i][m]+d[m][j])
						{
							p[i][j]=p[i][m];
							d[i][j]=d[i][m]+d[m][j];
							
						}
					
				}
			}
		}
	
		   System.out.print("v"+v0+"-");
			int j=p[v0][8];
		    while(j!=8)
		    {
		    	System.out.print("v"+j+"-");
		    	j=p[j][8];
		    }
			
			System.out.print(":"+d[v0][8]+"\n");
	}
	
	/**
	 * Dijkastra�㷨����Դs���·��
	 * Ҫ�����б߶��ǷǸ��ģ�ά��һ�鼯��Q����ԭ�㵽�����еĵ�����·���Ѿ��ó�
	 * �¼���һ����v������Q����v������Q�е�ÿ�����·����min(len(s,v),p[j]+len(v,j))
	 * ʱ�临�Ӷ�O��n2��
	 * @param v0
	 */
	public void getDijkastra(int v0)
	{
		int p[]=new int[number];
		int d[]=new int[number];
		for(int i=0;i<number;i++)
		{
			p[i]=-1;//ǰ������
			d[i]=arc[v0][i];//��ʼ��ΪԴ�㵽�������
		}
		vexs.get(v0).visiable=false;//Դ����Ϊ�Ѿ����뼯��
		for(int v=1;v<number;v++)
		{
			int k=0;
		    int min=maxnum;
			for(int i=0;i<number;i++)
			{
				
				if(vexs.get(i).visiable&&d[i]<min)
				{
					min=d[i];
					k=i;
				}
			}//�ҵ���Դ������ĵ�һ����k���뼯��
			vexs.get(k).visiable=false;
			
			for(int j=0;j<number;j++)//��������k�����Ľ��j��dֵ�����Դ�����k��j���С�ڴ�Դ��ֱ�ӵ�j�ľ��룬�͸���dֵΪd[k]����w<k,j>
			{
				
				if(vexs.get(j).visiable&&d[j]>min+arc[k][j])
				{
					p[j]=k;
					d[j]=min+arc[k][j];
					
				}
			}
			
		}
		for(int i=0;i<number;i++)
		{
			System.out.print("v"+i+"-");
			int j=p[i];
		    while(j!=-1)
		    {
		    	System.out.print("v"+j+"-");
		    	j=p[j];
		    }
			
			System.out.print(":"+d[i]+"\n");
		}
	}

	
	public int getFather(int i)//���鼯���ж��������Ƿ�����һ������
	{
		
		if(f[i]==i)
			return i;
		return f[i]=getFather(f[i]);//������ڵ㲻���Լ������������ϼ�
	}
	public void join(int i,int j,int f[])
	{
		int x=i;
		while(f[x]!=x)
			x=f[x];
		f[x]=j;
		
	}
	/**
	 * ��С������Kruskal��³˹����
	 */
	public void Kruskal()
	{
		int p[]=new int[edges.size()];
	    f=new int[number];
		for(int i=0;i<number;i++)
		{
			f[i]=i;
			
		}//��ʼ��P���������i�����Ƿ������С��������f����ÿ���ڵ�ĸ��ڵ�
		for(int i=0;i<edges.size();i++)
		{
			p[i]=0;
		}
		Collections.sort(edges,new Comparator<Edge>() {
			 
	            @Override
	            public int compare(Edge o1, Edge o2) {
	               
	                return o2.lenth-o1.lenth;
	            }
	        });//�Ա߼������������
		 for(int i=edges.size()-1;i>=0;i--)
		 {
			 int a=getFather(edges.get(i).begin);
			 int b=getFather(edges.get(i).end);
			 
			 if(a!=b)
			 {
				 p[i]=1;
				 f[a]=b;
			 }
		 }//��С�߿�ʼ���жϴαߵ���������Ƿ񹹳ɻ�·����������ĸ������ͬʱ��·
		
		 for(int i=edges.size()-1;i>=0;i--)
		 {
			if(p[i]==1)
				System.out.println(edges.get(i).begin+","+edges.get(i).end);
		 }

	}
	
	/**
	 * ����ķPrim�����һ����Ϊ��㣬��ʣ��Ķ�����ѡһ��������С�Ķ�����뼯�ϣ�֪��ʣ�ඥ��Ϊ��
	 */
	public void Prim()
	{
		
		int d[]=new int[number];
		int p[]=new int [number];
		for(int i=0;i<number;i++)
		{
			p[i]=arc[0][i];//��ʼ��p����Ϊ0���㵽���������·��
			d[i]=0;//��ʼ�����鼯����
		}
		
		int k=0;
		p[0]=0;//0��㸸�ڵ����Լ�
		for(int m=1;m<number;m++)
		{
			int min=maxnum;
			for(int i=0;i<number;i++)
			{
				if(min>p[i]&&p[i]!=0)
				{
					min=p[i];
					k=i;
				}
			}//�ҵ������0������Ѿ�����ļ��ϵ����·���Ķ���k��
			p[k]=0;//����k����Ѿ��ҵ�
			  System.out.println(d[k]+"-"+k);
			for(int j=0;j<number;j++)//�޸������0ֱ��j����·�����ڴ�k��j�ĳ��ȣ��޸�Ϊ��0-k-j
			{
				if(p[j]>arc[k][j]&&p[j]!=0)
				{
					p[j]=arc[k][j];
					d[j]=k;//jǰ����k
				}
			}
					
		}
		
	}
	
	/**
	 * ��������
	 */
	public void TopSort()
	{
		Stack<Vex> qVexs=new Stack<>();
		List<Vex> lVexs=new ArrayList<>();
		for(int i=0;i<number;i++)
		{
			boolean hasin=false;
			int n=0;
			for(int j=0;j<number;j++)
			{
				if(arc[j][i]!=0&&arc[j][i]<maxnum)
					{
						hasin=true;
						n++;
					}
					
			}//��ÿ����������
			vexs.get(i).index=n;
			if(!hasin)
				qVexs.push(vexs.get(i));//���Ϊ0��ջ
		}
		while(!qVexs.isEmpty())
		{
			Vex v0=qVexs.pop();//��ջ����ӡ������
			System.out.println((v0.num));

			lVexs.add(v0);
			for(int i=0;i<number;i++)//�Գ�ջ�����ν����ȼ�1���������0������ջ
			{
				if(arc[v0.num][i]<maxnum&&arc[v0.num][i]!=0)
				{
				
					if(--vexs.get(i).index==0)
						qVexs.push(vexs.get(i));
				}
			}
		}
		if(lVexs.size()<number)
			System.out.println("error");
	}
	
	/**
	 * ��ȡ��������,������etv
	 * @return
	 */
	public Stack<Vex> TopSortAOE()
	{
		Stack<Vex> qVexs=new Stack<>();
		sVexs=new Stack<>();
		etv=new int[number];//���緢��ʱ��
		ltv=new int[number];//������ʱ��
		for(int i=0;i<number;i++)
		{
			boolean hasin=false;
			int n=0;
			for(int j=0;j<number;j++)
			{
				ltv[j]=maxnum;
				etv[j]=0;
				if(arc[j][i]!=0&&arc[j][i]<maxnum)
					{
						hasin=true;
						n++;
					}
					
			}
			vexs.get(i).index=n;
			if(!hasin)
				qVexs.push(vexs.get(i));
		}
		while(!qVexs.isEmpty())
		{
			Vex v0=qVexs.pop();
			
			//System.out.println((v0.num));
			for(int i=0;i<number;i++)
			{
				if(arc[v0.num][i]<maxnum)
				{
				
					if(--vexs.get(i).index==0)
						qVexs.push(vexs.get(i));
					
				}
				if(v0.num>0&&arc[i][v0.num]<maxnum&&arc[v0.num][i]!=0&&etv[v0.num]<arc[i][v0.num]+etv[i])
				{
				
					etv[v0.num]=arc[i][v0.num]+etv[i];
				}//�ҳ�����v0��ǰ����etv[i]+len(v0,i)�����ֵetv[j]=max(etv[i]+arc[i],[j]),i��j��ǰ��
			}
            sVexs.push(v0);
		}
		return sVexs;
	}
	public void GetTopAoe()
	{
		Stack<Vex> sVex=TopSortAOE();	
		for(int i=number-1;i>=0;i--)//������ʱ�䣬���յ����ؼ���
		{		
			if(i==number-1)//��ʼltv�յ�=etv�յ�
				ltv[number-1]=etv[number-1];
			Vex vex=sVex.get(i);
			for(int j=0;j<number;j++)
			{
				if(i<number-1&&arc[vex.num][j]<maxnum&&arc[vex.num][j]!=0&&ltv[vex.num]>ltv[j]-arc[vex.num][j])
				{
				
					ltv[vex.num]=ltv[j]-arc[vex.num][j];
					
				}//ltv[i]=min(ltv[j]-arc[i][j]) j��i�ĺ���
			}
		}
		for(int i=0;i<number;i++)
		{
			for(int j=0;j<number;j++)
			{
				if(arc[i][j]<maxnum&&arc[i][j]!=0)
				{
					if(etv[i]==ltv[j]-arc[i][j])
						System.out.println(i+","+j);
				}//ete=lte��Ϊ�ؼ�·��
			}
		}
		
	}
/*	public static void main(String arg[])
	{
		MGraph G=new MGraph(14);
		G.arc[0][5]=1;G.arc[0][4]=1;G.arc[0][11]=1;
		G.arc[1][4]=1;G.arc[1][2]=3;G.arc[1][8]=7;
		G.arc[2][5]=5;G.arc[2][6]=3;G.arc[2][9]=1;
		G.arc[3][2]=7;G.arc[3][13]=2;
		G.arc[4][7]=5;
		G.arc[5][8]=7;G.arc[5][12]=3;
		G.arc[6][5]=3;		
		G.arc[8][7]=7;
		G.arc[9][11]=7;G.arc[9][10]=3;
		G.arc[10][13]=3;
		G.arc[12][9]=3;
		G.GetTopAoe();
	}*/
	public static void main(String arg[])
	{
		MGraph G=new MGraph(10);
		G.arc[0][1]=3;G.arc[0][2]=4;
		G.arc[1][3]=5;G.arc[1][4]=3;	
		G.arc[2][5]=7;G.arc[2][3]=8;
		G.arc[3][4]=3;
		G.arc[4][7]=4;G.arc[4][6]=9;
		G.arc[5][7]=6;
		G.arc[6][9]=2;		
		G.arc[7][8]=5;		
		G.arc[8][9]=3;
	   G.GetTopAoe();
	}
}