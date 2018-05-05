
public class userData {

	private String User;
	private String Xp;
	
	public userData(String user, String xp)
	{
		User = user;
		Xp = xp;
	}
	
	public String getUser(){ return User; }
	public void setUser(String user){ User = user; }
	
	public String getXp(){ return Xp; }
	public void setXp(String xp){ Xp = xp; }
	
	public String toString(){ return User + "," + Xp; }
	
	public static userData addNewuser(String lineFromText)
	{
		String[] temp = lineFromText.split(",");
		return new userData( temp[0], temp[1] );
	}
}
