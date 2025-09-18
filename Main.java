import java.util.ArrayList;
import java.util.Arrays;
public class Main
{
    public static int occurance(String str){
        // had AI teach me how to manipulate arrays
        String[] word = str.split(" ");
        // had AI teach me how to change an array into an arraylist
        ArrayList<String> words = new ArrayList<> (Arrays.asList(word)); 
        for(int i = 0; i < words.length; i++){
            int count = 0;
            for(int j = i + 1; j < words.length; j++){
                if(words.get(i).equals(words.get(j))){
                    count++;
                }
                
            }
            
        }
        return count;
        }
        //Function doesn't work properly and I couldn't figure out how to get it to return more than one Integer
    }   //Didn't give myself enough time, I will start trying to code sooner rather than waiting.
    public static boolean unique(String str) {
        for(int i = 0; i < str.length(); i++){
            for(int j = i + 1; j < str.length(); j++){
            if(str.charAt(i) == str.charAt(j)){
                return false;
            }
            }
            
        }
        return true;
    }
	public static void main(String[] args) {
		String str = "Hey";
		Boolean result = unique(str);
		System.out.println(result);
		int resultTwo = occurance("in the beginning god created the heavens and the earth");
		System.out.println(resultTwo);
	}
}
	}

