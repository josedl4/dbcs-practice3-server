/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.admin;

import Dominio.Jugador;
import Persistencia.JugadorFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author joselm
 */
@Path("admin")
public class AdminResource {
    JugadorFacadeLocal jugadorFacade = lookupJugadorFacadeLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AdminResource
     */
    public AdminResource() {
    }

    @POST
    @Path("/jugador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response importPlayer(String content) throws ParseException {
        
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(content);
        Jugador jugador = new Jugador();
        jugador.setContractuntil((String) json.get("contractuntil"));
        jugador.setDateofbirth((String) json.get("dateofbirth"));
        jugador.setJerseynumber(Math.toIntExact((long)json.get("jerseynumber")));
        jugador.setMarketvalue((String) json.get("marketvalue"));
        jugador.setName((String) json.get("name"));
        jugador.setNationality((String) json.get("nationality"));
        jugador.setPosition((String) json.get("position"));
        
        jugadorFacade.create(jugador);
        
        if(!jugadorFacade.findAll().contains(jugador))
            return Response.status(500).build();
        
        return Response.status(200).build();
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
