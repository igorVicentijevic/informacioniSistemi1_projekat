package entities;

import entities.Korisnik;
import entities.Snimak;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-08-09T19:20:08")
@StaticMetamodel(Ocena.class)
public class Ocena_ { 

    public static volatile SingularAttribute<Ocena, Integer> idOce;
    public static volatile SingularAttribute<Ocena, Date> datumVremeDavanjaOcena;
    public static volatile SingularAttribute<Ocena, Snimak> idSni;
    public static volatile SingularAttribute<Ocena, Integer> ocena;
    public static volatile SingularAttribute<Ocena, Korisnik> korisnik;

}