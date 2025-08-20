package Model;

public class Session {
    private static int loggedInCustomerId = -1;

    public static void setCustomerId(int id) {
        loggedInCustomerId = id;
    }

    public static int getCustomerId() {
        return loggedInCustomerId;
    }

    public static void clear() {
        loggedInCustomerId = -1;
    }
}
