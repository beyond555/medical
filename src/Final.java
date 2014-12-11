import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Final {
	public static void mergeFinal() throws Exception
	{
	//	String[] t={"屈光系统","玻璃体病","巩膜病","角膜病","结膜病","晶状体病","泪器病","葡萄膜炎","青光眼","视神经视路病变","视网膜病","眼表疾病","眼部肿瘤","眼睑病","眼眶病","眼外肌与弱视","眼外伤"};
		String[] t={"屈光系统","玻璃体病","巩膜病","晶状体病","泪器病","葡萄膜炎","青光眼","视神经视路病变","眼表疾病","眼部肿瘤","眼睑病","眼眶病","眼外肌与弱视","眼外伤"};

		int i;
		String dir1="python/chinese/";
		String dir2="python/english/";
		String file;
		List<AnsNode>ans;
		for(i=0;i<t.length;i++)
		{
			ans=new ArrayList<AnsNode>();
			file=dir1+t[i];
			set(file,ans);
			file=dir2+t[i];
			set(file,ans);

			output(t[i],ans);
		}
		
	}
	public static void set(String filename,List<AnsNode>ans) throws IOException
	{
		IO.setInt(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		String a[];
		while((s=br.readLine())!=null)
		{
			a=s.split("\t");
			ans.add(new AnsNode(a[2],a[0],Double.valueOf(a[1])));
		}
	}
	public static void output(String disease,List<AnsNode>ans)
	{
		StringBuffer sb=new StringBuffer("");
		PrintStream ps=IO.setOut("python/mergeAns/"+disease);
		Collections.sort(ans,new ComparatorAns());
		int i;
		for(i=0;i<ans.size();i++)
		{
			sb.append(ans.get(i).toString());
		}
		System.out.println(sb);
		ps.close();
		System.setOut(IO.ps);
	}
}
