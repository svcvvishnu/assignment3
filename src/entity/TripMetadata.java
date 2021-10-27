package entity;

public class TripMetadata {
    public int costImportance;
    public int travelTimeImportance;
    public int travelHopImportance;
    boolean isVaccinated;

    public TripMetadata(int costImportance, int travelTimeImportance, int travelHopImportance, boolean isVaccinated) {
        this.costImportance = costImportance;
        this.travelHopImportance = travelHopImportance;
        this.travelTimeImportance = travelTimeImportance;
        this.isVaccinated = isVaccinated;
    }
}
