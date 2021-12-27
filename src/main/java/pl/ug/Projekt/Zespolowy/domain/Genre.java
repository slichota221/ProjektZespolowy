package pl.ug.Projekt.Zespolowy.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genre_table")
public class Genre {
    private @Id @GeneratedValue @Column(name="id") long ID;
    private String name;
    private String pathCover;

    public Genre() {}
    public Genre(String name) {
        this.name = name;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathCover() {
        return pathCover;
    }

    public void setPathCover(String pathCover) {
        this.pathCover = pathCover;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ID);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", pathCover='" + pathCover + '\'' +
                '}';
    }
}
