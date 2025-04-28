package at.spengergasse.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "q_questions") // Tabelle in der Datenbank, die diese Entität repräsentiert
public class Question {
    @Id
    @Column(name = "q_id") // Der Primärschlüssel, der die Frage eindeutig identifiziert
    private int id;

    @Column(name = "q_text") // Die Frage selbst (Text der Frage)
    private String text;

    // Eine Frage kann viele Antworten haben, daher eine Liste von Antworten
    // 'mappedBy' sagt uns, dass die Antwort die Beziehung verwaltet (in der 'Answer'-Entität)
    @OneToMany(mappedBy = "question") // 'question' ist das Attribut in der 'Answer'-Entität, das auf diese Klasse verweist
    private List<Answer> answerList = new ArrayList<>(); // Die Liste der Antworten zu dieser Frage

    // Getter für den Text der Frage
    public String getText() {
        return text;
    }

    // Setter für den Text der Frage
    public void setText(String text) {
        this.text = text;
    }

    // Getter für die Liste der Antworten
    public List<Answer> getAnswerList() {
        return answerList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", answerList=" + answerList + // Gibt die Antworten zu dieser Frage aus
                '}';
    }
}



