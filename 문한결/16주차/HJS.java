import java.io.*;
public class HJS {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static String[][] str;
    static String[][] strFlag;
    public static void main(String[] args) throws Exception{
        init();
        execute();
        if(strFlag[1][1].equals(strFlag[0][0]) && strFlag[1][0].equals(strFlag[0][1])
                || strFlag[1][0].equals(strFlag[1][1]) || strFlag[0][0].equals(strFlag[0][1])
        ){
            System.out.println("Hmm...");
        }else{
            System.out.println("HJS! HJS! HJS!");
        }

    }
    static void init() throws Exception{
        n=Integer.parseInt(br.readLine());
        str=new String[3][n];
        strFlag=new String [2][2];
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++)strFlag[i][j]="a";
        }
        for(int i=0;i<3;i++){
            str[i]=br.readLine().split("");
        }
    }
    static void execute(){

        for(int i=0;i<n;i++){
            if(!str[2][i].equals(str[1][i])){
                strFlag[1][0]=str[1][i];
                strFlag[1][1]=str[2][i];
                break;
            }
        }
        for(int i=0;i<n;i++){
            if(!str[1][i].equals(str[0][i])){
                strFlag[0][0]=str[0][i];
                strFlag[0][1]=str[1][i];
                break;
            }
        }
    }
}
