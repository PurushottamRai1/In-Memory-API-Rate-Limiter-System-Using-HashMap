import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class ClientUsageTracker {
    private int currentMonthHits;
    private final int maxMonthlyLimit;

    public ClientUsageTracker(int currentMonthHits, int maxLimit) {
        this.currentMonthHits = currentMonthHits;
        this.maxMonthlyLimit = maxLimit;
    }

    public boolean hasExceededLimit() {
        return this.currentMonthHits >= this.maxMonthlyLimit;
    }

    public void incrementHit() {
        this.currentMonthHits++;
    }

    public int getCurrentMonthHits() { return currentMonthHits; }
    public int getMaxMonthlyLimit() { return maxMonthlyLimit; }
    public int getRemainingHits() { return maxMonthlyLimit - currentMonthHits; }
}

public class Main {

    
    private static final String RESET   = "\u001B[0m";
    private static final String RED     = "\u001B[31m";
    private static final String GREEN   = "\u001B[32m";
    private static final String YELLOW  = "\u001B[33m";
    private static final String CYAN    = "\u001B[36m";
    private static final String BOLD    = "\u001B[1m";

    private static final Map<String, ClientUsageTracker> registry = new HashMap<>();

    static {
        registry.put("DEV-ALPHA-101",   new ClientUsageTracker(0, 5));
        registry.put("DEV-BRAVO-202",   new ClientUsageTracker(2, 5));
        registry.put("DEV-CHARLIE-303", new ClientUsageTracker(4, 5));
        registry.put("DEV-DELTA-404",   new ClientUsageTracker(5, 5));
        registry.put("CORP-NIKE-99",     new ClientUsageTracker(1, 5));
        registry.put("CORP-APPLE-88",    new ClientUsageTracker(3, 5));
        registry.put("TEST-KEY-001",     new ClientUsageTracker(0, 5));
        registry.put("TEST-KEY-002",     new ClientUsageTracker(4, 5));
        registry.put("GUEST-ACCESS",     new ClientUsageTracker(2, 5));
        registry.put("ADMIN-BYPASS",     new ClientUsageTracker(5, 5));
    }

    public static void processApiCall(String apiKey) {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            System.out.println(RED + "ERROR: API Key cannot be blank." + RESET);
            return;
        }

        
        ClientUsageTracker usage = registry.get(apiKey);

        
        if (usage == null) {
            System.out.println(RED + "STATUS 401 (Unauthorized): Invalid API Key. Access Denied!" + RESET);
            return;
        }

        
        if (usage.hasExceededLimit()) {
            System.out.printf(RED + "STATUS 429 (Blocked): Access Denied! Key [%s] exhausted limit of %d.%n" + RESET,
                    apiKey, usage.getMaxMonthlyLimit());
            return;
        }

        
        usage.incrementHit();

        System.out.println(GREEN + "STATUS 200 (Success): Access Granted!" + RESET);
        System.out.println("---------------------------------------------");
        System.out.println("DATA RECEIVED:");
        System.out.println("Hello! Your API request was successfully processed.");
        System.out.println("Sample Data: Welcome to the protected API system!");
        System.out.println("---------------------------------------------");
        System.out.printf("Current Usage : %d/%d%n", usage.getCurrentMonthHits(), usage.getMaxMonthlyLimit());
        System.out.printf("Remaining Hits: %d%n", usage.getRemainingHits());
    }

    public static void printRegistrySnapshot() {
        System.out.println(CYAN + "\n=================== AUTHORIZED API KEYS ===================" + RESET);
        System.out.printf(BOLD + "%-20s | %-12s | %-12s | %-10s%n" + RESET,
                "API Key", "Current Hits", "Max Limit", "Status");
        System.out.println("-----------------------------------------------------------");

        registry.forEach((key, data) -> {
            boolean blocked = data.hasExceededLimit();
            String status = blocked ? RED + "BLOCKED" + RESET : GREEN + "ACTIVE " + RESET;

            System.out.printf("%-20s | %-12d | %-12d | %s%n",
                    key,
                    data.getCurrentMonthHits(),
                    data.getMaxMonthlyLimit(),
                    status
            );
        });
        System.out.println(CYAN + "===========================================================\n" + RESET);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(CYAN + BOLD + "==============================================" + RESET);
        System.out.println(CYAN + BOLD + "    IN-MEMORY API ACCESS CONTROL SYSTEM       " + RESET);
        System.out.println(CYAN + BOLD + "==============================================" + RESET);

        printRegistrySnapshot();

        System.out.println("Commands:");
        System.out.println(" - Enter API Key to make a request (e.g., 'DEV-ALPHA-101')");
        System.out.println(" - Type 'print' to show snapshot table");
        System.out.println(" - Type 'exit' to terminate program");

        while (true) {
            System.out.print("\nEnter API Key: ");

            if (!scanner.hasNextLine()) break;

            String inputKey = scanner.nextLine().trim();

            if (inputKey.equalsIgnoreCase("exit")) break;

            if (inputKey.equalsIgnoreCase("print")) {
                printRegistrySnapshot();
                continue;
            }

            processApiCall(inputKey);
        }

        scanner.close();
        System.out.println(YELLOW + "\nProcess terminated gracefully." + RESET);
    }
}
