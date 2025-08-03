package entities;

import entities.Ocena;
import entities.Paket;
import entities.Slusa;
import entities.Snimak;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-08-03T21:39:58")
@StaticMetamodel(Korisnik.class)
public class Korisnik_ { 

    public static volatile SingularAttribute<Korisnik, String> ime;
    public static volatile SingularAttribute<Korisnik, Integer> idK;
    public static volatile ListAttribute<Korisnik, Snimak> snimakList;
    public static volatile ListAttribute<Korisnik, Snimak> snimakList1;
    public static volatile ListAttribute<Korisnik, Slusa> slusaList;
    public static volatile ListAttribute<Korisnik, Ocena> ocenaList;
    public static volatile SingularAttribute<Korisnik, Integer> godiste;
    public static volatile SingularAttribute<Korisnik, String> mesto;
    public static volatile ListAttribute<Korisnik, Paket> paketList;
    public static volatile SingularAttribute<Korisnik, String> pol;
    public static volatile SingularAttribute<Korisnik, String> email;

}