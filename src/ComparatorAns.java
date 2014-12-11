import java.util.Comparator;


public  class ComparatorAns implements Comparator<AnsNode>
{
	public int compare(AnsNode user0, AnsNode user1) {
		if(user0.score>user1.score)
			return -1;
		if(user0.score<user1.score)
			return 1;
		return 0;
	}
}



