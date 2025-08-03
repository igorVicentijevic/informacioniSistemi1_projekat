package entities;

import entities.Paket;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-07-26T22:41:25")
@StaticMetamodel(Meseccena.class)
public class Meseccena_ { 

    public static volatile SingularAttribute<Meseccena, Integer> idMC;
    public static volatile SingularAttribute<Meseccena, Integer> mesec;
    public static volatile ListAttribute<Meseccena, Paket> paketList;
    public static volatile SingularAttribute<Meseccena, Float> cena;

}