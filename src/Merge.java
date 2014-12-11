import java.io.IOException;
import java.io.PrintStream;
import java.util.*;


public class Merge {
	/**
	 * 作者判重,机构名字相似的判断为一个作者
	 * @param thresh
	 * @throws IOException
	 */
	public static void process(double thresh,double thresh2,boolean use) throws IOException
	{
		int i,len=Util.list.size();
		doctor t;
		for(i=0;i<len;i++)
		{
			t=Util.list.get(i);
			deal(t,thresh,thresh2,use);
		}
		showClique(Util.list);
		map();
	}
	
	public static List<List<String>> bfs(doctor d,double thresh,double thresh2,boolean use)
	{
		int i,j,len=d.agency.size();
		List<String>l=new ArrayList<String>();
		for (String str : d.agency) {  
		      l.add(str);
		}
		boolean vis[]=new boolean[len];
		Queue<Integer>q;
		List<String>each;
		List<List<String>>C=new ArrayList<List<String>>();
		int head;
		for(i=0;i<len;i++)
		{
			if(vis[i]==true)
				continue;
			each=new ArrayList<String>();
			q=new LinkedList<Integer>();
			q.add(i);
			vis[i]=true;
			while(q.size()!=0)
			{
				head=q.poll();
				each.add(l.get(head));
				for(j=0;j<len;j++)
				{
					if(!vis[j]&&Algorithm.calSim(l.get(head), l.get(j), thresh,thresh2,use))
					{
						vis[j]=true;
						q.add(j);
					}
				}
			}
			C.add(each);
		}
		return C;
	}
	public static void deal(doctor d,double thresh,double thresh2,boolean use)
	{
		d.setClique(bfs(d,thresh,thresh2,use));
	}
	
	public static void showClique(List<doctor>list) throws IOException
	{
		int i,j,k,len=list.size(),listlen;
		doctor d;
		StringBuffer sb=new StringBuffer("");
		PrintStream ps=IO.setOut(Util.merge);
		List<List<String>>clique;
		List<String>sub;
		int clen,slen;
		String longname="";
		int length;
		List<tempNode>l=new ArrayList<tempNode>();
		for(i=0;i<len;i++)
		{
			d=list.get(i);
			listlen=d.agency.size();
			clique=d.clique;
			clen=clique.size();
		//	sb.append(d.name+"\t"+listlen+" agency and "+clen+" cliques \n");
			for(j=0;j<clen;j++)
			{
				sub=clique.get(j);
				slen=sub.size();
				length=0;
				for(k=0;k<slen;k++)
				{
					if(sub.get(k).length()>length)
					{
						length=sub.get(k).length();
						longname=sub.get(k);
					}
				}
				for(k=0;k<slen;k++)
				{
					d.m.put(sub.get(k), longname);
				/*	
					Algorithm.calSim(sub.get(k), longname, 0.6,1e-3,true);
					double dd=Algorithm.callFrequency(Algorithm.sb);
					l.add(new tempNode(sub.get(k),longname,dd));
					
					sb.append(d.name+"\t"+sub.get(k)+"\t"+longname+"\t"+dd+"\n");
				*/	
					sb.append(d.name+"\t"+sub.get(k)+"\t"+longname+"\n");
					
				}
			}
		}
		/*
		Collections.sort(l,new Comparator<tempNode>() {

			@Override
			public int compare(tempNode o1, tempNode o2) {
				if(o1.d>o2.d)
					return 1;
				else if(o1.d<o2.d)
					return -1;
				return 0;
			}
		});
		for(i=0;i<l.size();i++)
		{
			System.out.println(l.get(i).toString());
		}
		*/
		System.out.println(sb);
		ps.close();
		System.setOut(IO.ps);
	}
	public static void map()
	{
		int i,len=Util.list.size();
		doctor t;
		for(i=0;i<len;i++)
		{
			t=Util.list.get(i);
			Util.m.put(t.name, i+1);
		}
	}
}
