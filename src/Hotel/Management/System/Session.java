package Hotel.Management.System;

public class Session {
    private static boolean isAdmin = false;
    private static boolean calledFromUpdateRoom = false;

    public static void setAdmin(boolean status) {
        isAdmin = status;
    }

    public static boolean isAdmin() {
        return isAdmin;
    }

    public static void setCalledFromUpdateRoom(boolean status) {
        calledFromUpdateRoom = status;
    }

    public static boolean isCalledFromUpdateRoom() {
        return calledFromUpdateRoom;
    }
}