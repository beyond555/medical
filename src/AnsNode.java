
public class AnsNode {
	public double score;
	public String address;
	public String name;
	AnsNode(String address,String name,double score)
	{
		this.address=address;
		this.name=name;
		this.score=score;
	}
	@Override
	public String toString() {
		return name+"\t"+address+"\t"+score+"\n";
	}
	
}
