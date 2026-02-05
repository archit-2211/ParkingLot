package repository;


import enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class FareRepository {

    private static final Map<VehicleType, Double> fareMap = new HashMap<>();

    static {
        fareMap.put(VehicleType.BIKE, 20.0);
        fareMap.put(VehicleType.CAR, 50.0);
        fareMap.put(VehicleType.TRUCK, 100.0);
    }

    public static double getHourlyRate(VehicleType type) {
        return fareMap.get(type);
    }
}
