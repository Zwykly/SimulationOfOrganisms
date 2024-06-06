import java.util.LinkedList;
import java.util.List;

public class ObjectCreator implements IObjectCreator {
    protected int numRenewableFood;
    protected int numOneUseFood;
    protected int numBlankSpace;

    public ObjectCreator()
    {
        this.numRenewableFood = 1;
        this.numOneUseFood = 2;
        this.numBlankSpace = 5;
    }

    public ObjectCreator (int numRenewableFood, int numOneUseFood, int numBlankSpace)
    {
        this.numRenewableFood = numRenewableFood;
        this.numOneUseFood = numOneUseFood;
        this.numBlankSpace = numBlankSpace;
    }

    @Override
    public List<IObject> CreateObjects(IMap map)
    {
        List<IObject> objectList = new LinkedList<>();
        for(int i = 0; i < numBlankSpace; i++)
        {
                objectList.add(new Blankspace(map));
        }
        return objectList;
    }
}
