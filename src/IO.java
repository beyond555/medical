import java.io.*;


public class IO {
	public static PrintStream ps;
	public static void setInt(String filename)
	{
		BufferedInputStream in;
		try {
			in = new BufferedInputStream(new FileInputStream(filename));
			System.setIn(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static PrintStream setOut(String filename)
	{
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filename);
			BufferedOutputStream bos=new BufferedOutputStream(fos,1024);
			PrintStream ps=new PrintStream(bos,false);
			setdefault();
			System.setOut(ps);
			return ps;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void setdefault()
	{
		ps=System.out;
	}
}
