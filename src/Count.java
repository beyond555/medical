import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 统计,生成Journal,Title,Key的map
 * 同名作者合并agency list
 * @author ljw
 *
 */
public class Count {
	public static void step() throws IOException
	{
		Journal_Title_Key();
		getDocList(Util.filename1);
		sort();
		showList();	
//		summary();
	}
	/**
	 * 统计journal,title,key的三个map
	 * @throws IOException
	 */
	public static void Journal_Title_Key() throws IOException
	{
		initJournal_Title();
		outputTitle();
		outputJournal();
		/*
		initKey();
		outputKey();
		*/
		
	}
	public static void outputTitle()
	{
		StringBuffer sb=new StringBuffer("");
		PrintStream ps=IO.setOut(Util.titleMap);
		for(String key:Util.title.keySet())
		{
			sb.append(Util.title.get(key)+"\t"+key+"\n");
		}
		System.out.println(sb);
		ps.close();
		System.setOut(IO.ps);
	}
	public static void outputJournal()
	{
		StringBuffer sb=new StringBuffer("");
		PrintStream ps=IO.setOut(Util.journalMap);
		for(String key:Util.journal.keySet())
		{
			sb.append(Util.journal.get(key)+"\t"+key+"\n");
		}
		System.out.println(sb);
		ps.close();
		System.setOut(IO.ps);
	}
	public static void outputKey()
	{
		StringBuffer sb=new StringBuffer("");
		PrintStream ps=IO.setOut(Util.keyMap);
		for(String key:Util.key.keySet())
		{
			sb.append(Util.key.get(key)+"\t"+key+"\n");
		}
		System.out.println(sb);
		ps.close();
		System.setOut(IO.ps);
	}
	public static void initJournal_Title() throws IOException
	{
		IO.setInt(Util.filename1);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		String a[];
		String title,journal;
		int jid,tid;
		jid=tid=1;
		while((s=br.readLine())!=null)
		{
			a=s.split("\t");
			title=a[3];
			journal=a[2];
			if(!Util.title.containsKey(title))
			{
				Util.title.put(title, tid++);
			}
			if(!Util.journal.containsKey(journal))
			{
				Util.journal.put(journal, jid++);
			}
		}
	}
	
	public static void initKey() throws IOException
	{
		IO.setInt(Util.filename2);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		String a[];
		String key;
		int i,len;
		int kid=1;
		while((s=br.readLine())!=null)
		{
			a=s.split("\t");
			len=a.length;
			for(i=1;i<len;i++)
			{
				key=a[i];
				if(!Util.key.containsKey(key))
				{
					Util.key.put(key, kid++);
				}
			}
		}
	}
	
	/**
	 * 前提是文件按作者名排序了的
	 * @throws IOException
	 */
	public static void getDocList(String filename) throws IOException
	{
		IO.setInt(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		doctor pre=null;
		Util.list=new ArrayList<doctor>();
		while((s=br.readLine())!=null)
		{
			pre=count(pre,s);
		}
		if(pre!=null)
			Util.list.add(pre);
	}
	public static void getFrequency(String filename) throws IOException
	{
		IO.setInt(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		String a[];
		String angency;
		double id;
		char c;
		Map<Character,Double>count=new HashMap<Character,Double>();
		Set<Character>set;
		int num_of_people=0;
		while((s=br.readLine())!=null)
		{
			a=s.split("\t");
			if(a.length<2)
			{
				return ;
			}
			num_of_people+=1;
			angency=a[1];
			set=new HashSet<Character>();
			for(int i=0;i<angency.length();i++)
			{
				c=angency.charAt(i);
				if(!set.contains(c))
					set.add(c);
				else
					continue;
				if(count.containsKey(c))
				{
					id=count.get(c);
					count.put(c, id+1);
				}
				else
				{
					count.put(c, 1.0);
				}
			}
		}
		double d;
		for(Character cc: count.keySet())
		{
			d=count.get(cc);
			d=Math.log(num_of_people/d);
			count.put(cc,d);
			System.out.println(cc+" "+d);
		}
		Util.word_count=count;
	}
	/**
	 * 按照agency数量排序
	 */
	public static void sort()
	{
		ComparatoUser comparator = new ComparatoUser();
		Collections.sort(Util.list, comparator);
	}
	/**
	 * 显示同名的人对应的agency列表
	 * @throws IOException
	 */
	public static void showList() throws IOException
	{
		int i,len=Util.list.size(),listlen;
		doctor d;
		StringBuffer sb=new StringBuffer("");
		PrintStream ps=IO.setOut("./count");
		for(i=0;i<len;i++)
		{
			d=Util.list.get(i);
			listlen=d.agency.size();
			sb.append(d.name+"\t"+listlen+"\n");
			for (String str : d.agency) {  
			      sb.append(str+"\n"); 
			}
		}
		System.out.println(sb);
		ps.close();
		System.setOut(IO.ps);
	}
	
	public static doctor count(doctor pre,String s)
	{
		String a[]=s.split("\t");
		if(a.length<2)
		{
			return null;
		}
		String name=a[0];
		String angency=a[1];
		if(pre!=null&&name.equals(pre.name))
		{
			pre.agency.add(angency);
			return pre;
		}
		else
		{
			if(pre!=null)
				Util.list.add(pre);
			doctor now=new doctor(name,angency);
			return now;
		}
	}
	
	
	
	
	
	
	
	
	/*
	public static void summary()
	{
		int sum=0,max=0;
		int s[]=new int[100];
		int i,len=list.size(),listlen;
		doctor d;
		StringBuffer sb=new StringBuffer("");
		String temp;
		PrintStream ps=IO.setOut("./summary");
		for(i=0;i<len;i++)
		{
			d=list.get(i);
			listlen=d.agency.size();
			sum+=listlen;
			s[listlen]+=1;
			if(listlen>max)
				max=listlen;
		}
		DecimalFormat dt=(DecimalFormat) DecimalFormat.getInstance(); //获得格式化类对象
		dt.applyPattern("0.000");//设置小数点位数(两位) 余下的会四舍五入
		for(i=1;i<=max;i++)
		{
			double t=(double)s[i]/len;
			temp="people with "+ i+ "  agency : "+s[i]+"  percent: "+ dt.format(t*100.0) +"%\n";
			sb.append(temp);
		}
		sb.append("sum of agency : "+sum+"\n");
		sb.append("sum of people : "+len+"\n");
		System.out.println(sb);
		ps.close();
	}*/
}
