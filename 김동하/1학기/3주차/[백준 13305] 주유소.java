package one;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringBuilder sb = new StringBuilder();
	public static int n;
	public static long ans;
	public static int[] road = new int[100003];
	public static boolean[] isGas = new boolean[100003];
	public static class GasStation{
		int price;
		int idx;
		public GasStation(int idx, int price) {
			this.idx = idx;
			this.price = price;
		}
	}

	public static List<GasStation> list = new ArrayList<>();

	public static class GasStationComparator implements Comparator<GasStation> {
		@Override
		public int compare(GasStation o1, GasStation o2) {
			return o1.price - o2.price;
		}
	}
	
	
	public static void init() throws IOException{
		n = Integer.parseInt(br.readLine());
		int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for(int i = 0; i < n - 1; i++) {
			road[i] = in[i];
		}
		in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for(int i = 0; i < n; i++) {
			list.add(new GasStation(i, in[i]));
		}
		Collections.sort(list,new GasStationComparator());
	}

	public static void solution() throws IOException{
		init();
		for(GasStation g : list) {
			if(g.idx == n - 1) continue;
			for(int i = g.idx; i < n; i++) {
				if(isGas[i]) break;
				ans += ((long)road[i] * (long)g.price);
				isGas[i] = true;	
			}
		}
		sb.append(ans);
		bw.append(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args)throws IOException {
		solution();
	}

}
