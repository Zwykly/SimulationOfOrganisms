public interface IMap {
    public ICreature GetCreature(int pos);
    public boolean SettleCreature(ICreature creature, int[] pos);
    public int[] GetCreaturePos(ICreature creature);
    public IObject GetObject(int pos);
    public int[] GetObjectPos(IObject object);

    void PrintMap(IMap map);
}
