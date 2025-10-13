public class Main
{
    public static int disciplesTrained(long startingDisciples, long peopleInWorld){
        int years = 0;
        while(peopleInWorld > startingDisciples){
            startingDisciples = startingDisciples * 2;
            years++;
        }
        return years;
    }
    public static long trainedDisciplesEachYear(long disciples, long peopleWorld, int yearsDone){
        long disciplesPerYear = 1L;
        while(peopleWorld > disciples){
            for(int i = 1; i <= yearsDone; i++){
                disciples = disciples * disciplesPerYear;
            }
            disciplesPerYear++;
        }
        return disciplesPerYear;
    }
	public static void main(String[] args) {
	    long disciples = 13L;
	    long people = 7700000000L;
	    int years = 50;
	int returned = disciplesTrained(disciples, people);
	System.out.println(returned);
	long returned2 = trainedDisciplesEachYear(disciples, people, years);
	System.out.println(returned2);
	}
}
