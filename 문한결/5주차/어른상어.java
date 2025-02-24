import java.util.*;
import java.io.*;

public class 어른상어 {

	static int[] dx= {0,0,-1,1}, dy= {-1,1,0,0};
    static int[] input;
    static int N,K,smellTimeDefult;
    static Shark[][] sharkMap;
    static Smell[][] smells;
    static int[][][] sharkDir;
    static HashMap<Integer,int[]> sharkPos=new HashMap();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int answer=0;
    public static void main(String[] args) throws IOException {
        init();
        sol();
        System.out.println(answer);
        }
	private static void init() throws IOException {
		input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
        		.toArray();
        N=input[0]; K=input[1];smellTimeDefult=input[2];
        sharkDir=new int[K+1][4][4];
        sharkMap=new Shark[N][N];
        smells=new Smell[N][N];

        for(int i=0;i<N;i++) {
        	for(int j=0;j<N;j++) {
        		sharkMap[i][j]=new Shark(0,0);
        		smells[i][j]=new Smell(0,0);
        	}
        }
        
        for(int i=0;i<N;i++) {
        input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
              		.toArray();
        	for(int j=0;j<N;j++) {
        		if(input[j]>0) {
        		sharkMap[i][j].value=input[j];
        		sharkPos.put(input[j], new int[] {j,i});
        		}
        		}
        }
        
        input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
        		.toArray();
        for(int i=0;i<K;i++) {
        int []xy=sharkPos.get(i+1);
        sharkMap[xy[1]][xy[0]].dir=input[i]-1;
        }
        for(int i=0;i<K;i++) {
        	for(int j=0;j<4;j++) {
        		input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                		.toArray();
        		for(int k=0;k<4;k++)sharkDir[i+1][j][k]=input[k]-1;
        	}
        }
	}
     public static void sol() {
    	while(true) {
    		if(isFinish()) {
    			return;
    		}
    		if(answer>=1000) {
    			answer=-1;
    			return;
    		}
    		makeSmells();            
    		moveSharks();
    		minusSmell();
    		answer++;
    	}
    }
     public static boolean isFinish() {
    	 for(int i=0;i<N;i++) {
    		 for(int j=0;j<N;j++)
    			 if(sharkMap[i][j].value>1)return false;
    	 }
    	 return true;
    	 }
     
     public static void makeSmells() {
    	 for(int v: sharkPos.keySet()) {
    		 int xy[]=sharkPos.get(v);
    		 smells[xy[1]][xy[0]].time=smellTimeDefult;
    		 smells[xy[1]][xy[0]].value=v;
    	 }
     }  
     public static void moveSharks() {
    	 HashMap<String,int[]> tempPos=new HashMap();
    	 Set<Integer> sets = new HashSet();
    	 
    	 for(int i:sharkPos.keySet())sets.add(i);
    	 
    	 for(int v: sets) {
    		 int [] decidedPos=decidePos(v);
    		 sharkMap[sharkPos.get(v)[1]][sharkPos.get(v)[0]].value=0;
     		 sharkMap[sharkPos.get(v)[1]][sharkPos.get(v)[0]].dir=0;
    		 if(tempPos.get(String.valueOf(decidedPos[0])+"!"+String.valueOf(decidedPos[1]))==null) {
    			 tempPos.put(String.valueOf(decidedPos[0])+"!"+String.valueOf(decidedPos[1]), new int[] {v,decidedPos[2]});
    			 continue;
    		 }
    		 conflictShark(tempPos,decidedPos,v);
    	 }
    	 for(String xy: tempPos.keySet()) {
    		 String str[]=xy.split("!");
    		 int x=Integer.valueOf(str[0]); int y=Integer.valueOf(str[1]);
    		 sharkPos.put(tempPos.get(xy)[0], new int[] {x,
    				 y,tempPos.get(xy)[1]});
    		 sharkMap[y][x]=new Shark(tempPos.get(xy)[1],tempPos.get(xy)[0]);
    	 }
     }
     
     public static int[] decidePos(int v) {
    	 int xy[]=sharkPos.get(v);
    	 for(int i=0;i<4;i++) {
    		 int nx=xy[0]+dx[sharkDir[v][sharkMap[xy[1]][xy[0]].dir][i]];
    		 int ny=xy[1]+dy[sharkDir[v][sharkMap[xy[1]][xy[0]].dir][i]];
    		 if(nx>=0 && nx<N&&ny>=0 && ny<N && smells[ny][nx].value==0)
    			 return new int[] {nx,ny,sharkDir[v][sharkMap[xy[1]][xy[0]].dir][i]};

    	 }
    	 for(int i=0;i<4;i++) {
    		 int nx=xy[0]+dx[sharkDir[v][sharkMap[xy[1]][xy[0]].dir][i]];
    		 int ny=xy[1]+dy[sharkDir[v][sharkMap[xy[1]][xy[0]].dir][i]];
    		 if(nx>=0 && nx<N&&ny>=0 && ny<N && smells[ny][nx].value==smells[xy[1]][xy[0]].value)
    			 return new int[] {nx,ny,sharkDir[v][sharkMap[xy[1]][xy[0]].dir][i]};

    	 }
    	 return new int[] {0,0,0};
     }
     
     static void conflictShark(HashMap<String,int[]> pos,int[]xy,int newShark) {
    	 int[] originShark=pos.get(String.valueOf(xy[0])+"!"+String.valueOf(xy[1]));
		 int removeSharkNumber=Math.max(newShark, originShark[0]);
		int rxy[]= sharkPos.get(removeSharkNumber);
		sharkPos.remove(removeSharkNumber);
		sharkMap[rxy[1]][rxy[0]].value=0;
		if(originShark[0]>newShark) {
			 pos.put(String.valueOf(xy[0])+"!"+String.valueOf(xy[1]), new int[] {newShark,xy[2]});
		}else
		 pos.put(String.valueOf(xy[0])+"!"+String.valueOf(xy[1]),
				 new int[] {originShark[0],originShark[1]});
     }
     
     public static void minusSmell() {
    	 for(int i=0;i<N;i++) {
    		 for(int j=0;j<N;j++) {
    			 smells[i][j].minus();
    		 }
    	 }
     }
    	
   static class Shark{
    	int dir;
    	int value;
    	public Shark(int d,int v) {
    		this.dir=d;
    		this.value=v;
    	}
    }
   static class Smell{
    	int time;
    	int value;
    	public Smell(int t,int v) {
    		this.time=t;
    		this.value=v;
    	}
    	public void minus() {
    		if(time>0)
    			time-=1;
    		if(time==0)
    			value=0;
    	}
    }
}
   
