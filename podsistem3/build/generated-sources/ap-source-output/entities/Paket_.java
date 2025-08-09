package entities;

import entities.Korisnik;
import entities.Meseccena;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-08-09T20:29:54")
@StaticMetamodel(Paket.class)
public class Paket_ { 

    public static volatile SingularAttribute<Paket, String> naziv;
    public static volatile SingularAttribute<Paket, Integer> idPak;
    public static volatile ListAttribute<Paket, Meseccena> meseccenaList;
    public static volatile ListAttribute<Paket, Korisnik> korisnikList;

}