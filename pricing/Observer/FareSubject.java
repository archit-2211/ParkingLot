package pricing.Observer;

public interface FareSubject {
    void registerObserver(FareObserver observer);
    void removeObserver(FareObserver observer);
}
