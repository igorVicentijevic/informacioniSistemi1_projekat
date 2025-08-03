package entities;

import entities.Kategorija;
import entities.Korisnik;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-08-03T23:42:36")
@StaticMetamodel(Snimak.class)
public class Snimak_ { 

    public static volatile ListAttribute<Snimak, Kategorija> kategorijaList;
    public static volatile SingularAttribute<Snimak, Korisnik> idKvlasnik;
    public static volatile SingularAttribute<Snimak, Integer> idSni;
    public static volatile SingularAttribute<Snimak, Date> vremePostavljanja;
    public static volatile SingularAttribute<Snimak, Date> trajanje;
    public static volatile SingularAttribute<Snimak, String> naziv;

}