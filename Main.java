

import enums.VehicleType;
import models.*;
import pricing.*;
import pricing.Observer.FestivalSurchargeObserver;
import pricing.Observer.PeakHourSurchargeObserver;
import pricing.Observer.WeekendSurchargeObserver;
import repository.FareRepository;
import service.ParkingService;

import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        /* -------------------------
           1. CREATE PARKING LOT
        --------------------------*/

        ParkingSpot carSpot1 = new ParkingSpot("C1", VehicleType.CAR);
        ParkingSpot carSpot2 = new ParkingSpot("C2", VehicleType.CAR);

        ParkingFloor floor1 =
                new ParkingFloor("F1", List.of(carSpot1, carSpot2));

        ParkingLot parkingLot =
                new ParkingLot("Mall Parking", List.of(floor1));

        /* -------------------------
           2. PRICING STRATEGY
        --------------------------*/

        FareCalculationStrategy baseStrategy =
                new HourlyFareStrategy(
                        FareRepository.getHourlyRate(VehicleType.CAR)
                );

        FareCalculator fareCalculator =
                new FareCalculator(baseStrategy);

        /* -------------------------
           3. REGISTER SURCHARGE OBSERVERS
        --------------------------*/

        fareCalculator.registerObserver(new WeekendSurchargeObserver());
        fareCalculator.registerObserver(new PeakHourSurchargeObserver());
        fareCalculator.registerObserver(new FestivalSurchargeObserver(true));

        /* -------------------------
           4. GATES
        --------------------------*/

        EntryGate entryGate = new EntryGate("ENTRY-1");
        ExitGate exitGate = new ExitGate("EXIT-1", fareCalculator);

        /* -------------------------
           5. PARKING SERVICE
        --------------------------*/

        ParkingService parkingService =
                new ParkingService(parkingLot, entryGate, exitGate);

        /* -------------------------
           6. USER FLOW (CLI DEMO)
        --------------------------*/

        Vehicle car =
                new Vehicle("KA-01-1234", VehicleType.CAR);

        System.out.println("🚗 Vehicle entering parking lot...");
        Ticket ticket = parkingService.parkVehicle(car);

        Thread.sleep(3000); // simulate parking duration

        System.out.println("🚪 Vehicle exiting parking lot...");
        PaymentSlip slip = parkingService.exitVehicle(ticket);

        slip.print();
    }
}
