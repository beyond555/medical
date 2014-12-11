import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Disease {

	public static void getDisease(int type) throws IOException
	{
		String IF,dir;
		IF=dir="";
		if(type==1)
		{
			IF="IF_English.csv";
			dir ="english/";
		}
		else if(type==2)
		{
			IF="IF_Chinese.csv";
			dir ="chinese/";
		}
	//	dir="trans/";
		File file=new File(dir);
		String test[];
		test=file.list();
		Arrays.sort(test);
		int i;
		for(i=0;i<test.length;i+=2)//每个病含两个文件
		{
			Util.filename1=dir+test[i+1];
			Util.filename2=dir+test[i];
			Util.filename3=test[i].split("_")[0];
			System.out.println(Util.filename3);
			deal(IF,type);
			Util.init();
			System.out.println(Util.author.size());
		}
	}
	public static void deal(String IF,int type) throws IOException
	{
//		Filter.step1();
		Count.step();
		if(type==2)
			Merge.process(0.6,0,false);//中文名判重
		else
			Merge.process(1,0,false);
		Resume.process();
		Relation.process();
//		Rank.deal();
		String command="python  python/main.py "+Util.filename2+" "+Util.filename3+" "+IF+" "+type;
		System.out.println(command);
		Process proc = Runtime.getRuntime().exec(command);  
	//	Process proc = Runtime.getRuntime().exec("ls");  
		InputStream in;
		try {
			 in= proc.getInputStream();
			 BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));  
			 String line = "";
			 while ((line = input.readLine()) != null) {  
	                System.out.println(line);
                }  
            in.close();
			proc.waitFor();
			System.out.println("end "+Util.filename3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
