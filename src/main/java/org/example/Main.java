package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main
{
    public static void main(String[] args)
    {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
        EntityManager em = emf.createEntityManager();
        System.out.println("Factory y Entity Manager creados ... ");

        try
        {
            em.getTransaction().begin();

            

            em.getTransaction().commit();

        } catch (Exception e)
        {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo concretar la operación ... ");

        } finally
        {
            em.close();

        }


        emf.close();


    }
}
