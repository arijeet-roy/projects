import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PieceOfCake {

	public PieceOfCake() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String readFileName = "PieceOfCakeTest.txt";
		String writeFileName = "PieceOfCakeTestOutput.txt";
		int numOfCases;
		int areaArr[];
		int resultArray[];
		try (BufferedReader br = new BufferedReader(new FileReader(readFileName))) {
			String line = br.readLine();
			numOfCases = Integer.parseInt(line);
			areaArr = new int[numOfCases];
			resultArray = new int[numOfCases];
			int i = 0;
			while ((line = br.readLine()) != null) {
				// process the line.
				areaArr[i] = Integer.parseInt(line);
				i++;
			}
			PieceOfCake cake = new PieceOfCake();
			File deleteIdx = new File(writeFileName);
			deleteIdx.delete();
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(writeFileName, true));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				for (int j = 0; j < areaArr.length; j++) {
					resultArray[j] = cake.findMinimumPerimeter(areaArr[j]);
					writer.append(String.valueOf(resultArray[j])+"\n");
				}
				writer.flush();
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	private int findMinimumPerimeter(int area) {
		int length = (int) Math.floor(Math.sqrt(area));
		int breadth = 0;

		for (int i = length; i > 0; i--) {
			if (area % i == 0) {
				breadth = area / i;
				break;
			}
		}
		length = area / breadth;
		return 2 * (length + breadth);
	}

}
