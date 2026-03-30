import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseManager {

    // Points to a local SQLite database file in your data folder
    private static final String URL = "jdbc:sqlite:data/match_vault.db";

    // Sets up the database table if it doesn't exist yet
    public static void initializeDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS matches ("
                + "match_id TEXT PRIMARY KEY,"
                + "map_name TEXT,"
                + "kills INTEGER,"
                + "deaths INTEGER,"
                + "assists INTEGER,"
                + "is_win BOOLEAN,"
                + "kd_ratio REAL"
                + ");";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            System.out.println("Error setting up database: " + e.getMessage());
        }
    }

    // CONCURRENCY & JDBC COMBINED: Saves matches on a separate background thread
    public static void backupMatchesAsync(List<Match> matches) {
        Thread backupThread = new Thread(() -> {
            String insertSQL = "INSERT OR IGNORE INTO matches (match_id, map_name, kills, deaths, assists, is_win, kd_ratio) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(URL);
                 PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

                int count = 0;
                for (Match match : matches) {
                    pstmt.setString(1, match.getMatchId());
                    pstmt.setString(2, match.getMapName());
                    pstmt.setInt(3, match.getKills());
                    pstmt.setInt(4, match.getDeaths());
                    pstmt.setInt(5, match.getAssists());
                    pstmt.setBoolean(6, match.isWin());
                    pstmt.setDouble(7, match.getKdRatio());
                    
                    pstmt.executeUpdate();
                    count++;
                }
                System.out.println("\n[Async Thread] Successfully backed up " + count + " matches to the database!");

            } catch (SQLException e) {
                System.out.println("[Async Thread] Database Error: " + e.getMessage());
            }
        });

        // Start the background thread
        backupThread.start();
    }
}