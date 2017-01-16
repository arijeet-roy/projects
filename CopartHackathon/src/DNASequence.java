import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DNASequence {

	public DNASequence() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String readFileName = "DNATest.txt";
		String writeFileName = "DNATestOutput.txt";
		BufferedReader originTxt = new BufferedReader(new FileReader(readFileName));
		String dna = originTxt.readLine();
		char[] dnaArr = dna.toCharArray();
		DNASequence dnaSequence = new DNASequence();
		dnaSequence.findLongestSubstring(dnaArr, writeFileName);
	}

	private void findLongestSubstring(char[] dnaArr, String writeFileName) {
		// TODO Auto-generated method stub
		 char maxChar = '\0'; int maxCharCount = 0;
		
		for (int i = 0; i < dnaArr.length; i++) {
			int currMax = 1;
			int currentIndex = i + 1;
			while(currentIndex < dnaArr.length && dnaArr[i] == dnaArr[currentIndex]) {
				currMax++;
				currentIndex++;
			}
			if(currMax >= maxCharCount) {
				maxCharCount = currMax;
				maxChar = dnaArr[i];
				i = currentIndex;
			}
		}
		
		//delete before writing
		File deleteIdx = new File(writeFileName);
    	deleteIdx.delete();
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(writeFileName, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	try {
			writer.append(maxChar + " " + maxCharCount);
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

}
