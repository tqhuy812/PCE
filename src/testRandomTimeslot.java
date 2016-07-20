import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class testRandomTimeslot {
    ArrayList<Integer> factors = new ArrayList<Integer>();
//    ArrayList<Integer> multipliers = new ArrayList<Integer>();
//    ArrayList<Integer> increments = new ArrayList<Integer>();
    static int channel = 4;
	static int timeSlots = 7;
	int seed = 1; // choose btw 0<=seed<channel (not necessary)
	int nextChannel; // 0<a, 0<=c	(not necessary)
	static int[] multipliers = new int[timeSlots];
	static int[] increments = new int[timeSlots];
	// Should have a function to generate a,c,seed with constraints
	/* Constraints:
	 *	1) channel and c are relatively prime
	 *	2) a-1 is divisible by all prime factor of channel
	 *	3) a-1 is divisible by 4 if channel is divisible by 4
	 *=> Hull-Dobell Theorem		
	 */
	
//	nextChannel = (a*seed + c) % channel;
	
	public void primeFactors(int channel) {
	    int n = channel;
	    for (int i = 2; i <= n / i; i++) {
	      while (n % i == 0) {
	        factors.add(i);
	        n /= i;
	      }
	    }
	    if (n > 1) {
	      factors.add(n);
	    }
	  }
	
	public void generateA(){
		int a=1;
		int i=0;

		while (multipliers.length<timeSlots){
			if ((channel%4)==0){
				for (int prime : factors){
					if ( (((a-1)%4)==0) && (((a-1)%prime)==0)){
						
					}
				}
			}
			
			
			multipliers[i]=a;

			a++;
			i++;
		}
	}
	
	private static int getGCD(int value1, int value2)
	{
	    while (value1 != 0 && value2 != 0)
	    {
	        if (value1 > value2)
	            value1 %= value2;
	        else
	            value2 %= value1;
	    }
	    return Math.max(value1, value2);
	}
	
	private static boolean checkRelativelyPrime(int value1, int value2){
		return getGCD(value1,value2)==1;
	}
	
	public void generateC(){
		int c=0;
		int i=0;
		while(increments.length<timeSlots){
			if(checkRelativelyPrime(c,channel)){
				increments[i]=c;
			}
			c++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("LCG.txt");
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		
		
		for(int i=0;i!=multipliers.length;i++){
			bw.write(i+1);
			bw.write("\t");
			for (int j=0;j!=increments.length;j++){
				bw.write(String.valueOf(multipliers[i]));
			}
			bw.write("\n");
		}
		
		
	}

}
