import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * 根据作者排名,生成作者,机构列表
 * @author ljw
 *
 */
public class Rank {
	public static void deal() throws IOException
	{
		getList();
	}
	public static void getList() throws IOException
	{
		IO.setInt(Util.rank);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintStream ps=IO.setOut(Util.outrank);
		int len;
		String name;
		String s;
		StringBuffer sb=new StringBuffer("");
		while((s=br.readLine())!=null)
		{
			name=s.split("\t")[1];
			len=name.length();
			name=name.substring(0,len-1);			
			sb.append(name+"\t"+Util.author_organize.get(name)+"\t"+s.split("\t")[2]+"\t"+"\n");
		}
		System.out.println(sb);
		ps.close();
	}
}
