import java.util.Scanner;

public class CowsMeet {

	static final int MAX = 50000;
	
	public CowsMeet() {
		// TODO Auto-generated constructor stub
	}
	
	private int countNoMeetings(int b_size, int e_size, char[] b_dir, char[] e_dir) {
		// TODO Auto-generated method stub
		int b_sum=0, e_sum=0, meet_count=0;
		int max = Math.max(b_size, e_size);

		for(int i=0; i<max; i++) {
			//in case the movements are in equal direction, then no need to change any sum
			if(b_dir[i] == e_dir[i]) continue;
			//-1 for left case
			if(b_dir[i] == 'L') {
				b_sum--;
			}
			//+1 for right case
			else if(b_dir[i] == 'R') {
				b_sum++;
			}
			
			if(e_dir[i] == 'L') {
				e_sum--;
			}
			else if(e_dir[i] == 'R') {
				e_sum++;
			}
			//reset the count once the cows meet.
			if(b_sum == 0 && e_sum == 0) meet_count++;
		}
		return meet_count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int b_rows = scanner.nextInt();
		int e_rows = scanner.nextInt();
		char[] b_dir = new char[MAX];
		char[] e_dir = new char[MAX];
		
		int b_size=0;
		//take input for belsie for the rows
		for(int i=0; i<b_rows; i++) {
			int b_each_row = scanner.nextInt();
			String direction = scanner.next();
			char dir = direction.charAt(0);
			for(int j=0; j< b_each_row; j++) {
				b_dir[b_size++] = dir;
			}
		}
		
		int e_size=0;
		//take input for elsie for the rows
		for(int i=0; i<e_rows; i++) {
			int e_each_row = scanner.nextInt();
			String direction = scanner.next();
			char dir = direction.charAt(0);
			for(int j=0; j< e_each_row; j++) {
				e_dir[e_size++] = dir;
			}
		}
		CowsMeet cowsMeet = new CowsMeet();
		System.out.println(cowsMeet.countNoMeetings(b_size, e_size, b_dir, e_dir));

	}

}
