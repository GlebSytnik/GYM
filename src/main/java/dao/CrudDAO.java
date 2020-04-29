package dao;

import dao.exception.DAOException;

import java.sql.SQLException;

public interface CrudDAO<T,PK> {
    /**
     * Add entity to the database.
     *
     * @param object
     * This is entity.
     *
     * @return ID created entity.
     * @throws DAOException
     */
    PK create(T object);

    /**
     * Read entity to the database by ID.
     *
     * @param key
     * ID entity.
     *
     * @return Entity.
     * @throws DAOException
     */
    T read(PK key);

    /**
     * Update entity to the database.
     *
     * @param object
     * This is entity.
     *
     * @throws DAOException
     */
    void update(T object);

    /**
     * Deletes entity from the database.
     *
     * @param key
     * This is ID entity.
     *
     * @throws DAOException
     */
    void delete(PK key);


}
