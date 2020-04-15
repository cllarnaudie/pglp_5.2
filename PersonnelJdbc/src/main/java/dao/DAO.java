package main.java.dao;
import java.sql.Connection;
//import com.developpez.jdbc.ConnectionPostDerby;

/**
 * Interface DAO pour les operations CRUD ( create, read, update, delete)
 * @author claire
 *
 * @param <T>
 */
public abstract class DAO<T> {
	
	
	public Connection connect = main.java.ConnectionPostDerby.getInstance();
	
	/**
	 * create 
	 * @param obj
	 * @return
	 */
	public abstract T create(T obj);

	
	/**
	 * find
	 * @param id
	 * @return
	 */	 
	public abstract T find(String id);

	/**
	 * update
	 * @param obj
	 * @return
	 */
	public abstract T update(T obj);

	/**
	 * delete
	 * @param obj
	 */
	public abstract void delete(T obj); 
	
}

