import java.time.*;

class Vehicle {
    String customerName;
    String phoneNumber;
    String vehicleNumber;
    String vehicleType;
    int slotNumber;

    int plannedHours;
    LocalDateTime entryTime;

    double amount;
    boolean isPaid;

    Vehicle(String customerName, String phoneNumber, String vehicleNumber,
            String vehicleType, int slotNumber, int plannedHours) {

        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.slotNumber = slotNumber;
        this.plannedHours = plannedHours;
        this.entryTime = LocalDateTime.now();

        this.amount = calculateInitialAmount();
        this.isPaid = false;
    }

    int getRate() {
        if (vehicleType.equals("2")) return 10;
        else if (vehicleType.equals("4")) return 20;
        else if (vehicleType.equals("8")) return 30;
        return 0;
    }

    double calculateInitialAmount() {
        return getRate() * plannedHours;
    }

    double calculateFinalAmount() {
        LocalDateTime exitTime = LocalDateTime.now();

        long actualHours = Duration.between(entryTime, exitTime).toHours();
        if (actualHours == 0) actualHours = 1;

        return getRate() * actualHours;
    }
}
