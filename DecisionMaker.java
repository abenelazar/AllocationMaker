/*
 
 author: Ari Ben-Elazar
 date: January 9th, 2015
 This program is intended to take a specifically formatted .csv file of decisions for the 2014-2015 Finance Board at Brandeis University. It recieves the data.csv file which shares common directory and processes it line by line (Each line being a request), removing outliers, and averaging the result.
 
 */

import java.util.*;
import java.io.*;

public class DecisionMaker{
    public static void main(String args[])
				throws FileNotFoundException{
                    
                    /* Holds csv file */
                    Scanner csv = new Scanner(new File("data.csv"));
                    
                    /* Processes csv, makes decisions */
                    while(csv.hasNextLine()){
                        
                        String currentRequest = csv.nextLine();
                        
                        /* Pulls values of the currentRequest into an ArrayList */
                        ArrayList<Double> allocationList = getDecisions(currentRequest);
                        
                        /* Sorts ArrayList for outlier processing */
                        Collections.sort(allocationList);
                        
                        /* Removes outliers */
                        allocationList = removeOutliers(allocationList);
                        
                        /* Takes the refined list and averages the list into a final decision */
                        double finalDecision = averageList(allocationList);
                        
                        /* Writes decision */
                        System.out.println(finalDecision);
                        
                    }
                }
    
    /* Parses the line fetched from the .csv into an arrayList to be worked with in removeOutliers */
    public static ArrayList<Double> getDecisions(String currentRequest){
        ArrayList<Double> allocationList = new ArrayList<Double>();
        
        
        /* Loops through string to remove all values except last which will be fenceposted out */
        while(currentRequest.length() !=0 && currentRequest.contains(",")){
            
            /* Separates value of each individual decision by comma location and uses parseDouble to convert */
            double x = Double.parseDouble(currentRequest.substring(0, currentRequest.indexOf(",")));
            
            /* Adds decision to the list allocationList */
            allocationList.add(x);
            
            /* Cuts the current request string to remove the value just added */
            currentRequest = currentRequest.substring(1 + currentRequest.indexOf(","));
            
        }
        
        /* Corrects for fencepost */
        double x = Double.parseDouble((currentRequest));
        allocationList.add(x);
        
        
        return allocationList;
    }
    
    /* Removes the outliers from a sorted input array, currentAllocation and returns the "clean" output */
    public static ArrayList<Double> removeOutliers(ArrayList<Double> currentAllocation){
        /* Checks if the first in the sorted array is strictly 40% less than the second in the sorted array */
        if(currentAllocation.get(0) < currentAllocation.get(1)*.6){
            currentAllocation.remove(0);
        }
        /* Checks if the last in the sorted array is strictly 40% greater than the second to last in the sorted array */
        if(currentAllocation.get(-1+currentAllocation.size()) > currentAllocation.get(-2+currentAllocation.size())*1.4){
            currentAllocation.remove(-1+currentAllocation.size());
        }
        return currentAllocation;
    }

    /* Averages the resulting list and returns the result  */
    public static double averageList(ArrayList<Double> currentAllocation){
        int size = currentAllocation.size();

        /* Cumulative variable for looping */
        double cumulativeSum = 0;
        
        /* Loops through the list and adds up the values */
        for(int i = 0; i < size; i++){
            cumulativeSum += currentAllocation.get(i);
        }

        /* Divides the total by the number of entries (size) and returns */
        return (cumulativeSum/size);
        
    }
    
}