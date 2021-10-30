package pl.ug.Projekt.Zespolowy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;


@Entity
public class Game {

    private @Id @GeneratedValue long ID;
    private String nameGame;
    private String dateRelease;
    private String description;
    private String pathCover;
    private int IdGenre;
    private int IdPublisher;

    public Game() {

    }

    public Game(String nameGame) {
        this.nameGame = nameGame;
    }

    public Game(String nameGame, String dateRelease, String description, String pathCover, int idGenre, int idPublisher) {
        this.nameGame = nameGame;
        this.dateRelease = dateRelease;
        this.description = description;
        this.pathCover = pathCover;
        IdGenre = idGenre;
        IdPublisher = idPublisher;
    }

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

    public String getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(String dateRelease) {
        this.dateRelease = dateRelease;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathCover() {
        return pathCover;
    }

    public void setPathCover(String idCover) {
        pathCover = idCover;
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

    @Override
    public int hashCode() {
        return Objects.hash(this.ID);
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + this.ID + ", name='" + this.nameGame + '}';
    }
}
