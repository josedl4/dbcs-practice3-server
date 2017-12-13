package Dominio;

import Dominio.Equipo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-13T18:00:41")
@StaticMetamodel(Jugador.class)
public class Jugador_ { 

    public static volatile SingularAttribute<Jugador, String> dateofbirth;
    public static volatile SingularAttribute<Jugador, String> nationality;
    public static volatile SingularAttribute<Jugador, Integer> jerseynumber;
    public static volatile SingularAttribute<Jugador, String> contractuntil;
    public static volatile SingularAttribute<Jugador, String> name;
    public static volatile ListAttribute<Jugador, Equipo> equipoList;
    public static volatile SingularAttribute<Jugador, String> marketvalue;
    public static volatile SingularAttribute<Jugador, Integer> id;
    public static volatile SingularAttribute<Jugador, String> position;

}