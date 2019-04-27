package rockets.dataaccess;

import org.neo4j.ogm.cypher.Filters;
import rockets.model.Entity;

import java.util.Collection;

public interface DAO {
    <T extends Entity> T load(Class<T> clazz, Long id);

    <T extends Entity> T createOrUpdate(T entity);

    <T extends Entity> Collection<T> loadAll(Class<T> clazz);

    <T extends Entity> void delete(T entity);

    <T extends Entity> Collection<T> loadWithFilter(Class<T> clazz, Filters filters);
}
