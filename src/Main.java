import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "matches.csv";

        System.out.println("=====================================");
        System.out.println("  INITIATING TACTICAL MATCH VAULT... ");
        System.out.println("=====================================\n");

        DatabaseManager.initializeDatabase();

        System.out.println("Loading match history from local vault...");
        List<Match> matchHistory = FileParser.loadMatches(filePath);
        System.out.println("Loaded " + matchHistory.size() + " matches successfully.\n");

        boolean running = true;

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. View All Matches");
            System.out.println("2. View Lifetime Stats");
            System.out.println("3. Backup Data to SQL Database");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    for (Match m : matchHistory) {
                        System.out.println(m);
                    }
                    break;
                case "2":
                    calculateStats(matchHistory);
                    break;
                case "3":
                    DatabaseManager.backupMatchesAsync(matchHistory);
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    private static void calculateStats(List<Match> matches) {
        int wins = 0;
        int kills = 0;
        int deaths = 0;

        for (Match m : matches) {
            if (m.isWin()) wins++;
            kills += m.getKills();
            deaths += m.getDeaths();
        }

        double winRate = ((double) wins / matches.size()) * 100;
        double kd = deaths == 0 ? kills : (double) kills / deaths;

        System.out.printf("Win Rate: %.1f%%\n", winRate);
        System.out.printf("Overall KD: %.2f\n", kd);
    }
}