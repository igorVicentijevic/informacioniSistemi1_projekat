package entities;

import entities.Snimak;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-07-25T15:56:22")
@StaticMetamodel(Kategorija.class)
public class Kategorija_ { 

    public static volatile ListAttribute<Kategorija, Snimak> snimakList;
    public static volatile SingularAttribute<Kategorija, Integer> idKat;
    public static volatile SingularAttribute<Kategorija, String> naziv;

}