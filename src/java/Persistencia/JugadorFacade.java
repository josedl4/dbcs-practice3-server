package Persistencia;

import Dominio.Jugador;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class JugadorFacade extends AbstractFacade<Jugador> implements JugadorFacadeLocal {

    @PersistenceContext(unitName = "dbcs-practice3-serverPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JugadorFacade() {
        super(Jugador.class);
    }

    @Override
    public List<Jugador> getByCoach(String coachID) {
        Query query = em.createQuery("SELECT j FROM Jugador j, Equipo e WHERE j.id=e.jugId.id AND e.usId.id=:coach");
        query.setParameter("coach", coachID);
        return query.getResultList();
    }

    @Override
    public List<Jugador> withoutTeam() {
        List list = em.createQuery("SELECT j FROM Jugador j WHERE j.id NOT IN(SELECT e.jugId.id FROM Equipo e)").getResultList();
        return list;
    }

}
