package pl.ug.Projekt.Zespolowy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Rating {
    private @Id @GeneratedValue long ID;
    @OneToOne
    private long ID_USER;
    @OneToOne
    private long ID_GAME;
    private int value;
    private String comment;
}
