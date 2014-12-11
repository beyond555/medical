import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class Filter {
	//留下最长的机构名字
	public static void step1() throws IOException
	{
		IO.setInt("./author_splited_picked.txt");
//		IO.setInt("./test");
		PrintStream ps=IO.setOut("./author_splited_filter");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		StringBuffer sb=new StringBuffer("");
		while((s=br.readLine())!=null)
		{
			sb.append(filter(s));
			sb.append("\n");
		}
		System.out.println(sb.toString());
		ps.close();
	}
	public static StringBuffer filter(String s)
	{
		String a[]=s.split("\t");
		int i,len=a.length;
		StringBuffer ans=new StringBuffer("");
		String t="";
		int mlen=0;
		if(len>1)
		{
			for(i=1;i<len;i++)
			{
				if(a[i].length()>mlen)
				{
					mlen=a[i].length();
					t=a[i];
				}
			}
		}
		ans.append(a[0]).append("\t").append(t);
		return ans;
	}
}
