
public class Main
{
    public static void TopFive(int[] tempArray,int[] salesRevenue, int maxRevenue, int nextRevenue) {
        
		for(int i = 0; i < tempArray.length; i++){
		    for(int j = 0; j < salesRevenue.length; j++){
		        if(salesRevenue[j] > maxRevenue && salesRevenue[j] < nextRevenue){
		            maxRevenue = salesRevenue[j];
		            tempArray[i] = maxRevenue;
		            
		            
		        }
		    }
		    nextRevenue = maxRevenue;
		    maxRevenue = 0;
		}
		
		for(int l = 0; l < tempArray.length; l++){
		    System.out.println(tempArray[l]);
		}
    }
    
	public static void main(String[] args) {
		int[] tempArray = { 0, 0, 0, 0, 0,};
		int [] salesRevenue = { 312, 589, 476, 328, 601, 445, 390, 523, 317, 578, 341, 499, 365, 420, 560, 385, 432, 511, 398, 600};
		int maxRevenue = 0;
		int nextRevenue = 602;
		TopFive(tempArray, salesRevenue, maxRevenue, nextRevenue);
		}
	}

