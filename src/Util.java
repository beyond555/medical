import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Util {
	public static Map<String,Integer>m=new HashMap<String,Integer>();
	public static Map<String,String>city_province_map=new HashMap<String,String>();
	public static Map<String,String>hospital_province_map=new HashMap<String,String>();
	public static Map<String,String>university_province_map=new HashMap<String,String>();
	public static List<doctor>list=new ArrayList<doctor>();
	public static Map<String,Integer>title=new HashMap<String,Integer>();
	public static Map<String,Integer>journal=new HashMap<String,Integer>();
	public static Map<String,Integer>key=new HashMap<String,Integer>();
	public static Map<String,Integer>author=new HashMap<String,Integer>();
	public static Map<String,String>author_organize=new HashMap<String,String>();
	public static Map<Character,Double>word_count=new HashMap<Character,Double>();
	public static String filename1="./data1";//info
	public static String filename2="./data2";//title-key
	public static String filename3="";//ansFile
	public static String titleMap="./info/titleMap";
	public static String journalMap="./info/journalMap";
	public static String keyMap="./info/keyMap";
	public static String merge="./info/merge";
	public static String AuthorMap = "./info/AuthorMap";
	public static String mydata="./mydata3";
	public static String rank="./rank/8";
	public static String outrank="./rank/ans8";
	public static String AuthorAgency="./info/AuthorAgency";
	public static String map="./info/map";
	public static String paper2key="./info/paper2key";
	public static String paper2author="./info/paper2author";
	public static String paper2journal="./info//paper2journal";
	public static String province_file="./data/city1.txt";
	public static String province_hos_dir="./data/hospital/";
	public static String province_univeristy_dir="./data/university.csv";
	public static List<AnsNode>ans;
	public static void init()
	{
		m=new HashMap<String,Integer>();
		list=new ArrayList<doctor>();
		title=new HashMap<String,Integer>();
		journal=new HashMap<String,Integer>();
		key=new HashMap<String,Integer>();
		author=new HashMap<String,Integer>();
		author_organize=new HashMap<String,String>();
		filename1="./data1";//info
		filename2="./data2";//title-key
		filename3="";//ansFile
	}
}
