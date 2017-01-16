import java.util.ArrayList;

public class BinarySearch {

	public BinarySearch() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> arrayList = new ArrayList<>();
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(4);
		arrayList.add(5);
		arrayList.add(8);
		BinarySearch binarySearch = new BinarySearch();
		System.out.println(binarySearch.findPos(arrayList, 0, 0, arrayList.size() - 1));
	}
	
	private int findPos(ArrayList<Integer> lis, int num, int start, int end) {
		// TODO Auto-generated method stub
		if (start == end) return start;
		else if(start < end) {
			int mid = (start + end) / 2;
			if(num > lis.get(mid)) {
				return findPos(lis, num, mid + 1, end);
			}
			else if(num < lis.get(mid)) {
				return 1 + findPos(lis, num, start, mid - 1);
			}
			else {
				return mid;
			}
			
		}
		else {
			return -1;
		}
	}


}
