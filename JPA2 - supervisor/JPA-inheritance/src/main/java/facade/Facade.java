/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Employee;
import entity.Person;
import entity.Student;
import java.util.Collection;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rasmus
 */
public class Facade {
    
    private EntityManagerFactory emf;
    private PersonJpaController persCtrl;
    private EmployeeJpaController empCtrl;
    private StudentJpaController studCtrl;
    
    
    public Facade(EntityManagerFactory emf){
        this.emf = emf;
        this.persCtrl = new PersonJpaController(emf);
        this.empCtrl = new EmployeeJpaController(emf);
        this.studCtrl = new StudentJpaController(emf);
    }
    
    public void createStuden(Student stud){
        studCtrl.create(stud);
    }
    
    public void createEmployee(Employee emp){
        empCtrl.create(emp);
    }
    
    public Collection<Person> getAllPersons(){
        return persCtrl.findPersonEntities();
    }
   
}
