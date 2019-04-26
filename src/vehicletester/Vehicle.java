/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicletester;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import static vehicletester.VehicleTester.*;

/**
 *
 * @author Kyle's PC
 */
public class Vehicle {

    public static int passengers;
    public static double fareCost, gasPrice, dist, distTot, efficiency, tank, gasTot, gasUsed, cost, revenue, profit, remainingDist;
    public static String input;
    public static int window = 1;
    public static boolean trip;
    public static DecimalFormat decimal = new DecimalFormat("0.00");

    /**
     */
    private void makeVehicle() {  //store vehicle object passengers, fare price, cost of fuel, fuel efficiency, tank size 

        trip = false;

        switch (window) {

            case 1:
                input = JOptionPane.showInputDialog("How many passengers are in the vehicle?");
                check();
                try {
                    passengers = Integer.parseInt(input);
                } catch (Exception e) {
                    makeVehicle();
                }
                checkIn();
                break;

            case 2:
                input = JOptionPane.showInputDialog("What is the price of the fare? ($)");
                check();
                try {
                    fareCost = Double.parseDouble(input);
                } catch (Exception e) {
                    makeVehicle();
                }
                checkIn();
                break;

            case 3:
                input = JOptionPane.showInputDialog("What is the current cost of fuel? ($/L)");
                check();
                try {
                    gasPrice = Double.parseDouble(input);
                } catch (Exception e) {
                    makeVehicle();
                }
                checkIn();
                break;

            case 4:
                input = JOptionPane.showInputDialog("What is the vehicle's fuel efficiency? (km/L)");
                check();
                try {
                    efficiency = Double.parseDouble(input);
                } catch (Exception e) {
                    makeVehicle();
                }
                checkIn();
                break;

            case 5:
                input = JOptionPane.showInputDialog("How large is the vehicle's gas tank? (L)");
                check();
                try {
                    tank = Double.parseDouble(input);
                    gasTot = tank;
                    distTot = 0;
                } catch (Exception e) {
                    makeVehicle();
                }
                checkIn();
                
                trip = true;
                window = 1;
                break;
        }
    }

    private void check() {    //method to check for exit

        if (input == null) {
            System.exit(0);
        }
    }

    private void checkIn() { //method to check input

        if (isNullOrWhitespace(input)) {     //force input

            if (trip != true) {
                makeVehicle();
            } else {
                trips();
            }

        } else {    //next input

            if (trip != true) {
                window++;
                makeVehicle();
            } else {
                return;
            }

        }
    }

    private boolean isNullOrWhitespace(String input) { //check for proper inputs
        if (input == null) {
            return true;
        }

        for (int i = 0; i < input.length(); i++) {
            if (!Character.isWhitespace(input.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public Vehicle() {  //create vehicle object
        makeVehicle();
    }

    public void trips() {   //Add new trips to the vehicle and calculate revenue, cost, and profit

        remainingDist = efficiency * gasTot;

        input = JOptionPane.showInputDialog("How far will the vehicle be travelling?\nThere is " + decimal.format(remainingDist) + "km worth of gas remaining.");       //get distance of next trip
        check();
        try {
            dist = Double.parseDouble(input);
        } catch (Exception e) {
            trips();
        }
        checkIn();
        
        gasUsed = dist / efficiency;
        
        if ((gasTot - gasUsed) >= 0) {
            System.out.println("Trip #" + vehicleTrip + ":");
            System.out.println("Drive " + decimal.format(dist) + "kms. Start with " + decimal.format(gasTot) + "L of gas. Used " + decimal.format(gasUsed) + "L of gas.");
            gasTot -= gasUsed;
        } else {
            JOptionPane.showMessageDialog(null, "There is not enough gas in the vehicle do to this trip!");
            trips();
        }
        
        distTot += dist;
        cost = ((tank - gasTot) * gasPrice);
        revenue += (passengers * fareCost);
        profit = (revenue - cost);
        displayResults();

    }

    public boolean hasGas() {  //check if vehicle still has gas left to do another trip and has more than 1km remaining

        if (gasTot > 0.13333333 && remainingDist > 1) {
            return true;
        } else {
            return false;
        }

    }

    public String infoString() {  //vehicle object information to string

        String output = "Gas Kilometrage: " + decimal.format(efficiency) + " KM/L\n";
        output += "Fuel Tank Size: " + decimal.format(tank) + "L\n";
        output += "Fuel Available: " + decimal.format(gasTot) + "L\n";
        output += "Kilometres Driven: " + decimal.format(distTot) + " KM\n";
        output += "Passengers: " + passengers + "\n";
        output += "Fare: $" + decimal.format(fareCost) + "\n";
        output += "Fuel Cost: $" + decimal.format(gasPrice) + "/L\n\n";
        return output;

    }

    public String resultString() {  //profit information to string

        String result = "Final Results: \n";
        result += "Gas Remaining: " + decimal.format(gasTot) + "L\n";
        result += "Revenue: $" + decimal.format(revenue) + "\n";
        result += "Cost: $" + decimal.format(cost) + "\n";
        result += "Profit: $" + decimal.format(profit) + "\n";

        return result;

    }
}
