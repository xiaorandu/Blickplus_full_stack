package com.project.onlineOrder.dao;

import com.project.onlineOrder.entity.Authority;
import com.project.onlineOrder.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository //annotate dao object
public class CustomerDao {
    // The main runtime interface between a Java application and Hibernate. This is the central API class abstracting the notion of a persistence service. The main function of the Session is to offer create, read and delete operations for instances of mapped entity classes. Instances may exist in one of three states:
    private SessionFactory sessionFactory;
//    The main contract here is the creation of Session instances. Usually an application has a single SessionFactory instance and threads servicing client requests obtain Session instances from this factory

    @Autowired
    public CustomerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void signUp(Customer customer) {
        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        authority.setEmail(customer.getEmail());

        Session session = null;
        try {
            session = sessionFactory.openSession(); //get a session object
            session.beginTransaction(); //atomicity, all or nothing
            session.save(authority);
            session.save(customer); //customer object, no need to add cart here~ @OnetoOne(cascade = CascadeType All)
            session.getTransaction().commit(); //execute

        }
        catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) session.getTransaction().rollback();
        }
        finally {
            if (session != null) {
                session.close(); //release the database
            } //to check if get a session obect from openSession()
        }

    }

    public Customer getCustomer(String email) {
        Customer customer = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            customer = session.get(Customer.class, email);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return customer;
        //try-with-resources (autoclosable)
//        try (Session session = sessionFactory.openSession()) {
//            customer = session.get(Customer.class, email);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return new Customer();
    }
}
