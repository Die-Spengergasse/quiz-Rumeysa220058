package at.spengergasse.entities;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;


public class Quiz {
    private List<Question> questions; // Liste aller Fragen
    private int correctAnswers = 0; // Zähler für richtige Antworten
    private EntityManager em; // EntityManager zur Verwaltung der Datenbankverbindung

    public Quiz() {
        em = Persistence.createEntityManagerFactory("quiz").createEntityManager();
        loadQuestions(); // Fragen aus der Datenbank laden
    }

    // Lädt alle Fragen aus der Datenbank in die Liste
    private void loadQuestions() {
        TypedQuery<Question> query = em.createQuery("SELECT q FROM Question q", Question.class);
        questions = query.getResultList();
    }

    // Startet das Quiz und verarbeitet die Benutzerantworten
    public void start() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println(question.getText()); // Frage ausgeben
            List<Answer> answers = question.getAnswerList();

            // Antwortmöglichkeiten anzeigen
            for (int i = 0; i < answers.size(); i++) {
                System.out.println((i + 1) + ". " + answers.get(i).getText());
            }

            // Benutzer zur Eingabe auffordern
            System.out.print("Deine Antwort (Nummer eingeben): ");
            int userChoice = scanner.nextInt() - 1;

            // Antwort überprüfen
            if (userChoice >= 0 && userChoice < answers.size() && answers.get(userChoice).isCorrect()) {
                System.out.println("Richtig!");
                correctAnswers++;
            } else {
                System.out.println("Falsch! Die richtige Antwort wäre: " + getCorrectAnswerText(answers));
            }
        }

        showResults(); // Ergebnis anzeigen
        scanner.close();
        em.close(); // Verbindung schließen
    }

    // Gibt die richtige Antwort als String zurück
    private String getCorrectAnswerText(List<Answer> answers) {
        return answers.stream().filter(Answer::isCorrect).map(Answer::getText).findFirst().orElse("Keine richtige Antwort gefunden");
    }

    // Zeigt das Endergebnis an
    private void showResults() {
        double percentage = ((double) correctAnswers / questions.size()) * 100;
        System.out.println("Du hast " + correctAnswers + " von " + questions.size() + " Fragen richtig beantwortet.");
        System.out.println("Ergebnis: " + String.format("%.2f", percentage) + "%");
    }


}
