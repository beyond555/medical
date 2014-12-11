import java.util.*;


public class doctor {
	public String name;
	public Set<String> agency;
	public List<List<String>>clique;
	public Map<String,String>m;
	doctor(String Name,String Agency)
	{
		name=Name;
		agency=new HashSet<String>();
		clique=new ArrayList<List<String>>();
		m=new HashMap<String,String>();
		agency.add(Agency);
	}
	public void setClique(List<List<String>> C)
	{
		clique=C;
	}
}

