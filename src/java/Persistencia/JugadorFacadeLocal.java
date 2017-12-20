package Persistencia;

import Dominio.Jugador;
import java.util.List;
import javax.ejb.Local;

@Local
public interface JugadorFacadeLocal {

    void create(Jugador jugador);

    void edit(Jugador jugador);

    void remove(Jugador jugador);

    Jugador find(Object id);

    List<Jugador> findAll();

    List<Jugador> findRange(int[] range);

    int count();
    
    List<Jugador> getByCoach(String coachID);
    
    List<Jugador> withoutTeam();
    
}
