package Dominio;

import Dominio.Equipo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-20T17:39:00")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile ListAttribute<Usuario, Equipo> equipoList;
    public static volatile SingularAttribute<Usuario, String> id;
    public static volatile SingularAttribute<Usuario, String> nombre;

}