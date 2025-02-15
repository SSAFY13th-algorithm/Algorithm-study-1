import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;



public class Main {
  
	private static int N,M,W;
	private static String str;
	private static Set<String> set=new HashSet();

	
 
    public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
    	str=br.readLine();
    	for(int i=1;i<=str.length();i++) {
    		for(int j=0;j<=str.length()-i;j++) {
    			set.add(str.substring(j,j+i));
    		}
    	}
    	System.out.println(set.size());
    	
  
    }
  

	
 }



