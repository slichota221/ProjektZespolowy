package pl.ug.Projekt.Zespolowy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rating {
    private @Id @GeneratedValue long ID;
    private long ID_USER;
    private long ID_GAME;
    private int value;
    private String comment;
}
