public interface IMap {
    public int GetSize();
    public boolean SettleCreature(ICreature creature, int[] pos);
    public int[] GetCreaturePos(ICreature creature);
    public ICreature GetCreatureByPos(int[] pos);
    public void KillCreature(ICreature creature);
    public int[] GetObjectPos(IObject object);
    void PrintMap(IMap map);
}
