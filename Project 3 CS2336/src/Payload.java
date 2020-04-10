//Mohammed Ahmed, msa190000


public class Payload implements Comparable<Payload> 
{
	private String name;
	private int highScore;
	private String initials;
	private int plays;
	private double revenue;

	public Payload()
	{
		name = "";
		highScore = 0;
		initials = "";
		plays = 0;
		revenue = 0.0;
	}
	
	public Payload(String theName)
	{
		this();
		name = theName;
	}
	
	public Payload(String theName, int theHighScore, String theInitials, int thePlays)
	{
		this();
		name = theName;
		highScore = theHighScore;
		initials = theInitials;
		plays = thePlays;
		revenue = 0.25 * plays;
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
	
	public double getRevenue()
	{
		return revenue;
	}
	
	public String getRevenueAsTwoDecimalStringWithDollarSign()
	{
		String revenueString = String.format("$%.2f\n", revenue);
		return revenueString;
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
		revenue = 0.25 * plays;
	}


	@Override
	public int compareTo(Payload theOtherPayload)
	{
		return name.compareTo(theOtherPayload.getName());
	}

	@Override
	public String toString()
	{
		double revenue = 0.25 * plays;
		
		StringBuilder result = new StringBuilder();
		
		result.append("Name: " + name + "\n");
		result.append("High Score: " + highScore + "\n");
		result.append("Initials: " + initials + "\n");
		result.append("Plays: " + plays + "\n");
		//formatting the revenue string before appending it
		String revenueString = String.format("Revenue: $%.2f", revenue);
		result.append(revenueString);
		
		return result.toString();
	}
	
	public String toStringWithoutName()
	{
		double revenue = 0.25 * plays;
		
		StringBuilder result = new StringBuilder();
		
		result.append("High Score: " + highScore + "\n");
		result.append("Initials: " + initials + "\n");
		result.append("Plays: " + plays + "\n");
		//formatting the revenue string before appending it
		String revenueString = String.format("Revenue: $%.2f\n", revenue);
		result.append(revenueString);
		
		return result.toString();
	}
	
	
	@Override
	public boolean equals(Object someObject) //return true if the name contains the search term
	{
		if(!(someObject instanceof Payload) )
		{
			return false;
		}
		else
		{
			if(name.contains(((Payload)someObject).getName()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
}

