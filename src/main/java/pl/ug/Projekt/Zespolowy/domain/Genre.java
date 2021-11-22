package pl.ug.Projekt.Zespolowy.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genre_table")
public class Genre {
    private @Id @GeneratedValue @Column(name="id") long ID;
    private String name;

    public Genre() {}

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

    public Genre(String name) {
        this.name = name;
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.ID);
    }

    @Override
    public String toString() {
        return "Genre{" + "id=" + this.ID + ", name='" + this.name + '}';
    }
}
