
import java.util.regex.Pattern;

public class test {
	public static void main(String[] args){
		String url = "coap://[fd00::c30c:0:0:2]:5683/test/routing-info";
//		String delims1 = Pattern.quote("\\[|\\]");
		String delims1 = "(//|\\[|\\])+";

		String[] tokens = url.split(delims1);
		int i = 0;
		for(i=0;i!=tokens.length;i++){
			System.out.println(tokens[i]);
			System.out.println("a");
			System.out.println("\n");
		}
	}
}
