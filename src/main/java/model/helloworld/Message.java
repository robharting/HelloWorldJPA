package model.helloworld;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by robendiane on 27-01-18.
 */
@Entity
public class Message {

    @Id

    @GeneratedValue
    private Long id;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
