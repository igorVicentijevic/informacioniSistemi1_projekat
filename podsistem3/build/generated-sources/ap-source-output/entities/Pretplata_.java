package entities;

import entities.Korisnik;
import entities.Paket;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-08-03T19:15:05")
@StaticMetamodel(Pretplata.class)
public class Pretplata_ { 

    public static volatile SingularAttribute<Pretplata, Date> datumVremePretplate;
    public static volatile SingularAttribute<Pretplata, Integer> idPre;
    public static volatile SingularAttribute<Pretplata, Paket> paket;
    public static volatile SingularAttribute<Pretplata, Korisnik> korisnik;

}