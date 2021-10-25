package pl.ug.Projekt.Zespolowy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class Game {


    private @Id @GeneratedValue long ID;
    private String nameGame;
    private Date dateRelase;
    private String description;
    private int IdCover;
    private int IdGenre;
    private int IdPublisher;


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    public Date getDateRelase() {
        return dateRelase;
    }

    public void setDateRelase(Date dateRelase) {
        this.dateRelase = dateRelase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdCover() {
        return IdCover;
    }

    public void setIdCover(int idCover) {
        IdCover = idCover;
    }

    public int getIdGenre() {
        return IdGenre;
    }

    public void setIdGenre(int idGenre) {
        IdGenre = idGenre;
    }

    public int getIdPublisher() {
        return IdPublisher;
    }

    public void setIdPublisher(int idPublisher) {
        IdPublisher = idPublisher;
    }
}
