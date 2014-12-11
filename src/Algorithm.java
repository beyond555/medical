import java.util.Collections;
import java.util.List;

//http://blog.csdn.net/cclive1601/article/details/7533945
public class Algorithm {
	static StringBuffer sb;
	static int b[][];
	public static boolean calSim(String X,String Y,double thresh,double thresh2,boolean use)
	{
		if(!use)
			return false;
		sb=new StringBuffer();
		lcs(X,Y);
		print(X,X.length(),Y.length());		
		double t=(double)sb.length()/Math.min(X.length(), Y.length());
	//	if(t>0.8)
	//		return true;
		if(t>=thresh)
		{
			if(callFrequency(sb)>thresh2)
			{
				return true;
			}
		}
		return false;
	}
	public static double callFrequency(StringBuffer sb)
	{
		double sum=0;
		int i;
		for(i=0;i<sb.length();i++)
		{
			sum+=1.0/(Util.word_count.get(sb.charAt(i)));
		}
		return sum;
	}
	public static void lcs(String X,String Y)
	{
		int N=X.length(),M=Y.length();
		int i,j,m,n;
		int e[][]=new int[N+1][M+1];
		b=new int[N+1][M+1];
		for(i=0;i<=N;++i)
			e[i][0]=0;
		for(j=0;j<=M;++j)
			e[0][j]=0;
		for(m=1;m<=N;++m)
		{
			for(n=1;n<=M;++n)
			{
				if(X.charAt(m-1)==Y.charAt(n-1))
				{
					e[m][n]=e[m-1][n-1]+1;
					b[m][n]=0;
				}
				else if(e[m-1][n]>=e[m][n-1])
				{
					e[m][n]=e[m-1][n];
					b[m][n]=1;
				}
				else
				{
					e[m][n]=e[m][n-1];
					b[m][n]=-1;
				}
			}
		}
	}
	public static void print(String X,int i,int j)
	{
		if(i==0||j==0)
			return ;
		if(b[i][j]==0)
		{
			sb.append(X.charAt(i-1));
			print(X,i-1, j-1);
		}
		else if(b[i][j]==1)
			print(X, i-1, j);
		else 
			print(X, i,j-1);
	}
}
