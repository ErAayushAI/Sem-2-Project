package Display;

import java.util.List;

public class DisplayUtil {
    public static void printList(List< ? extends Displayable> items, String title) {
        //Check if it is null or not
        if (items == null || items.isEmpty()) {
            System.out.println("⚠️ No data found for " + title);
            return;
        }

        //Prints name what data is printed
        System.out.println("\n📋 " + title + " List");
        System.out.println("-------------------------------------------------------------");

        //Print headers based on title
        switch (title.toLowerCase()) {
            case "bus":
                System.out.printf("%-5s %-15s %-10s %-15s %-15s%n",
                        "ID", "License Plate", "Capacity", "Route ID", "Area ID");
                break;
            case "metro":
                System.out.printf("%-5s %-20s %-10s %-15s %-15s%n",
                        "ID", "Train Name", "Capacity", "Route ID", "Area ID");
                break;
            case "emergency":
                System.out.printf("%-5s %-30s %-15s %-10s %-15s %-20s%n",
                        "ID", "Name", "Type", "AreaID", "Contact", "Available Vehicles");
                break;
            case "area":
                System.out.printf("%-7s %-20s %-12s %-12s %-18s%n",
                        "ID", "Name", "Latitude", "Longitude", "Emergency Point");
                break;
            case "street":
                System.out.printf("%-5s %-12s %-12s %-13s %-10s%n",
                        "ID", "Start Area", "End Area", "Distance(km)", "One Way");
                break;
            case "parking":
                System.out.printf("%-5s %-30s %-10s %-10s %-15s %-10s%n",
                        "ID", "Name", "AreaID", "Capacity", "Occupied", "Status");
                break;
            case "route":
                System.out.printf("%-5s %-40s %-10s %-15s %-15s%n",
                        "ID", "Name", "Length(km)", "Bus Route", "Metro Route");
                break;
            case "schedule":
                System.out.printf("%-5s %-10s %-15s %-15s %-15s%n",
                        "ID", "Route ID", "Departure Time", "Bus Schedule", "Metro Schedule");
                break;
            case "station":
                System.out.printf("%-5s %-20s %-10s %-15s %-15s%n",
                        "ID", "Name", "Area ID", "Bus Station", "Metro Station");
                break;
            case "place":
                System.out.printf("%-5s %-30s %-25s %-10s %-20s%n",
                        "ID", "Name", "Category", "Area ID", "Ratings");
                break;
            case "complaint":
                String format = "| %-4s | %-20s | %-7s | %-9s | %-45s |%n";
                String separator = String.format("+%s+", "-".repeat(86));
                System.out.println(separator);
                System.out.format(format, "ID", "Department", "UserID", "Status", "Issue (Preview)");
                System.out.println(separator);
                System.out.println("+--------------------------------------------------------------------------------------+");
                System.out.println("🔍 Use printDetails(item) to see complete issue text.\n");
                System.out.println("--------------------------------------------------");

                break;
            case "feedback":
                System.out.printf("%-5s %-8s %-9s %-40s %-6s\n",
                        "ID", "UserID", "PlaceID", "Comments", "Rating");
        }

        for (Displayable item : items) {
            item.displaySummary();
        }

        System.out.println("-------------------------------------------------------------");
    }

    public static void printDetails(Displayable item, String title) {
        //Check if it is null or not
        if (item == null) {
            System.out.println("⚠️ " + title + " not found.");
            return;
        }

        item.displayDetails();
    }
}
