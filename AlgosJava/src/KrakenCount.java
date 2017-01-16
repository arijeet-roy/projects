
public class KrakenCount {

	public KrakenCount() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m = 3;
		int n = 3;
		System.out.println(krakenCount(m, n));
	}

	static int krakenCount(int m, int n) {
		// TODO Auto-generated method stub
		int noOfWays[][] = new int[m][n];
		
		for (int i = 0; i < noOfWays.length; i++) {
			noOfWays[i][0] = 1;
		}
		
		for (int j = 0; j < noOfWays[0].length; j++) {
			noOfWays[0][j] = 1;
		}
		
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				noOfWays[i][j] = noOfWays[i-1][j] + noOfWays[i][j-1] + noOfWays[i-1][j-1];
			}
		}
		
		return noOfWays[m-1][n-1];
	}

}
