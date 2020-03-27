//Mohammed Ahmed, msa190000

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Payload implements Comparable<Payload> 
{
	private String name;
	private int highScore;
	private String initials;
	private int plays;

	public Payload()
	{
		name = "";
		highScore = 0;
		initials = "";
		plays = 0;
	}
	
	public Payload(String theName, int theHighScore, String theInitials, int thePlays)
	{
		this();
		name = theName;
		highScore = theHighScore;
		initials = theInitials;
		plays = thePlays;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getHighScore()
	{
		return highScore;
	}
	
	public String getInitials()
	{
		return initials;
	}
	
	public int getPlays()
	{
		return plays;
	}
	
	
	public void setName(String theName)
	{
		name = theName;
	}
	
	public void setHighScore(int theScore)
	{
		highScore = theScore;
	}
	
	public void setInitials(String theInitials)
	{
		initials = theInitials;
	}
	
	public void setPlays(int thePlays)
	{
		plays = thePlays;
	}


	@Override
	public int compareTo(Payload theOtherPayload)
	{
		return name.compareTo(theOtherPayload.getName());
	}

	@Override
	public String toString()
	{
		
	}
}
