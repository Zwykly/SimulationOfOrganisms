import java.util.Map;
/**
 * Interfejs który inicuje metody dla klasy abstarkcyjnej ACreature.
 */
public interface ICreature {
    /**
     *
     */
    public void DecideAction();
    public ICreature NearestAlly();
    public void Die();
    public void Move();
    public boolean Move(int[] pos);
    public IMap GetMap();
    public void SetMap(IMap map);
}
