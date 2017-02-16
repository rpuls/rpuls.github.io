package jpqldemo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {

        System.out.println("1. Find all Students in the system");
        TypedQuery<Student> allStudentsQuery = em.createNamedQuery("Student.findAll", Student.class);
        List<Student> allStudents = allStudentsQuery.getResultList();
        for (Student s : allStudents) {
            System.out.println(s.getFirstname() + " " + s.getLastname());
        }

        System.out.println("2. Find all Students in the System with the first Name jan");
        TypedQuery<Student> allJansQuery = em.createNamedQuery("Student.findByFirstname", Student.class);
        allJansQuery.setParameter("firstname", "jan");
        List<Student> allJans = allJansQuery.getResultList();
        for (Student s : allJans) {
            System.out.println(s.getFirstname() + " " + s.getLastname());
        }

        System.out.println("3. Find all Students in the system with the last name Olsen");
        TypedQuery<Student> allOlsensQuery = em.createNamedQuery("Student.findByLastname", Student.class);
        allOlsensQuery.setParameter("lastname", "Olsen");
        List<Student> allOlsens = allOlsensQuery.getResultList();
        for (Student s : allOlsens) {
            System.out.println(s.getFirstname() + " " + s.getLastname());
        }

        System.out.println("4. Find the total sum of study point scores, for a student given the student id.");
        Integer id = 1;
        TypedQuery<Integer> pointsQuery = em.createQuery("SELECT sum(sp.score) FROM Studypoint sp WHERE sp.student.id = :id GROUP BY sp.student.id", Integer.class);
        pointsQuery.setParameter("id", id);
        List<Integer> points = pointsQuery.getResultList();
        System.out.println("Total points for student: " + points.get(0));

        System.out.println("5. Find the total sum of studypoint scores, given to all students.");
        TypedQuery<Integer> totalPointsQuery = em.createQuery("SELECT sum(sp.score) FROM Studypoint sp", Integer.class);
        List<Integer> totalPoints = totalPointsQuery.getResultList();
        System.out.println("Total points for all students: " + totalPoints.get(0));
        
        System.out.println("6. Find those students that has the greatest total of studypoint scores");
        System.out.println("7. Find those students that has the lowest total of studypoint scores");

        System.out.println("D) Create the following methods to insert new data into the system");
        System.out.println("1. Create a method to create new Students and test the method");
        System.out.println("2. Add a method to the Student class addStudyPoint(..) and test the method");
    }
}
