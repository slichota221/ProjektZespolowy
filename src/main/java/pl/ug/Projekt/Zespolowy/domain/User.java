package pl.ug.Projekt.Zespolowy.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {
    private @Id @GeneratedValue @Column(name="id") long ID;
    private String userName;
    //z hasłem brak pewności czy jest dobrze
    private String password;
    //być może do emaila warto stworzyć własną klasę
    private String email;
    private Date dateRegister;
    private Role role;


}

