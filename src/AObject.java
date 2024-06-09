public abstract class AObject implements IObject{
    protected IMap map;
    public AObject(IMap map) { this.map = map; }

    @Override
    public void SetMap(IMap map) {
        this.map = map;
    }
}
