package classes;

public class Film {

	private String hyperbolic;
	private String story;
	private String subject1;
	private String subject2;
	private String subject3;
	private String verb;
	private String location;
	private String title;
	
	
	public Film()
	{
		//Load testdata
		hyperbolic="Fast-Paced";
		story="Drama";
		subject1="Mad Sientist";
		subject2="Database Administrator";
		subject3="Lumberjack";
		verb="Sink";
		location="The Canadian Rockies";
	}
	
	public void buildTitle() 
	{
		//  “A (hyperbolic) (story) of a (subject) And a (subject) who must (verb) a (subject) in (location)” 
		//   A . . of a . And a . who must . a . in .
		
		StringBuilder sb = new StringBuilder();
	}
	
}
