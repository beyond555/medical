import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;


public class Province {
	public static void step()throws IOException
	{
		getcityProvince();
		gethositalProvince();
		getuniversityProvince();
		String dir="./finalAns/";
		File file=new File(dir);
		String test[];
		test=file.list();
		for(int i=0;i<test.length;i++)
		{
	//		System.out.println(dir+test[i]);
			resume_province(dir+test[i],dir+test[i]+"_ans");
		}
	}
	public static void getuniversityProvince() throws IOException
	{
		IO.setInt(Util.province_univeristy_dir);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		String a[];
		String t="";
		while((s=br.readLine())!=null)
		{
			a=s.split(",");
			if(a.length==1)
			{
				t=a[0];	
			}
			else
			{
				Util.university_province_map.put(a[1], find_province_by_city(t));
		//		System.out.println(a[1]+"\t"+t);
			}
		}
	}
	public static void gethositalProvince() throws IOException
	{
		String dir=Util.province_hos_dir;
		File file=new File(dir);
		String test[]=file.list();
		for(int i=0;i<test.length;i++)
		{
			gethositalProvinceInfo(dir+test[i],test[i]);
		}
	}
	public static void gethositalProvinceInfo(String file,String name) throws IOException
	{
		IO.setInt(file);
		int cnt=0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;		
		while((s=br.readLine())!=null)
		{
			cnt++;
			if(s.length()>0)
			{
				Util.hospital_province_map.put(Rule(s), name);
			}
		}
//		System.out.println(cnt);
	}
	public static String Rule(String org)
	{
		char c;
		if(org.contains("解放军"))
		{
			if(org.contains("中国人民"))
				org=org.substring(4);
//			StringBuilder s=new StringBuilder("解放军第");
			StringBuilder s=new StringBuilder("");
			for(int i=0;i<org.length();i++)
			{
				c=getnumber(org.charAt(i));
				if(c!=' ')
					s.append(c);
			}
			String t=s.toString();
			int id=t.lastIndexOf("医院");
			if(id!=-1)
				t=t.substring(0,id+2);
			id=t.lastIndexOf("第");
			if(id!=-1)
				t=t.substring(0,id)+t.substring(id+1);
		//	System.out.println(t);
			return t;
		}
		return org;
	}
	public static void getcityProvince() throws IOException
	{
		IO.setInt(Util.province_file);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		String a[]=new String[100];
		int i=0;
		while((s=br.readLine())!=null)
		{
			a=s.split("\t");
			for(i=0;i<a.length;i++)
			{
				if(a[i].charAt(a[i].length()-1)=='市')
					a[i]=a[i].substring(0,a[i].length()-1);
				Util.city_province_map.put(a[i], a[0]);
			}
		}
		Util.city_province_map.put("首都", "北京");
		Util.city_province_map.put("安徽","安徽");
	}
	public static String find_province_by_city(String organization)
	{
		Map<String,String>m=Util.city_province_map;
		for(String key : m.keySet())
		{			
			if(organization.contains(key))
				return m.get(key);
		}
		return null;
	}
	public static String find_province_by_hospital(String organization)
	{
		Map<String,String>m=Util.hospital_province_map;
		for(String key : m.keySet())
		{
			if(organization.contains(key)||key.contains(organization))
				return m.get(key);
		}
		return null;
	}
	public static String find_province_by_university(String organization)
	{
		Map<String,String>m=Util.university_province_map;
		for(String key : m.keySet())
		{
			if(organization.contains(key))
				return m.get(key);
		}
		return null;
	}
	public static void resume_province(String filename,String out) throws IOException
	{
		IO.setInt(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		String a[]=new String[100];
		int cnt=0;
		String ans;
		String t;
		IO.setOut(out);
		while((s=br.readLine())!=null)
		{
			a=s.split("\t");
			ans=find_province_by_city(a[1]);
			t=a[1];
			if(ans==null)
			{
				
				ans=find_province_by_university(t);
				if(ans==null)
				{
					t=Rule(t);
					ans=find_province_by_hospital(t);					
					if(ans==null)
					{
						cnt++;
					}
				}
			}
			System.out.println(a[0]+"\t"+ans+"\t"+a[1]);
		}
		System.out.println(cnt);
		IO.setdefault();
	}
	public static char getnumber(char a)
	{
	 	   switch(a)
	 	   {
	 	   case '十':
	 	   	return ' ';
	 	   case '百':
	 	   	return ' ';
	 		   case '一':
	 			   return '1';
	 		   case '二':
	 			   return '2';
	 		   case '三':
	 			   return '3';
	 		   case '四':
	 			   return '4';
	 		   case '五':
	 			   return '5';
	 		   case '六':
	 			   return '6';
	 		   case '七':
	 			   return '7';
	 		   case '八':
	 			   return '8';
	 		   case '九':
	 			   return '1';
	 		   case '零':
	 			   return '0';
	 		  case '○':
	 			   return '0';
	 	   }
	 	   return a;
	 }
}
