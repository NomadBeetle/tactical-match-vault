public class Match {
    private String matchId;
    private String mapName; // e.g., Ascent, Mirage, Bind
    private int kills;
    private int deaths;
    private int assists;
    private boolean isWin;

    // Constructor
    public Match(String matchId, String mapName, int kills, int deaths, int assists, boolean isWin) {
        this.matchId = matchId;
        this.mapName = mapName;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.isWin = isWin;
    }

    // Business Logic: Calculate K/D Ratio
    public double getKdRatio() {
        if (deaths == 0) return (double) kills; // Prevent division by zero
        return (double) kills / deaths;
    }

    // Getters
    public String getMatchId() { return matchId; }
    public String getMapName() { return mapName; }
    public int getKills() { return kills; }
    public int getDeaths() { return deaths; }
    public int getAssists() { return assists; }
    public boolean isWin() { return isWin; }

    // Override toString for easy terminal printing later
    @Override
    public String toString() {
        String result = isWin ? "VICTORY" : "DEFEAT";
        return String.format("Map: %-10s | %s | K/D/A: %d/%d/%d | KD Ratio: %.2f", 
                mapName, result, kills, deaths, assists, getKdRatio());
    }
}