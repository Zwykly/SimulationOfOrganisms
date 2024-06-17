import java.util.List;
/**
 * Interfejs który inicuje metody dla klasy ObjectCreator.
 */
public interface IObjectCreator {
    List<IObject> CreateObjects(IMap map);
}
