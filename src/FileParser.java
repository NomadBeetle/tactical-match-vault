import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    public static List<Match> loadMatches(String filePath) {
        List<Match> matchHistory = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 6) {
                    String id = data[0];
                    String map = data[1];
                    int kills = Integer.parseInt(data[2]);
                    int deaths = Integer.parseInt(data[3]);
                    int assists = Integer.parseInt(data[4]);
                    boolean isWin = Boolean.parseBoolean(data[5]);

                    matchHistory.add(
                        new Match(id, map, kills, deaths, assists, isWin)
                    );
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file.");
        }

        return matchHistory;
    }
}