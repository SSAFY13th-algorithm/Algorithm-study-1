import java.io.*;
import java.util.*;

public class Main {

	private static final int[]	dx = { 0, 1, 0, -1 };
	private static final int[]	dy = { 1, 0, -1, 0 };
	private static final int[]	SATISFACTION = { 0, 1, 10, 100, 1000 };

	private static int	n;

	private static int[][]	friendList;

	private static Student[][]	seat;

	/**
	 * elements of Priority Queue
	 */
	static class	Student implements Comparable<Student> {
		int	id;
		int	x;
		int	y;
		int	friendsNearby;
		int	emptySeatsNearby;

		Student(int id, int x, int y, int friendsNearby, int emptySeatsNearby) {
			this.id = id;
			this.x = x;
			this.y = y;
			this.friendsNearby = friendsNearby;
			this.emptySeatsNearby = emptySeatsNearby;
		}

		@Override
		public int compareTo(Student s) {
			// compare friends(desc)
			if (this.friendsNearby != s.friendsNearby)
				return Integer.compare(s.friendsNearby, this.friendsNearby);
			
			// compare empty seats(desc)
			if (this.emptySeatsNearby != s.emptySeatsNearby)
				return Integer.compare(s.emptySeatsNearby, this.emptySeatsNearby);
			
			// compare row(asc)
			if (this.x != s.x) 
				return Integer.compare(this.x, s.x);

			// compare column(asc)
			return Integer.compare(this.y, s.y);
		}
	}

	/**
	 * Check if the given index is out of bounds
	 * 
	 * @param i	row index
	 * @param j	column index
	 * @return	true if the index isn't out of bounds, false otherwise
	 */
	private static boolean oob (int i, int j) {
		return 0 <= i && i < n && 0 <= j && j < n;
	}

	public static void main(String[] args) throws IOException {
		sol();
	}

	private static void sol() throws IOException {
		int				self;
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st;

		n = Integer.parseInt(br.readLine());
		seat = new Student[n][n];
		friendList = new int[n*n+1][4];

		for (int i = 0; i < n * n; i++) {
			st = new StringTokenizer(br.readLine());
			self = Integer.parseInt(st.nextToken());

			for (int j = 0; j < 4; j++) {
				friendList[self][j] = Integer.parseInt(st.nextToken());
			}
			findBestSeat(self);
		}
		System.out.println(calcSatisfaction());
	}

	/**
	 * Find the best seat for the student
	 * 
	 * @param self	student's id
	 */
	private static void findBestSeat(int self) {
		PriorityQueue<Student>	pq = new PriorityQueue<>();

		int	friends;
		int	emptySeats;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (seat[i][j] == null) {
					friends = countFriends(i, j, self);
					emptySeats = countEmptySeats(i, j);

					pq.offer(new Student(self, i, j, friends, emptySeats));
				}
			}
		}
		Student	bestSeat = pq.poll();
		seat[bestSeat.x][bestSeat.y] = bestSeat;
	}

	/**
	 * Count the number of friends nearby
	 * 
	 * @param i		row index
	 * @param j		column index
	 * @param self	student's id
	 * @return		the number of friends nearby
	 */
	private static int countFriends(int i, int j, int self) {
		int	friends = 0;

		for (int d = 0; d < 4; d++) {
			int	nx = i + dx[d];
			int	ny = j + dy[d];

			if (!oob(nx, ny) || seat[nx][ny] == null) continue;
			if (isContaining(self, nx, ny)) {
				friends++;
			}
		}
		return friends;
	}

	/**
	 * Count the number of empty seats nearby
	 * 
	 * @param i	row index
	 * @param j	column index
	 * @return	the number of empty seats nearby
	 */
	private static int countEmptySeats(int i, int j) {
		int	empty = 0;
		
		for (int d = 0; d < 4; d++) {
			int	nx = i + dx[d];
			int	ny = j + dy[d];

			if (!oob(nx, ny)) continue;
			if (seat[nx][ny] == null) empty++;
		}
		return empty;
	}

	/**
	 * Calculate the total satisfaction
	 * 
	 * @return	the total satisfaction
	 */
	private static int calcSatisfaction() {
		int total = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int friends = countFriends(i, j, seat[i][j].id);
				if (friends > 0) {
					total += SATISFACTION[friends];
				}
			}
		}
		return total;
	}
	
	/**
	 * Check if the given student is in the friend list
	 * 
	 * @param self	student's id
	 * @param i		row index
	 * @param j		column index
	 * @return		true if the student is in the friend list, false otherwise
	 */
	private static boolean isContaining(int self, int i, int j) {
		boolean	flag = false;
		
		for (int fid : friendList[self]) {
			if (fid == seat[i][j].id) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}