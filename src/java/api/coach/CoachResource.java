package api.coach;

import Dominio.Equipo;
import Dominio.Jugador;
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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

/**
 * Recurso Coach para el entrenador
 *
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

    /**
     * Devuelve los jugadores que forman parte del equipo de un entrenador
     * determinado. La lista estara vacia si no hay jugadores en el equipo.
     *
     * @param coach String que identifica el entrenador del equio
     * @return lista de jugadores del equipo
     */
    @GET
    @Produces("application/json")
    @Path("/{coach}/jugadores")
    public List<Jugador> getEquipo(@PathParam("coach") String coach) {
        return jugadorFacade.getByCoach(coach);
    }

    /**
     * Devuelve los jugadores que no forman parte de ningun equipo. La lista
     * estara vacia si no hay jugadores.
     *
     * @return lista de jugadores sin equipo
     */
    @GET
    @Produces("application/json")
    @Path("/jugadores")
    public List<Jugador> getJugadores() {
        return jugadorFacade.withoutTeam();
    }

    /**
     * Agrega un jugador al quipo de un entrenador
     *
     * @param coach String que identifica al entreneador
     * @param player int que identifica al jugador
     * @return Response con codigo de exito si se hizo correctamente
     */
    @PUT
    @Produces("application/json")
    @Path("/{coach}/jugador/{player}")
    public Response addJugadorEquipo(@PathParam("coach") String coach,
            @PathParam("player") int player) {
        Equipo equipo = new Equipo();
        equipo.setUsId(usuarioFacade.find(coach));
        equipo.setJugId(jugadorFacade.find(player));

        equipoFacade.create(equipo);

        return Response.status(200).build();
    }

    /**
     * Elimina un jugador del equipo de un entrenador. Si no se encontra se
     * devuelve una Responde con codigo de error.
     *
     * @param coach String que identifica el entrengador
     * @param player String que identifica al jugador
     * @return Response con codigo de exito si se hizo correctamente
     */
    @DELETE
    @Produces("application/json")
    @Path("/{coach}/jugador/{player}")
    public Response deleteJugador(@PathParam("coach") String coach, @PathParam("player") int player) {
        if (equipoFacade.findWithUserAndPlayer(player, coach) == null) {
            return Response.status(400).build();
        }
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
