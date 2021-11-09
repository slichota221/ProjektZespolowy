package pl.ug.Projekt.Zespolowy.domain;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name="game_table")
public class Game {

    private @Id @GeneratedValue @Column(name="id") long ID;

    private String nameGame;
    private String dateRelease;
    private String description;
    private String pathCover;

    @OneToOne(cascade = CascadeType.ALL) // może OneToMany?
    @JoinColumn(name="genre_id", referencedColumnName = "id")
    private Genre genre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publisher publisher;

    public Game() {

    }

    public Game(String nameGame) {
        this.nameGame = nameGame;
    }

    public Game(String nameGame, String dateRelease, String description, String pathCover, Genre genre, Publisher publisher) {
        this.nameGame = nameGame;
        this.dateRelease = dateRelease;
        this.description = description;
        this.pathCover = pathCover;
        this.genre = genre;
        this.publisher = publisher;
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
        this.pathCover = idCover;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setIdGenre(Genre genre) {
        this.genre = genre;
    }

    public Publisher getIdPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
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
