
public class tempNode {
	public String x;
	public String y;
	public double d;
	tempNode(String x,String y,double d)
	{
		this.x=x;
		this.y=y;
		this.d=d;
	}
	@Override
	public String toString() {
		return x+"\t"+y+"\t"+d;
	}
}
