import java.util.List;
/**
 * Interfejs który inicuje metody dla klasy CreatureCreator.
 */
public interface ICreatureCreator {
    List<ICreature> CreateCreatures(IMap map);
}
