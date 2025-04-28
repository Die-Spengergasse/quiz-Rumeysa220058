package at.spengergasse;

import at.spengergasse.entities.Question;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // EntityManager erstellen
        EntityManager em = Persistence.createEntityManagerFactory("quiz")
                .createEntityManager();

        // Frage mit ID 1 finden
        Question q = em.find(Question.class, 1);

        // Alle Fragen abfragen
        TypedQuery<Question> query = em.createQuery("select q from Question q", Question.class);
        List<Question> questions = query.getResultList();

        // Ausgabe der gefundenen Frage
        System.out.println(q);

        // Schließen des EntityManagers
        em.close();
    }
}
