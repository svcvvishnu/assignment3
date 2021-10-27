package entity;

public class TripMetadata {
    public int costImportance;
    public int travelImportance;
    public int travelHopImportance;
    boolean isVaccinated;

    public TripMetadata(int costImportance, int travelImportance, int travelHopImportance, boolean isVaccinated) {
        this.costImportance = costImportance;
        this.travelHopImportance = travelHopImportance;
        this.travelImportance = travelImportance;
        this.isVaccinated = isVaccinated;
    }
}
