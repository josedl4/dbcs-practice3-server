/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Equipo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author joselm
 */
@Stateless
public class EquipoFacade extends AbstractFacade<Equipo> implements EquipoFacadeLocal {
    @PersistenceContext(unitName = "dbcs-practice3-serverPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoFacade() {
        super(Equipo.class);
    }

    @Override
    public Equipo findWithUserAndPlayer(int player, String coach) {
        Query query = em.createNamedQuery("Equipo.findByUserAndPlayer");
        query.setParameter("player", player);
        query.setParameter("coach", coach);
        
        List result = query.getResultList();
        
        if(result == null)
            return null;
        else
            return (Equipo) result.get(0);
    }
    
}
