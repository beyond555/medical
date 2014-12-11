import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class Relation {
	/**
	 * 生成<title,key>,<title,作者>,<title,期刊>三个关系map
	 * @throws IOException
	 */
	public static void process() throws IOException
	{	
		Title2Author();
		Title2Journal();
	}
	public static void Title2Author() throws IOException
	{
		IO.setInt(Util.mydata);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb=new StringBuffer("");
		PrintStream ps=IO.setOut(Util.paper2author);
		int len;
		String s;
		String a[];
		String title,author;
		while((s=br.readLine())!=null)
		{
			a=s.split("\t");
	//		sb.append(a.length+" "+a+"\n");
			len=a.length;
			if(len<2)//mydata最后一行的空格
				continue;
			title=a[2];
			author=a[0];
			sb.append(Util.title.get(title)+"\t"+Util.author.get(author)+"\n");
		}
		System.out.println(sb);
		ps.close();
		System.setOut(IO.ps);
	}
	public static void Title2Journal() throws IOException
	{
		IO.setInt(Util.mydata);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb=new StringBuffer("");
		PrintStream ps=IO.setOut(Util.paper2journal);
		int len;
		String s;
		String a[];
		String title,journal;
		while((s=br.readLine())!=null)
		{
			a=s.split("\t");
	//		sb.append(a.length+" "+a+"\n");
			len=a.length;
			if(len<2)//mydata最后一行的空格
				continue;
			title=a[2];
			journal=a[1];
			sb.append(title+"\t"+journal.toUpperCase()+"\n");
		}
		System.out.println(sb);
		ps.close();
		System.setOut(IO.ps);
	}
}
