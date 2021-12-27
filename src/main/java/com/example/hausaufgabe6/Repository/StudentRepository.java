package com.example.hausaufgabe6.Repository;

import com.example.hausaufgabe6.Exceptions.NullException;
import com.example.hausaufgabe6.Model.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * Student com.company.Repository that extends an in-DataBase-com.company.Repository
 */
public class StudentRepository extends StudentJdbcRepository {
    public StudentRepository() throws SQLException {
        super();
    }

    /**
     * finds the entity with the give id from the repository
     *
     * @param id the id of the entity to be returned id must not be null
     * @return the entity with the specified id or null
     * @throws SQLException  if connection to database could not succeed
     * @throws NullException if input parameter id is NULL
     */
    @Override
    public Student findOne(Long id) throws SQLException, NullException {
        return super.findOne(id);
    }

    /**
     * retrieves all entities from the repository
     *
     * @return all entities
     * @throws SQLException if connection to database could not succeed
     */
    @Override
    public List<Student> findAll() throws SQLException {
        return super.findAll();
    }

    /**
     * adds an entity in the repository
     *
     * @param obj entity must be not null
     * @return null if the given entity is saved otherwise returns the entity
     * @throws SQLException  if connection to database could not succeed
     * @throws NullException if input parameter entity obj is NULL
     */
    @Override
    public Student save(Student obj) throws SQLException, NullException {
        return super.save(obj);
    }

    /**
     * updates an entity from the repository
     *
     * @param obj entity must not be null
     * @return null if the entity is updated, otherwise returns the entity
     * @throws SQLException  if connection to database could not succeed
     * @throws NullException if input parameter entity obj is NULL
     */
    @Override
    public Student update(Student obj) throws SQLException, NullException {
        return super.update(obj);
    }

    /**
     * removes the entity with the specified id from the repository
     *
     * @param id id must be not null
     * @return the removed entity or null if there is no entity with the given id
     * @throws SQLException  if connection to database could not succeed
     * @throws NullException if input parameter id is NULL
     */
    @Override
    public Student delete(Long id) throws SQLException, NullException {
        return super.delete(id);
    }
}
