package pl.ug.Projekt.Zespolowy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Publisher {

    private @Id @GeneratedValue @Column(name="id") long ID;
    private String name;

    public Publisher(){}

    public Publisher(String name){
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

    @Override
    public String toString(){
        return "Publisher{id="+ID + ", name='"+name+"'}";
    }
}
