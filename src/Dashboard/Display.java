package Dashboard;

import Model.*;

import java.util.List;

public class Display {

    public static void printAreas(List<Area> areas) {
        if (areas == null || areas.isEmpty()) {
            System.out.println("⚠️ No area data found.");
            return;
        }

        System.out.println("\n🌐 Area List");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-12s %-12s %-18s%n", "ID", "Name", "Latitude", "Longitude", "Emergency Point");
        System.out.println("--------------------------------------------------------------------------------");

        for (Area a : areas) {
            String emergencyStatus = a.isEmergencyPoint() ? "✅ Yes" : "❌ No";
            System.out.printf("%-5d %-20s %-12.6f %-12.6f %-18s%n",
                    a.getAreaId(),
                    a.getName(),
                    a.getLatitude(),
                    a.getLongitude(),
                    emergencyStatus);
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    public static void printBusList(List<Bus> buses) {
        if (buses == null || buses.isEmpty()) {
            System.out.println("⚠️ No bus data found.");
            return;
        }

        System.out.println("\n🚌 Bus List");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-10s %-15s %-15s%n", "ID", "License Plate", "Capacity", "Route ID", "Area ID");
        System.out.println("--------------------------------------------------------------------------------");

        for (Bus b : buses) {
            String route = (b.getCurrentRouteId() != null) ? String.valueOf(b.getCurrentRouteId()) : "—";
            String area = (b.getCurrentAreaID() != null) ? String.valueOf(b.getCurrentAreaID()) : "—";

            System.out.printf("%-5d %-15s %-10d %-15s %-15s%n",
                    b.getId(),
                    b.getLicensePlate(),
                    b.getCapacity(),
                    route,
                    area);
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    public static void printEmergencyServices(List<EmergencyService> services) {
        if (services == null || services.isEmpty()) {
            System.out.println("⚠️ No emergency services found.");
            return;
        }

        System.out.println("\n🚨 Emergency Services List");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-15s %-10s %-15s %-20s%n", "ID", "Name", "Type", "AreaID", "Contact", "Available Vehicles");
        System.out.println("--------------------------------------------------------------------------------------");

        for (EmergencyService es : services) {
            System.out.printf("%-5d %-20s %-15s %-10d %-15d %-20d%n",
                    es.getId(),
                    es.getName(),
                    es.getType(),
                    es.getAreaId(),
                    es.getContactNumber(),
                    es.getAvailableVehicles());
        }

        System.out.println("--------------------------------------------------------------------------------------");
    }

    public static void printMetroList(List<Metro> metros) {
        if (metros == null || metros.isEmpty()) {
            System.out.println("⚠️ No metro data found.");
            return;
        }

        System.out.println("\n🚇 Metro List");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-10s %-15s %-15s%n", "ID", "Train Name", "Capacity", "Route ID", "Area ID");
        System.out.println("--------------------------------------------------------------------------------");

        for (Metro m : metros) {
            String route = (m.getCurrentRouteID() != null) ? String.valueOf(m.getCurrentRouteID()) : "—";
            String area = (m.getCurrentAreaID() != null) ? String.valueOf(m.getCurrentAreaID()) : "—";

            System.out.printf("%-5d %-20s %-10d %-15s %-15s%n",
                    m.getId(),
                    m.getTrainName(),
                    m.getCapacity(),
                    route,
                    area);
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    public static void printParkingLots(List<ParkingLot> parkingList){
        if (parkingList == null || parkingList.isEmpty()) {
            System.out.println("⚠️ No parking lot data found.");
            return;
        }

        System.out.println("\n🅿️ Parking Lot List");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-10s %-10s %-15s %-10s%n", "ID", "Name", "AreaID", "Capacity", "Occupied", "Status");
        System.out.println("--------------------------------------------------------------------------------");

        for (ParkingLot p : parkingList) {
            int available = p.getCapacity() - p.getCurrentOccupancy();
            String status = (available > 0) ? "🟢 Available" : "🔴 Full";

            System.out.printf("%-5d %-20s %-10d %-10d %-15d %-10s%n",
                    p.getId(),
                    p.getName(),
                    p.getAreaId(),
                    p.getCapacity(),
                    p.getCurrentOccupancy(),
                    status);
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    public static void printRoutes(List<Route> routes) {
        if (routes == null || routes.isEmpty()) {
            System.out.println("⚠️ No route data found.");
            return;
        }

        System.out.println("\n🛣️ Route List");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-10s %-15s %-15s%n", "ID", "Name", "Length(km)", "Bus Route", "Metro Route");
        System.out.println("--------------------------------------------------------------------------------");

        for (Route r : routes) {
            String busStatus = r.isBusRoute() ? "✅ Yes" : "❌ No";
            String metroStatus = r.isMetroRoute() ? "✅ Yes" : "❌ No";

            System.out.printf("%-5d %-20s %-10.2f %-15s %-15s%n",
                    r.getId(),
                    r.getName(),
                    r.getLength(),
                    busStatus,
                    metroStatus);
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    public static void printStations(List<Station> stations) {
        if (stations == null || stations.isEmpty()) {
            System.out.println("⚠️ No station data available.");
            return;
        }

        System.out.println("\n🚏 Station List");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-10s %-15s %-15s%n", "ID", "Name", "Area ID", "Bus Station", "Metro Station");
        System.out.println("--------------------------------------------------------------------------------");

        for (Station s : stations) {
            String busStatus = s.isBusStation() ? "✅ Yes" : "❌ No";
            String metroStatus = s.isMetroStation() ? "✅ Yes" : "❌ No";

            System.out.printf("%-5d %-20s %-10d %-15s %-15s%n",
                    s.getId(),
                    s.getName(),
                    s.getAreaId(),
                    busStatus,
                    metroStatus);
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    public static void printStreets(List<Street> streets) {
        if (streets == null || streets.isEmpty()) {
            System.out.println("⚠️ No street data available.");
            return;
        }

        System.out.println("\n🛤️ Street List");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-5s %-12s %-12s %-10s %-10s%n", "ID", "Start Area", "End Area", "Distance(km)", "One Way");
        System.out.println("--------------------------------------------------------------------------------");

        for (Street s : streets) {
            String oneWayStatus = s.isOneWay() ? "➡️ Yes" : "↔️ No";

            System.out.printf("%-5d %-12d %-12d %-10.2f %-10s%n",
                    s.getId(),
                    s.getStartAreaId(),
                    s.getEndAreaId(),
                    s.getDistance(),
                    oneWayStatus);
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    public static void printTouristPlaces(List<TouristPlace> places) {
        if (places == null || places.isEmpty()) {
            System.out.println("⚠️ No tourist place data available.");
            return;
        }

        System.out.println("\n🗺️ Tourist Places");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-15s %-10s %-10s%n", "ID", "Name", "Category", "Area ID", "Ratings");
        System.out.println("--------------------------------------------------------------------------------");

        for (TouristPlace tp : places) {
            System.out.printf("%-5d %-20s %-15s %-10d %-10.1f%n",
                    tp.getId(),
                    tp.getName(),
                    tp.getCategory(),
                    tp.getAreaId(),
                    tp.getRatings());
        }

        System.out.println("--------------------------------------------------------------------------------");
    }

    public static void printArea(Area area) {
        if (area == null) {
            System.out.println("⚠️ Area not found.");
            return;
        }

        System.out.println("\n🌐 Area Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", area.getAreaId());
        System.out.printf("Name: %s%n", area.getName());
        System.out.printf("Latitude: %.6f%n", area.getLatitude());
        System.out.printf("Longitude: %.6f%n", area.getLongitude());
        System.out.printf("Emergency Point: %s%n", area.isEmergencyPoint() ? "✅ Yes" : "❌ No");
        System.out.println("--------------------------------------------------");
    }

    public static void printStreet(Street street) {
        if (street == null) {
            System.out.println("⚠️ Street not found.");
            return;
        }

        System.out.println("\n🛣️ Street Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", street.getId());
        System.out.printf("Start Area ID: %d%n", street.getStartAreaId());
        System.out.printf("End Area ID: %d%n", street.getEndAreaId());
        System.out.printf("Distance: %.2f km%n", street.getDistance());
        System.out.printf("One Way: %s%n", street.isOneWay() ? "➡️ Yes" : "↔️ No");
        System.out.println("--------------------------------------------------");
    }

    public static void printStation(Station station) {
        if (station == null) {
            System.out.println("⚠️ Station not found.");
            return;
        }

        System.out.println("\n🚏 Station Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", station.getId());
        System.out.printf("Name: %s%n", station.getName());
        System.out.printf("Area ID: %d%n", station.getAreaId());
        System.out.printf("Bus Station: %s%n", station.isBusStation() ? "✅ Yes" : "❌ No");
        System.out.printf("Metro Station: %s%n", station.isMetroStation() ? "✅ Yes" : "❌ No");
        System.out.println("--------------------------------------------------");
    }

    public static void printMetro(Metro metro) {
        if (metro == null) {
            System.out.println("⚠️ Metro not found.");
            return;
        }

        System.out.println("\n🚇 Metro Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", metro.getId());
        System.out.printf("Train Name: %s%n", metro.getTrainName());
        System.out.printf("Capacity: %d%n", metro.getCapacity());
        System.out.printf("Current Route ID: %s%n", metro.getCurrentRouteID() != null ? metro.getCurrentRouteID() : "—");
        System.out.printf("Current Area ID: %s%n", metro.getCurrentAreaID() != null ? metro.getCurrentAreaID() : "—");
        System.out.println("--------------------------------------------------");
    }

    public static void printEmergencyService(EmergencyService es) {
        if (es == null) {
            System.out.println("⚠️ Emergency service not found.");
            return;
        }

        System.out.println("\n🚨 Emergency Service Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", es.getId());
        System.out.printf("Name: %s%n", es.getName());
        System.out.printf("Type: %s%n", es.getType());
        System.out.printf("Area ID: %d%n", es.getAreaId());
        System.out.printf("Contact Number: %d%n", es.getContactNumber());
        System.out.printf("Available Vehicles: %d%n", es.getAvailableVehicles());
        System.out.println("--------------------------------------------------");
    }

    public static void printBus(Bus bus) {
        if (bus == null) {
            System.out.println("⚠️ Bus not found.");
            return;
        }

        System.out.println("\n🚌 Bus Details");
        System.out.println("--------------------------------------------------");
        System.out.printf("ID: %d%n", bus.getId());
        System.out.printf("License Plate: %s%n", bus.getLicensePlate());
        System.out.printf("Capacity: %d%n", bus.getCapacity());
        System.out.printf("Current Route ID: %s%n", bus.getCurrentRouteId() != null ? bus.getCurrentRouteId() : "—");
        System.out.printf("Current Area ID: %s%n", bus.getCurrentAreaID() != null ? bus.getCurrentAreaID() : "—");
        System.out.println("--------------------------------------------------");
    }
}
