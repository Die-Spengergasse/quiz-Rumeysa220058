package at.spengergasse.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "a_answers") // Tabelle in der Datenbank, die diese Entität repräsentiert
public class Answer {

    @Id
    @Column(name = "a_id") // Der Primärschlüssel, der die Antwort eindeutig identifiziert
    private int id;

    @Column(name = "a_text") // Der Text der Antwort
    private String text;

    @Column(name = "a_correct") // Gibt an, ob die Antwort korrekt ist
    private boolean correct;

    // Eine Antwort gehört zu einer Frage, daher die Many-to-One-Beziehung
    // @ManyToOne bedeutet, dass viele Antworten zu einer Frage gehören
    @ManyToOne
    @JoinColumn(name = "fk_q_id") // Der Fremdschlüssel, der auf die Frage verweist
    private Question question; // Verweis auf die zugehörige Frage

    // Getter für den Text der Antwort
    public String getText() {
        return text;
    }

    // Getter, um zu prüfen, ob die Antwort korrekt ist
    public boolean isCorrect() {

        return correct;
    }

    // Getter für die Frage, zu der diese Antwort gehört
    public Question getQuestion() {
        return question;
    }

    // Setter für die Frage, zu der diese Antwort gehört
    public void setQuestion(Question question) {
        this.question = question;
    }



}



