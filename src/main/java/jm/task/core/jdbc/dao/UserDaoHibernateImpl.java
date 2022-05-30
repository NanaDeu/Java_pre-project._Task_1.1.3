package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                    " name VARCHAR(40), lastName VARCHAR(40), age INT);";

            Query query = session.createNativeQuery(sql);
            query.executeUpdate();

            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS user";

            Query query = session.createNativeQuery(sql);
            query.executeUpdate();

            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            User newUser = new User(name, lastName, age);
            String sql = "INSERT INTO user(name, lastName, age) VALUES('" + newUser.getName() + "'" +
                    ",'" + newUser.getLastName() + "'" + "," + newUser.getAge() + ");";

            Query query = session.createNativeQuery(sql);
            query.executeUpdate();

            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "DELETE FROM user WHERE id = " + id + ";";

            Query query = session.createNativeQuery(sql);
            query.executeUpdate();

            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            List<User> users = (List<User>) session.createQuery("From User").list();

            System.out.println(users.toString());
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "DELETE FROM user;";

            Query query = session.createNativeQuery(sql);
            query.executeUpdate();

            transaction.commit();
        }
    }
}
