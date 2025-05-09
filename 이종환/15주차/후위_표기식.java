import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 후위_표기식 {
	static String[] operations = {"+","-","*","/", "(",")"};
	static ArrayList<Unit> list = new ArrayList<>();;
	static Unit ans;
	static int startIdx, endIdx;
	
	
	static class Unit{
		int rank;
		String num;
		public Unit( String num) {
			super();
			this.rank = -2;
			for (int i = 0; i < 6; i++) {
				if(num.equals(operations[i])) {
					rank = i;
					break;
				}
			}
			this.num = num;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		process();
		print();
	}
	
	private static void print() {
		System.out.println(ans.num);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split("");
		for (int i = 0; i < input.length; i++) list.add(new Unit(input[i]));
	}

	private static void process() {
		while(true) {
			findBracket();
			if (endIdx == -1) {
				//전체에 괄호 없으면 순서대로 합침
				ans = processOperator(list);
				return;
			} 
			// 괄호 있는 부분을 때서 내부 처리 처리
			ArrayList<Unit> temp = new ArrayList<>();
			for (int i = startIdx+1; i<endIdx; i++) {
				temp.add(list.get(i));
			}
			for (int i = startIdx; i<= endIdx; i++) list.remove(startIdx);
			list.add(startIdx,processOperator(temp));
		}
	}
	

	private static Unit processOperator(ArrayList<Unit> l) {
		
		while(l.size() != 1) {
			int idx = -1;
			int oper = -1;
			for (int i = 0; i < l.size(); i++) {
				if(l.get(i).rank/2 > oper) {
					oper = l.get(i).rank/2;
					idx = i;
				}
			}
			
			change(l,idx);
		}
		return l.get(0);
	}
	

	private static void findBracket() {
		startIdx = -1;
		endIdx = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).rank == 4) startIdx = i;
			else if (list.get(i).rank == 5) {
				endIdx = i;
				return;
			}
		}
	}

	// 후위로 합쳐서 반환
	private static void change(ArrayList<Unit> l, int idx) {
		StringBuilder sb = new StringBuilder();
		sb.append(l.get(idx-1).num).append(l.get(idx+1).num).append(l.get(idx).num);
		l.remove(idx-1);
		l.remove(idx-1);
		l.remove(idx-1);
		l.add(idx-1,new Unit(sb.toString())); 
	}
}
