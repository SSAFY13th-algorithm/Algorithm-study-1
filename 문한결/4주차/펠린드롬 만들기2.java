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



public class Main {
  
	private static int N,M,W;
	private static String str;
	private static String str2;
	private static String remainS="";

	
 
    public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
    	
    	str=br.readLine();
    	int answer=str.length();
    	str2=new String(str);
    	for(int i=0;i<str.length();i++) {
    		if(isPalin(str2))break;
    		remainS=str.charAt(i)+remainS;
    		str2=str+remainS;
    		answer+=1;
    	}
    	System.out.println(answer);
  
    }
    private static boolean isPalin(String s) {
    	for(int i=0;i<s.length()/2;i++) {
    		if(s.charAt(i)!=s.charAt(s.length()-i-1))
    		return false;
    	}
    	return true;
    	
    }


	
 }



