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
	int f[];//并查集数组
	public int[][] arc;
	public int number;
    public int etv[];//最早发生时间
    public int ltv[];//最晚发生时间
	Stack<Vex> sVexs;//拓扑序列	
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
	 * 广度优先搜索
	 */
	public void BFS()
	{
		 Queue<Vex> qvexs=new PriorityQueue<>();
		 for(int i=0;i<number;i++)//连通图的话只会循环一次
		 {
			 if( vexs.get(i).visiable)
			 {
				 vexs.get(i).visiable=false;//标记为已经访问
				 System.out.println(vexs.get(i).num);//输出访问的结点
					qvexs.add(vexs.get(i));//加入队列
				while(!qvexs.isEmpty())
				{
					 Vex v=qvexs.poll();//出队列
					 for(int j=0;j<number;j++)//找到和这个结点直接连通的其他结点，未被访问过的。
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
		vexs.get(i).visiable=false;//标记访问
		System.out.println(vexs.get(i).num);
		for(int j=0;j<number;j++)//找到顶点相连每个点递归深度搜索
		{
			if(arc[i][j]>0&&arc[i][j]<maxnum&&vexs.get(j).visiable)
				DFS(j);//对这个点继续深度搜索到底
		}
	}
	
	/**
	 * 深度优先搜索
	 */
	public void DFS1()
	{
		
		for(int i=0;i<vexs.size();i++)
		{
			if(vexs.get(i).visiable)//对每个没访问过的顶点，深度遍历
				DFS(i);
		}
	}
	
	/**
	 * 所有结点对的最短路径，时间复杂度O(n3)参考算法导论25.2暂时没看懂
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
	 * Dijkastra算法，求单源s最短路径
	 * 要求所有边都是非负的，维持一组集合Q，从原点到集合中的点的最短路径已经得出
	 * 新加入一个点v不属于Q，对v到集合Q中的每个点的路径求min(len(s,v),p[j]+len(v,j))
	 * 时间复杂度O（n2）
	 * @param v0
	 */
	public void getDijkastra(int v0)
	{
		int p[]=new int[number];
		int d[]=new int[number];
		for(int i=0;i<number;i++)
		{
			p[i]=-1;//前驱数组
			d[i]=arc[v0][i];//初始化为源点到个点距离
		}
		vexs.get(v0).visiable=false;//源点标记为已经加入集合
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
			}//找到离源点最近的第一个点k加入集合
			vexs.get(k).visiable=false;
			
			for(int j=0;j<number;j++)//遍历所有k出发的结点j的d值，如果源点进过k到j结点小于从源点直接到j的距离，就更新d值为d[k]加上w<k,j>
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

	
	public int getFather(int i)//并查集，判断两个点是否属于一个集合
	{
		
		if(f[i]==i)
			return i;
		return f[i]=getFather(f[i]);//如果父节点不是自己，继续查找上级
	}
	public void join(int i,int j,int f[])
	{
		int x=i;
		while(f[x]!=x)
			x=f[x];
		f[x]=j;
		
	}
	/**
	 * 最小生成树Kruskal克鲁斯卡尔
	 */
	public void Kruskal()
	{
		int p[]=new int[edges.size()];
	    f=new int[number];
		for(int i=0;i<number;i++)
		{
			f[i]=i;
			
		}//初始化P数组表明第i条变是否加入最小生成树，f数组每个节点的父节点
		for(int i=0;i<edges.size();i++)
		{
			p[i]=0;
		}
		Collections.sort(edges,new Comparator<Edge>() {
			 
	            @Override
	            public int compare(Edge o1, Edge o2) {
	               
	                return o2.lenth-o1.lenth;
	            }
	        });//对边集数组进行排序
		 for(int i=edges.size()-1;i>=0;i--)
		 {
			 int a=getFather(edges.get(i).begin);
			 int b=getFather(edges.get(i).end);
			 
			 if(a!=b)
			 {
				 p[i]=1;
				 f[a]=b;
			 }
		 }//从小边开始，判断次边的两个结点是否构成回路，如果两结点的根结点相同时回路
		
		 for(int i=edges.size()-1;i>=0;i--)
		 {
			if(p[i]==1)
				System.out.println(edges.get(i).begin+","+edges.get(i).end);
		 }

	}
	
	/**
	 * 普里姆Prim随便找一个点为起点，从剩余的顶点中选一个代价最小的顶点加入集合，知道剩余顶点为空
	 */
	public void Prim()
	{
		
		int d[]=new int[number];
		int p[]=new int [number];
		for(int i=0;i<number;i++)
		{
			p[i]=arc[0][i];//初始化p数组为0顶点到各个顶点的路径
			d[i]=0;//初始化并查集数组
		}
		
		int k=0;
		p[0]=0;//0结点父节点是自己
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
			}//找到距离和0顶点和已经加入的集合的最短路径的顶点k，
			p[k]=0;//表明k结点已经找到
			  System.out.println(d[k]+"-"+k);
			for(int j=0;j<number;j++)//修改如果从0直接j顶点路径大于从k到j的长度，修改为从0-k-j
			{
				if(p[j]>arc[k][j]&&p[j]!=0)
				{
					p[j]=arc[k][j];
					d[j]=k;//j前驱是k
				}
			}
					
		}
		
	}
	
	/**
	 * 拓扑排序
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
					
			}//求每个顶点的入度
			vexs.get(i).index=n;
			if(!hasin)
				qVexs.push(vexs.get(i));//入度为0入栈
		}
		while(!qVexs.isEmpty())
		{
			Vex v0=qVexs.pop();//出栈并打印排序结点
			System.out.println((v0.num));

			lVexs.add(v0);
			for(int i=0;i<number;i++)//对出栈的下游结点入度减1，如果等于0继续入栈
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
	 * 获取拓扑序列,并计算etv
	 * @return
	 */
	public Stack<Vex> TopSortAOE()
	{
		Stack<Vex> qVexs=new Stack<>();
		sVexs=new Stack<>();
		etv=new int[number];//最早发生时间
		ltv=new int[number];//最晚发生时间
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
				}//找出来以v0的前驱的etv[i]+len(v0,i)的最大值etv[j]=max(etv[i]+arc[i],[j]),i是j的前驱
			}
            sVexs.push(v0);
		}
		return sVexs;
	}
	public void GetTopAoe()
	{
		Stack<Vex> sVex=TopSortAOE();	
		for(int i=number-1;i>=0;i--)//最晚发生时间，从终点往回计算
		{		
			if(i==number-1)//初始ltv终点=etv终点
				ltv[number-1]=etv[number-1];
			Vex vex=sVex.get(i);
			for(int j=0;j<number;j++)
			{
				if(i<number-1&&arc[vex.num][j]<maxnum&&arc[vex.num][j]!=0&&ltv[vex.num]>ltv[j]-arc[vex.num][j])
				{
				
					ltv[vex.num]=ltv[j]-arc[vex.num][j];
					
				}//ltv[i]=min(ltv[j]-arc[i][j]) j是i的后驱
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
				}//ete=lte则为关键路径
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