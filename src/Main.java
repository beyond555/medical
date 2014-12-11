import java.io.*;
import java.util.*;


public class Main {
	public static void main(String args[]) throws Exception 
	{
		
//		predeal();

		/*
		int type=2;//1 for english,2 for chinese
		Disease.getDisease(type);
		*/
		
		/*
		Final.mergeFinal();
		*/
		
		
		Province.step();
		
	}
	public static void predeal() throws IOException
	{
		Count.getFrequency("./data/authorListRanked");		
		Count.getDocList("./data/authorListRanked");
		Count.sort();
		Merge.process(0.6,30,true);		
	}
}
