import java.util.Map;

public interface ICreature {
    public void DecideAction();
    public boolean IsSameType(ICreature creature);
    public void Copulate(int[] pos);
    public void Die();
    public void Move();
    public boolean Move(int[] pos);
    public IMap GetMap();
    public void SetMap(IMap map);
}
