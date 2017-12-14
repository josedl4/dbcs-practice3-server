/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.coach;

import Dominio.Equipo;
import Dominio.Jugador;
import Persistencia.EquipoFacade;
import Persistencia.EquipoFacadeLocal;
import Persistencia.JugadorFacadeLocal;
import Persistencia.UsuarioFacadeLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author joselm
 */
@Path("coach")
public class CoachResource {
    JugadorFacadeLocal jugadorFacade = lookupJugadorFacadeLocal();
    UsuarioFacadeLocal usuarioFacade = lookupUsuarioFacadeLocal();
    EquipoFacadeLocal equipoFacade = lookupEquipoFacadeLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CoachResource
     */
    public CoachResource() {
    }

    @GET
    @Produces("application/json")
    @Path("/{coach}/jugadores")
    public List<Jugador> getEquipo(@PathParam("coach") String coach) {
        return jugadorFacade.getByCoach(coach);
    }
    
    @GET
    @Produces("application/json")
    @Path("/jugadores")
    public List<Jugador> getJugadores() {
        return jugadorFacade.withoutTeam();
    }
    
    @PUT
    @Produces("application/json")
    @Path("/{coach}/jugador/{player}")
    public Response addJugadorEquipo(@PathParam("coach") String coach, 
            @PathParam("player") int player){
        Equipo equipo = new Equipo();
        equipo.setUsId(usuarioFacade.find(coach));
        equipo.setJugId(jugadorFacade.find(player));
        
        equipoFacade.create(equipo);
        
        return Response.status(200).build();
    }
    
    @DELETE
    @Produces("application/json")
    @Path("/{coach}/jugador/{player}")
    public Response deleteJugador(@PathParam("coach") String coach, @PathParam("player") int player){
        equipoFacade.remove(equipoFacade.findWithUserAndPlayer(player, coach));
        return Response.status(200).build();
    }

    private EquipoFacadeLocal lookupEquipoFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (EquipoFacadeLocal) c.lookup("java:global/dbcs-practice3-server/EquipoFacade!Persistencia.EquipoFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private UsuarioFacadeLocal lookupUsuarioFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (UsuarioFacadeLocal) c.lookup("java:global/dbcs-practice3-server/UsuarioFacade!Persistencia.UsuarioFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private JugadorFacadeLocal lookupJugadorFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (JugadorFacadeLocal) c.lookup("java:global/dbcs-practice3-server/JugadorFacade!Persistencia.JugadorFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
}
