import java.util.LinkedList;
import java.util.List;

/**
 * Klasa dla  kreatora obiektów IObject.
 */
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

    /**
     * Konstruktor który tworzy obiekt kreatora z danymi podanym w arugmentach.
     * @param numRenewableFood ilość odnawialnego jedzenia.
     * @param numOneUseFood ilość pojedyńczego jedzenia.
     * @param numBlankSpace Ilość pustych miejsc.
     */
    public ObjectCreator (int numRenewableFood, int numOneUseFood, int numBlankSpace)
    {
        this.numRenewableFood = numRenewableFood;
        this.numOneUseFood = numOneUseFood;
        this.numBlankSpace = numBlankSpace;
    }

    /**
     * Metoda ta ma za zadanie utworzyć listę obiektów, zapełnienić ją konkretnymi obiektami oraz ją zwrócić.
     * @param map mapa do której są przypisywane obiekty.
     * @return Lista obiektów.
     */
    @Override
    public List<IObject> CreateObjects(IMap map)
    {
        List<IObject> objectList = new LinkedList<>();
        for(int i = 0; i < numBlankSpace; i++)
        {
                objectList.add(new Blankspace(map));
        }

        for(int i = 0; i < numOneUseFood; i++)
        {
            objectList.add(new OneUseFood(map));
        }

        for(int i = 0; i < numRenewableFood; i++)
        {
            objectList.add(new RenewableFood(map));
        }

        return objectList;
    }
}
