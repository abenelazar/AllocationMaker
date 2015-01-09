import java.util.*;
import java.io.*;

public class DecisionMaker{
	public static void main(String args[])
				throws FileNotFoundException{
		Scanner csv = new Scanner(new File("data.csv"));

		while(csv.hasNextLine()){
			String currentRequest = csv.nextLine();			
			ArrayList<Double> allocationList = makeDecision(currentRequest);
			Collections.sort(allocationList);
			allocationList = removeOutliers(allocationList);
			double finalDecision = averageList(allocationList);
			System.out.println(finalDecision);
		}

	}
	
	
	public static ArrayList<Double> makeDecision(String currentRequest){
		ArrayList<Double> allocationList = new ArrayList<Double>();

		while(currentRequest.length() !=0 && currentRequest.contains(",")){
			double x = Double.parseDouble(currentRequest.substring(0, currentRequest.indexOf(",")));
			allocationList.add(x);
			currentRequest = currentRequest.substring(1 + currentRequest.indexOf(","));

		}
		double x = Double.parseDouble((currentRequest));
		allocationList.add(x);
		return allocationList;
	}
	

	public static ArrayList<Double> removeOutliers(ArrayList<Double> currentAllocation){
		if(currentAllocation.get(0) < currentAllocation.get(1)*.6){
			currentAllocation.remove(0);
		}
		if(currentAllocation.get(-1+currentAllocation.size()) > currentAllocation.get(-2+currentAllocation.size())*1.4){
			currentAllocation.remove(-1+currentAllocation.size());
		}
		return currentAllocation;
	}
	
	public static double averageList(ArrayList<Double> currentAllocation){
		int size = currentAllocation.size();
		double cumulativeSum = 0;
		for(int i = 0; i < size; i++){
			cumulativeSum += currentAllocation.get(i);
		}
		return (cumulativeSum/size);

	}

}