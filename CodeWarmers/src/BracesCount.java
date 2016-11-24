import java.util.Scanner;

public class BracesCount {

	public BracesCount() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		BracesCount bracesCount = new BracesCount();
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		System.out.println(bracesCount.findCountFirst(str, 0, 0));
	}
	
	private int findCountFirst(String str, int start, int count) {
		if(str.length() < 4) return 0;
		
		if(start == str.length() - 3) return count;
		
		if(str.charAt(start) == '(' && str.charAt(start+1) == '(') {
			count = findCountSecond(str, start+2, count);
		}
		
		return findCountFirst(str, start+1, count);
	}

	private int findCountSecond(String str, int start, int count) {
		// TODO Auto-generated method stub
		if(start == str.length() - 1) return count;
		if(str.charAt(start) == ')' && str.charAt(start+1) == ')') {
			count ++;
		}
		return findCountSecond(str, start+1, count);
	}
	
	
}
