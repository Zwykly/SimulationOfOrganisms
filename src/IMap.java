public interface IMap {
    public int GetSize();

    public boolean SettleCreature(ICreature creature, int[] pos);
    public int[] GetCreaturePos(ICreature creature);

    //Methods to be completed and add methods for Objects
    public int[] GetObjectPos(IObject object);
    boolean SettleObject(IObject object, int[] pos);

    void DeleteObject(IObject object, int[] pos);

    void PrintMap(IMap map);
}
