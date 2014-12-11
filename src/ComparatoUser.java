import java.util.Comparator;


public  class ComparatoUser implements Comparator<doctor>
{
	public int compare(doctor user0, doctor user1) {
		if(user0.agency.size()>user1.agency.size())
			return 0;
		return 1;
	}
}



