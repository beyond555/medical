import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成<作者,id>,<作者,机构>的map
 * @author ljw
 *
 */
public class Resume {
	private static String prename=null;
	private static Map<String,Integer>Uid=new HashMap<String, Integer>();
	private static int uid=1;
	public static void init()
	{
		prename=null;
		Uid=new HashMap<String, Integer>();
		uid=1;
	}
	public static void process() throws IOException
	{
		init();
		resumeList();
		getAuthorMap();
		showAuthorMap();
		showAuthorAgencyMap();
	}
	/**
	 * AuthorMap 作者名对应id
	 */
	public static void showAuthorMap()
	{
		StringBuffer sb=new StringBuffer("");
		PrintStream ps=IO.setOut(Util.AuthorMap);
		for(String key:Util.author.keySet())
		{
			sb.append(key+"\t"+Util.author.get(key)+"\n");
		}
		System.out.println(sb);
		ps.close();

		System.setOut(IO.ps);
	}
	/**
	 * 作者对应机构
	 */
	public static void showAuthorAgencyMap()
	{
		StringBuffer sb=new StringBuffer("");
		PrintStream ps=IO.setOut(Util.AuthorAgency);
		for(String key:Util.author_organize.keySet())
		{
			sb.append(key+"\t"+Util.author_organize.get(key)+"\n");
		}
		System.out.println(sb);
		ps.close();

		System.setOut(IO.ps);
	}
	/**
	 * 还原list,即作者(已判重),期刊,论文名
	 * @throws IOException
	 */
	public static void resumeList() throws IOException
	{
		IO.setInt(Util.filename1);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintStream ps=IO.setOut(Util.mydata);
		String s;
		StringBuffer sb=new StringBuffer("");
		while((s=br.readLine())!=null)
		{
			sb.append(resume(s));
		}
		System.out.println(sb);
		ps.close();
		System.setOut(IO.ps);
	}
	
	public static StringBuffer resume(String s)
	{
		String a[]=s.split("\t");
		StringBuffer sb=new StringBuffer("");
		int i,len=a.length;
		if(a.length<2)
		{
			return sb;
		}
		String name=a[0];
		if(prename==null||!prename.equals(name))
		{
			Uid=new HashMap<String, Integer>();
		}
		prename=name;
		String angency=a[1];
		String map;
		doctor t;
		int id;
		int Uidsize=0;
		id=Util.m.get(name);
		t=Util.list.get(id-1);
		map=t.m.get(angency);
		if(Uid.get(map)==null)
		{
			Uidsize=Uid.size();
			Uid.put(map,Uidsize+1);
		}
		for(i=0;i<len;i++)
		{
			if(i==0)
			{
				sb.append(name+Uid.get(map)+"\t");
				continue;
			}
			else if(i==1)
			{
				continue;
			}
			sb.append(a[i]+"\t");
		}
		sb.append("\n");
		return sb;
	}
	/**
	 * 获取作者(已判重)加机构信息
	 * @throws IOException
	 */
	public static void getAuthorMap() throws IOException
	{
		IO.setInt(Util.filename1);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintStream ps=IO.setOut(Util.map);
		String s;
		StringBuffer sb=new StringBuffer("");
		StringBuffer temp;
		while((s=br.readLine())!=null)
		{
			temp=AuthorMap(s);
			if(temp!=null)
				sb.append(temp);
		}
		System.out.println(sb);
		ps.close();

		System.setOut(IO.ps);
	}
	
	public static StringBuffer AuthorMap(String s)
	{
		String a[]=s.split("\t");
		StringBuffer sb=new StringBuffer("");
		if(a.length<2)
		{
			return null;
		}
		String name=a[0];
		if(prename==null||!prename.equals(name))
		{
			Uid=new HashMap<String, Integer>();
		}
		prename=name;
		String angency=a[1];
		String map;
		doctor t;
		int id;
		int Uidsize=0;
		id=Util.m.get(name);
		t=Util.list.get(id-1);
		map=t.m.get(angency);
		String U;
//		System.out.println(name+" "+prename+" "+Uid.size());
		if(Uid.get(map)==null)
		{
			Uidsize=Uid.size();
			Uid.put(map,Uidsize+1);
			U=name+Uid.get(map);
			sb.append(name+Uid.get(map)+" "+map);
			sb.append("\n");
			if(!Util.author.containsKey(U))
			{
				Util.author.put(U, uid++);
				Util.author_organize.put(U, map);
			}
			return sb;
		}
		return null;
	}
}
