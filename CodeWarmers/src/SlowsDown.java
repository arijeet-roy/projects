import java.io.*;
import java.util.*;

public class SlowsDown {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("slowdown.in"));
		PrintWriter output = new PrintWriter("slowdown.out");
		
		int numEvents = 0, numDistanceEvents = 0, numTimeEvents = 0;
		int distances[], times[];
		
		numEvents = input.nextInt();
		distances = new int[numEvents];
		times = new int[numEvents];
		
		for(int i = 0; i < numEvents; i++) {
			String eventType;
			eventType = input.next();
			if(eventType.equals("D"))
				distances[numDistanceEvents++] = input.nextInt();
			else if(eventType.equals("T"))
				times[numTimeEvents++] = input.nextInt();
		}
		
		sort(distances, numDistanceEvents);
		sort(times, numTimeEvents);
		
		int currentDistance = 0;
		int currentTime = 0;
		int currentRate = 1;
		int dindex = 0, tindex = 0;
		while((dindex <numDistanceEvents) && (tindex < numTimeEvents)) {
			double targetDistance = currentDistance + 
					(times[tindex] - currentTime * 1.0/currentRate);
			if(distances[dindex] < targetDistance) {
				currentTime += (distances[dindex] - currentDistance) *currentRate;
			} else {
				currentDistance = (int) targetDistance;
				currentTime = times[tindex++];
			}
			
			for(; dindex < numDistanceEvents; dindex++) {
				currentTime += (distances[dindex] - currentDistance) *currentRate;
				currentDistance = distances[dindex];
			}
			
			for(; tindex < numTimeEvents; tindex++) {
				currentDistance = (int) (times[tindex] - currentTime * 1.0/currentRate);
				currentTime = times[tindex++];
			}
			
			if(currentDistance < 1000) {
				currentTime += (1000 - currentDistance) * currentRate;
			}
		}
		
		output.write(currentTime);
		
		output.close();
	}
	
	public static void sort(int array[], int size) {
		for(int i = 0 ; i<size-1; i++) {
			int small = array[i];
			int smallIndex = i;
			for(int j = i+1; j<size; j++)
				if(array[j] < small) {
					small = array[j];
					smallIndex = j;
				}
			
			int temp = array[i];
			array[i] = array[smallIndex];
			array[smallIndex] = temp;
		}
	}
	
}