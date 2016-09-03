package bar.utils;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bar.dao.BookDAO;
import bar.dao.UserDAO;
import bar.model.Item;
import bar.model.User;

@Stateless
public class DatabaseUtils {
    
    private static User[] USERS = {
            new User("First User", "123456", "first.user@somemail.com",
                    new Date()),
            new User("Second User", "Test1234", "second.user@somemail.com",
                    new Date()),
            new User("Third User", "98411TA", "third.user@somemail.com",
                    new Date())};

    private static Item[] BOOKS = {
            new Item("The Old Man and the Sea", "Ernest Hemingway",
                    "978-3-16-148410-0", 5),
            new Item("Tom Sawyer", "Mark Twain", "978-4-16-241512-0", 0)};

    @PersistenceContext
    private EntityManager em;

    @EJB
    private BookDAO bookDAO;
    
    @EJB
    private UserDAO userDAO;
    
    public void addTestDataToDB() {
       
        addTestUsers();
        addTestBooks();
    }

    private void deleteData() {
        em.createQuery("DELETE FROM Book").executeUpdate();
        em.createQuery("DELETE FROM User").executeUpdate();
   }

    private void addTestUsers() {
        for (User user : USERS) {
            userDAO.addUser(user);
        }
    }

    private void addTestBooks() {
        for (Item book : BOOKS) {
            bookDAO.addBook(book);
        }
    }
}