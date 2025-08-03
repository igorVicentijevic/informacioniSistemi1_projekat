package entities;

import entities.Korisnik;
import entities.Snimak;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-08-03T17:47:32")
@StaticMetamodel(Slusa.class)
public class Slusa_ { 

    public static volatile SingularAttribute<Slusa, Date> datumVremePocetkaSlusanja;
    public static volatile SingularAttribute<Slusa, Snimak> idSni;
    public static volatile SingularAttribute<Slusa, Integer> idSlu;
    public static volatile SingularAttribute<Slusa, Integer> odslusanoUSekundama;
    public static volatile SingularAttribute<Slusa, Integer> sekundaOdKojeJePoceloSlusanje;
    public static volatile SingularAttribute<Slusa, Korisnik> korisnik;

}