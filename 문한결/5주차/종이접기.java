import java.util.*;
import java.io.*;
public class 종이접기 {

    static String input;
    static int T;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<String> strList;
    public static void main(String[] args) throws IOException {
        T=Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++) {
        	strList=new ArrayList();
        	input=br.readLine().trim();
        	for(int j=0;j<input.length();j++) {
        		strList.add(input.substring(j,j+1));
        	}
        	
        	System.out.println(isFold()?"YES":"NO");
        }
        
    }
    public static boolean isFold() {

    	while(strList.size()>1) {
    		ArrayList<String> tempList=new ArrayList();
    		for(int i=0;i<strList.size()/2;i++) {
    			if(strList.get(i).equals(strList.get(strList.size()-i-1))) return false;
    			tempList.add(strList.get(i));
    		}
    		strList=tempList;
    	}
    	return true;
    }
}
