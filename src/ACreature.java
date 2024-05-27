public abstract class ACreature implements ICreature {
    protected IMap map;
    public ACreature(IMap map) {
        this.map = map;
    }
    @Override
    public IMap GetMap() {return map;}
    @Override
    public void SetMap(IMap map) {
        this.map = map;
    }
}
