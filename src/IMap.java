/**
 * Interfejs który inicuje metody dla klasy MapSimple.
 */
public interface IMap {
    public int GetSize();
    public boolean SettleCreature(ICreature creature, int[] pos);
    public int[] GetCreaturePos(ICreature creature);
    public ICreature GetCreatureByPos(int[] pos);
    public void KillCreature(ICreature creature);

    public int[] GetObjectPos(IObject object);
    boolean SettleObject(IObject object, int[] pos);

    public IObject GetObjectByPos(int[] pos);
    void DeleteObject(IObject object, int[] pos);

    String PrintMap(IMap map);
}
