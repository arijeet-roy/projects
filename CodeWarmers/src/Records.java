import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Records {

	public Records() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
//		System.out.println("Enter the number of hours : ");
		int hrs = scanner.nextInt();
//		System.out.println("Enter the 3 names for each hour : ");
		String str[][] = new String[hrs][3];
		for(int i=0; i<hrs; i++) {
			for(int j=0; j<3; j++) {
				str[i][j] = scanner.next();
			}
		}
		
		Records records = new Records();
		System.out.println(records.findMaxCount(str, hrs));
		
	}
	
	private int findMaxCount(String[][] str, int hrs) {
		// TODO Auto-generated method stub
		for(int i=0; i<hrs; i++) {
			List<String> strList = Arrays.asList(str[i]);
			Collections.sort(strList);
			str[i] = (String[]) strList.toArray();
		}
		
		Map<String, Integer> values = new HashMap<>();
		for(int i=0; i<hrs; i++) {
			values.put(str[i][0]+str[i][1]+str[i][2], 0);
		}
		
		for(int i=0; i<hrs; i++) {
			int value = values.get(str[i][0]+str[i][1]+str[i][2]);
			values.put(str[i][0]+str[i][1]+str[i][2], ++value);
		}
		return Collections.max(values.values());
	}

}
