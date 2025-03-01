import java.util.*;
import java.io.*;
public class 파이어볼 {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

	static HashMap<Block,Fireball> maps=new HashMap();
	static int[]input;
	static int T;
	static int []dx=new int[]{0,1,1,1,0,-1,-1,-1};
	static int []dy=new int[]{-1,-1,0,1,1,1,0,-1};
    static int answer=0;

	static int N,M,K;
	static int mInput,sInput,dInput,xInput,yInput;
	public static void main(String[] args) throws IOException {
    input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    N=input[0]; M=input[1]; K=input[2];
    
    for(int i=0;i<M;i++) {
        input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        xInput=input[1];yInput=input[0];mInput=input[2]; sInput=input[3]; dInput=input[4];
        maps.put(new Block(xInput,yInput),new Fireball(mInput,sInput,dInput));

    }
    
    excute();
    
	System.out.println(answer);

	}
	public static void excute() {
		
		for(int i=0;i<K;i++) {
			HashMap<Block,Fireball> tempMaps=new HashMap();
			Set <Block>blocks=maps.keySet();
			moveFireballs(tempMaps, blocks);
			blocks=((HashMap<Block, Fireball>) tempMaps.clone()).keySet();
			removeDuplicatePos(tempMaps,blocks);
			maps=tempMaps;
		}
		for(Block b: maps.keySet()) {
			for (int j=0;j<maps.get(b).mList.size();j++) {
				answer+=maps.get(b).mList.get(j);
			}
		}
	}
    
	private static void moveFireballs(HashMap<Block, Fireball> tempMaps, Set<Block> blocks) {
		for(Block b:blocks ) {
			for(int j=0;j<maps.get(b).dList.size();j++) {
				int d=maps.get(b).dList.get(j);
				int m=maps.get(b).mList.get(j);
				int s=maps.get(b).sList.get(j);
				int nx = ((b.x - 1 + dx[d] * s) % N + N) % N + 1;
				int ny = ((b.y - 1 + dy[d] * s) % N + N) % N + 1;
				if(tempMaps.get(new Block(nx,ny))!=null) {
					tempMaps.get(new Block(nx,ny)).addSamePos(m, d,s);
					continue;
				}
				tempMaps.put(new Block(nx,ny), new Fireball(m,s,d));
			}
		}
	}
    
	public static void removeDuplicatePos(HashMap<Block, Fireball> tempMaps, Set<Block> blocks) {
		for(Block b: blocks) {	
			if(tempMaps.get(b).sList.size()>1) {
				int m=0,s=0;
				int []d=new int[] {0,2,4,6};
				int sumM=0,sumS=0;
				for(int j=0;j<tempMaps.get(b).sList.size();j++) {
					sumM+=tempMaps.get(b).mList.get(j);
					sumS+=tempMaps.get(b).sList.get(j);
				}
				if(!isAllSameOddEven(tempMaps.get(b).dList))d=new int[] {1,3,5,7};
				m=sumM/5; s=sumS/tempMaps.get(b).sList.size();
				 tempMaps.remove(b);

				if(m==0) {
				  continue;
				}
				for(int k=0;k<4;k++) {
					if(tempMaps.get(b)==null) {
					tempMaps.put(b, new Fireball(m,s,d[k]));
					continue;
					}
					tempMaps.get(b).addSamePos(m, d[k], s);
				}
			}
		}
	}
	public static boolean isAllSameOddEven(ArrayList<Integer> arr) {
		boolean isAllNotEven=false;
		boolean isAllNotOdd=false;
		for(int i:arr) {
			if(i%2!=0) {
				isAllNotEven=true;
			}
			else {
				isAllNotOdd=true;
			}
		}
		if(!isAllNotOdd || !isAllNotEven)return true;
		return false;
		
	}
	static class Fireball{
		ArrayList<Integer> mList;
		ArrayList<Integer> sList;
		ArrayList<Integer> dList;
		public Fireball( int m, int s,int d) {
			mList=new ArrayList();mList.add(m);
			dList=new ArrayList();dList.add(d);
			sList=new ArrayList();sList.add(s);
		}
		
		public void addSamePos(int m,int d,int s) {
			mList.add(m);
			dList.add(d);
			sList.add(s);
		}
	}
	static class Block{
		int x,y;
		public Block(int x,int y) {
			this.x=x;
			this.y=y;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Block other = (Block) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
	}
}