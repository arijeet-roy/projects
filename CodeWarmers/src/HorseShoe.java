import java.util.Scanner;

public class HorseShoe {

	public HorseShoe() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int size = scanner.nextInt();
		scanner.nextLine();
		String input[] = new String[size];
		char matrix[][] = new char[size][size];
		for(int i=0; i<size; i++) {
			input[i] = scanner.nextLine();
		}
		
		for(int i=0; i<input.length; i++) {
			matrix[i] = input[i].toCharArray();
		}
		int dx[] = {1, -1, 0, 0};
		int dy[] = {0, 0, 1, -1};
		boolean restriction[][] = new boolean[matrix.length][matrix[0].length];
		
		for(int i=0; i<restriction.length; i++) {
			for(int j=0; j<restriction[0].length; j++) {
				restriction[i][j] = true;
			}
		}

		int max = 0; String str = "";
		HorseShoe horseShoe = new HorseShoe();
		System.out.println(horseShoe.countUtil(matrix, 0, 0, max, str, 
				dx, dy, restriction));
	}

	private int countUtil(char [][] matrix, int row, int column, int max, String str, 
			int dx[], int dy[], boolean [][]restriction) {
		// TODO Auto-generated method stub
		if(row < 0 || column < 0 || row >= matrix.length || column >= matrix[0].length)
			return max;
		
		str = str+matrix[row][column];
		if(str.length() > 1 && str.length()%2 == 0) {
			String start = str.substring(0, str.length()/2);
			String end = str.substring(str.length()/2, str.length());
			if(!end.contains("(")) {
				String endTemp = end.replace(')', '(');
				if(endTemp.equals(start)) {
					max = 2*start.length();
				}				
			}
		}
		
		restriction[row][column] = false;
		for(int i=0; i<4; i++) {
			int x_dir = row + dx[i];
			int y_dir = column + dy[i];
			if(x_dir < 0 || y_dir < 0 || x_dir >= restriction.length || y_dir >= restriction[0].length)
				continue;
			if(restriction[x_dir][y_dir]) {
				max = Math.max(max, countUtil(matrix, x_dir, y_dir, 
						max, str, dx, dy, restriction));				
			}
		}
		restriction[row][column] = true;
		return max;
	}
}
