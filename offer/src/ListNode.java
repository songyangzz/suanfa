
public class ListNode {
  
    
	
	
	public static void main(String arg[])
	{
		MGraph G=new MGraph(9);
		G.arc[0][1]=1;G.arc[0][2]=5;
		G.arc[1][0]=1;G.arc[1][2]=3;G.arc[1][3]=7;G.arc[1][4]=5;
		G.arc[2][0]=5;G.arc[2][1]=3;G.arc[2][4]=1;G.arc[2][5]=7;
		G.arc[3][1]=7;G.arc[3][4]=2;G.arc[3][6]=3;
		G.arc[4][1]=5;G.arc[4][2]=1;G.arc[4][3]=2;G.arc[4][5]=3;G.arc[4][6]=6;G.arc[4][7]=9;
		G.arc[5][2]=7;G.arc[5][4]=3;G.arc[5][7]=5;
		G.arc[6][3]=3;G.arc[6][4]=6;G.arc[6][7]=2;G.arc[6][8]=7;
		G.arc[7][4]=9;G.arc[7][5]=5;G.arc[7][6]=2;G.arc[7][8]=4;
		G.arc[8][6]=7;G.arc[8][7]=4;
		G.getDijkastra(0);
	}
	/*public static void main(String arg[])
	{
		CutSteal cutSteal=new 	CutSteal(5);
		cutSteal.Price=new int[10
		                       ];
		cutSteal.Price[0]=1;
		cutSteal.Price[1]=5;
		cutSteal.Price[2]=8;
		cutSteal.Price[3]=9;
		cutSteal.Price[4]=10;
		cutSteal.Price[5]=17;
		cutSteal.Price[6]=17;
		cutSteal.Price[7]=20;
		cutSteal.Price[8]=24;
		cutSteal.Price[9]=30;
		cutSteal.CutWay();
	}*/
	
	/*public static void main(String arg[])
	{
		MGraph G=new MGraph(9);
		G.edges.add(G.new Edge(7, 4, 7));
		G.edges.add(G.new Edge(8, 2, 8));
		G.edges.add(G.new Edge(10, 0, 1));
		G.edges.add(G.new Edge(11, 0, 5));
		G.edges.add(G.new Edge(12, 1, 8));
		G.edges.add(G.new Edge(16, 3, 7));
		G.edges.add(G.new Edge(11, 0, 5));
		G.edges.add(G.new Edge(16, 1, 6));
		G.edges.add(G.new Edge(17, 5, 6));
		G.edges.add(G.new Edge(18, 1, 2));
		G.edges.add(G.new Edge(19, 6, 7));
		G.edges.add(G.new Edge(20, 3, 4));
		G.edges.add(G.new Edge(21, 3, 8));
		G.edges.add(G.new Edge(22, 2, 3));
		G.edges.add(G.new Edge(24, 3, 6));
		G.edges.add(G.new Edge(26, 4, 5));
		G.Kruskal();
	}*/
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
		G.TopSort();
	}*/
}