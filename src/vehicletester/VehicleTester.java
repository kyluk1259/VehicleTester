/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicletester;

import javax.swing.JOptionPane;

/**
 *
 * @author Kyle's PC
 */
public class VehicleTester {

    /**
     * @param args the command line arguments
     */
    public static Vehicle vehicle;
    public static int vehicleTrip = 1;
    public static boolean on = true;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    //create vehicles, add trips and display final information
        // TODO code application logic here
        newVehicle();

        while (on != false) {
            newTrip();
        }
        displayResults();

    }

    public static void displayResults() { //display information

        if (on != false) {
            System.out.println(vehicle.infoString());
        } else {
            System.out.println(vehicle.resultString());
        }

    }

    public static void newVehicle() {  //create new vehicle using vehicle object

        vehicle = new Vehicle();

        //check if vehicle information is correct, if not then repeat creating vehicle 
        int k = JOptionPane.showOptionDialog(null, "Is this information correct?\n" + vehicle.infoString(), "Confirmation", 0, JOptionPane.DEFAULT_OPTION, null, null, null);

        if (k != JOptionPane.YES_OPTION) {
            newVehicle();
        } else {
            return;
        }

    }

    public static void newTrip() {  //add new trips to the vehicle 

        vehicle.trips();
        //check to add new trip or display results
        if (vehicle.hasGas() != false) {

            int n = JOptionPane.showOptionDialog(null, "Would you like to add another trip?", "Another trip?", 0, JOptionPane.DEFAULT_OPTION, null, null, null);
            //if you choose yes (YES = 0) then continue the loop and increase trip count, or else end loop and display profit
            if (n == JOptionPane.YES_OPTION) {
                vehicleTrip++;
            } else {
                on = false;
            }

        } else {
            JOptionPane.showMessageDialog(null, "There is no longer enough gas in the vehicle do to another trip!");
            on = false;
        }

    }
}
