package pl.ug.Projekt.Zespolowy.domain;

import javax.persistence.*;

@Entity
public class Rating {
    private @Id @GeneratedValue long ID;
    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
    @OneToOne
    @JoinColumn(name="game_id", referencedColumnName = "id")
    private Game game;
    private int value;
    private String comment;
}
