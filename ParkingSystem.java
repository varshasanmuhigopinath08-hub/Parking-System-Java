import java.util.*;
import java.time.*;
import java.awt.Desktop;
import java.io.File;

public class ParkingSystem {

    static ArrayList<Vehicle> parkingList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static int totalSlots = 5;
    static boolean[] slots = new boolean[totalSlots];

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- Parking Lot System ---");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    parkVehicle();
                    break;
                case 2:
                    removeVehicle();
                    break;
                case 3:
                    System.out.println("Come Again!👋");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // 🚗 PARK VEHICLE
    static void parkVehicle() {

        if (parkingList.size() == totalSlots) {
            System.out.println("Parking Full!");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Your Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Your Phone Number: ");
        String phone = sc.nextLine();

        System.out.print("Enter Vehicle Number: ");
        String vNumber = sc.nextLine();

        System.out.print("Enter Vehicle Type (2=🚲two wheeler, 4=🚗four wheeler, 8=🚚load vehicles): ");
        String type = sc.nextLine();

        // 🔹 Ask planned hours
        System.out.print("⏰Enter number of hours to park: ");
        int hours = sc.nextInt();

        // 🔹 Show available slots
        System.out.print("Available Slots: ");
        for (int i = 0; i < totalSlots; i++) {
            if (!slots[i]) {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println();

        // 🔹 Choose slot
        System.out.print("Choose Slot Number: ");
        int chosenSlot = sc.nextInt() - 1;

        if (chosenSlot < 0 || chosenSlot >= totalSlots || slots[chosenSlot]) {
            System.out.println("Invalid or Occupied Slot!");
            return;
        }

        slots[chosenSlot] = true;

        Vehicle v = new Vehicle(name, phone, vNumber, type, chosenSlot, hours);
        parkingList.add(v);

        System.out.println("Vehicle Parked at Slot: " + (chosenSlot + 1));
        System.out.println("🕛Entry Time: " + v.entryTime);

        // 🔹 Initial cost
        System.out.println("Initial Cost: ₹" + v.amount);

        sc.nextLine();
        System.out.print("Payment Method (UPI/Cash): ");
        String payment = sc.nextLine();
        if (payment.equalsIgnoreCase("UPI")) {
    System.out.println("Scan this UPI ID: parking@upi");
    try {
        File file = new File("qr.png");
        Desktop.getDesktop().open(file);
    } catch (Exception e) {
        System.out.println("QR code not found!");
    }
    System.out.println("Amount: ₹" + v.amount);
}

v.isPaid = true;
System.out.println("Payment Successful via " + payment+"👍");
    }

    // 🚗 REMOVE VEHICLE
    static void removeVehicle() {

        sc.nextLine();

        System.out.print("Enter Vehicle Number: ");
        String vNumber = sc.nextLine();

        for (Vehicle v : parkingList) {
            if (v.vehicleNumber.equalsIgnoreCase(vNumber)) {

                double finalAmount = v.calculateFinalAmount();

                System.out.println("Customer: " + v.customerName);
                System.out.println("Slot: " + (v.slotNumber + 1));
                System.out.println("🕛Entry Time: " + v.entryTime);
                System.out.println("🕒Exit Time: " + LocalDateTime.now());

                System.out.println("Initial Paid: ₹" + v.amount);
                System.out.println("Final Amount: ₹" + finalAmount);

                if (finalAmount > v.amount) {
                    double extra = finalAmount - v.amount;
                    System.out.println("Extra Payment Required: ₹" + extra);

                    System.out.print("Pay extra (UPI/Cash): ");
                    String payment = sc.nextLine();

                    System.out.println("Extra Payment Done via " + payment);
                } else {
                    System.out.println("No extra payment needed.");
                }
                if (finalAmount < v.amount) {
                     double refund = v.amount - finalAmount;
                     System.out.println("Refund Amount: ₹" + refund);

                } else {
                     System.out.println("Exact amount paid.");
}

                slots[v.slotNumber] = false;
                parkingList.remove(v);

                System.out.println("Vehicle Removed Successfully!");
                return;
            }
        }

        System.out.println("Vehicle Not Found!");
    }
}
