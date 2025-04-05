package solution;

import java.util.*;
import java.io.*;
public class 상어 {

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static int m,s;
    static int [] input;
    static Block [][]blocks;
    static int sharkX,sharkY;
    //사전순 
    static int[] dxByOrder={0,-1,0,1}, dyByOrder={-1,0,1,0};
    static int[] dx={-1,-1,0,1,1,1,0,-1}, dy={0,-1,-1,-1,0,1,1,1};
    static Block [][]temps;
    static int [][]smells;
    static int answer=0;
    static int fishMax=0;
    static boolean visited[][];
    static List<int[]> listPath;



    public static void main(String[] args)throws Exception {
        init();
        execute();
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                answer+=blocks[i][j].fishes.size();
            }
        }
        System.out.println(answer);


        
    }

    static void init() throws IOException{
        fishMax=0;
        visited=new boolean[4][4];
        input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        m=input[0]; s=input[1];
        blocks=BlockFactory.initialize();
        smells=new int[4][4];
      
        for(int i=0;i<m;i++){
            input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            blocks[input[0]-1][input[1]-1].fishes.add(input[2]-1);
        }
        input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        sharkX=input[1]-1; sharkY=input[0]-1;
    }

    static void execute(){
        for(int i=0;i<s;i++){
            fishMax=-1;
            listPath=new ArrayList<>();
            visited=new boolean[4][4];
        commandCopyFishes();

  
        moveFish();

        moveShark();
 
    

        reduceSmell();
        executeCopyFishes();



    }

    }
    static void commandCopyFishes(){
        temps=BlockFactory.initialize();
        BlockFactory.copyToFrom(blocks,temps);

    }
    static void moveFish(){
        Block [][]moveTemp = BlockFactory.initialize();
        
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(blocks[i][j].fishes.size()!=0){
                    for(int d: blocks[i][j].fishes){
                        moveFishOne(j, i, d, moveTemp);
                    }
                    
                }

            }
        }
        blocks=moveTemp;
    }
    static void moveShark(){

        findMaxFishes(0,sharkX,sharkY,0,new ArrayList<>());

        for(int xy[]: listPath){
            if(blocks[xy[1]][xy[0]].fishes.size()>0){
                smells[xy[1]][xy[0]]=-3;
                blocks[xy[1]][xy[0]].fishes=new ArrayList<>();
            }

        }

        int xy[]=listPath.get(listPath.size()-1);
            sharkX=xy[0]; sharkY=xy[1];
        
        


    }
    static void reduceSmell(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++)if(smells[i][j]<0)smells[i][j]++;
        }

    }
    static void executeCopyFishes(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                for(int d: temps[i][j].fishes)
                blocks[i][j].fishes.add(d);
            }
        }
    }
    //←, ↖, ↑, ↗, →, ↘, ↓, 
    static void moveFishOne(int x,int y,int d,Block[][] temps){
        boolean isNotMove=false;
        for(int i=0;i<8;i++){
            if(validate(x+dx[d], y+dy[d])){
                temps[y+dy[d]][x+dx[d]].fishes.add(d);
                isNotMove=true;
                break;
            }
            d=d-1;
            if(d==-1)d=7;
        }
        if(!isNotMove)temps[y][x].fishes.add(d);
    }
    static boolean validate(int x,int y){
        if(!OOB(x,y)|| smells[y][x]!=0 || (x==sharkX && y==sharkY)  ) return false;
        return true; 
    }
    static boolean OOB(int x,int y){
        if(x>=4 || x<0 || y>=4 || y<0)return false;
        return true;

    }
    static void findMaxFishes(int cnt, int x, int y,int fishNum,List<int []>list){
        if(cnt==3){
            if(fishNum>fishMax){
                listPath=new ArrayList<>();
                fishMax=fishNum;
                for(int []xy: list){
                    listPath.add(new int[]{xy[0],xy[1]});
                }
            
            }
            return;
        }
        for(int i=0;i<4;i++){
            int nx=x+dxByOrder[i];
            int ny=y+dyByOrder[i];
            if(OOB(nx,ny)){
               
                if(!visited[ny][nx]){
                    visited[ny][nx]=true;
                    list.add(new int[]{nx,ny});
                    findMaxFishes(cnt+1,nx,ny,fishNum+blocks[ny][nx].fishes.size(),list);
                    list.remove(list.size()-1);
                    visited[ny][nx]=false;
                }
                else{
                    list.add(new int[]{nx,ny});
                 findMaxFishes(cnt+1,nx,ny,fishNum,list);  
                 list.remove(list.size()-1);

                }
             
            }
        }

        

        
    }
    static void printFish(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                System.out.print(blocks[i][j].fishes.size()+" ");
            }
            System.out.println();
        }
    }
    
 
    
}

class Block{

    List<Integer>  fishes;
    public Block(){
        fishes=new ArrayList<>();
    }
    public List<Integer> clone(){
        List<Integer> cloneFishes=new ArrayList<>();
        for(int i: fishes)cloneFishes.add(i);
        return cloneFishes;
    }

}

class BlockFactory{

    static Block[][] initialize(){
        Block block[][];
        block=new Block[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                block[i][j]=new Block();
            }
        }
        return block;
    }

    static void copyToFrom(Block from[][], Block to[][]){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                to[i][j].fishes=from[i][j].clone();
            }
        }
    }

}

