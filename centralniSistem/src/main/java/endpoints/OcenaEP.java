/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoints;

import AdditionalComponents.RequestSender;
import AdditionalComponents.ResponseHandler;
import SharedLibrary.RequestDTO;
import SharedLibraryPodsistem3.OcenaDTO;
import SharedLibraryPodsistem3.SlusaDTO;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author igor
 */
@Path("ocena")
public class OcenaEP {
    
    @Resource(lookup="jms/__defaultConnectionFactory")
    private ConnectionFactory connFactory;
    
    @Resource(lookup="podsistem1Q")
    private Queue podsistem1Q;
    
    @Resource(lookup="podsistem2Q")
    private Queue podsistem2Q;

    @Resource(lookup="podsistem1CentralniSistemQ")
    private Queue fromPodsistem1Q;
    @Resource(lookup="podsistem2CentralniSistemQ")
    private Queue fromPodsistem2Q;
    
    @Resource(lookup="podsistem3Q")
    private  Queue podsistem3Q;
    
    @Resource(lookup="podsistem3CentralniSistemQ")
    private  Queue fromPodsistem3Q;
    
    @POST
    public Response dodajOcenu(OcenaDTO ocenaDTO){
        
        
        RequestDTO request = new RequestDTO("dodajOcenu", ocenaDTO);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem3Q, connFactory);
        ResponseHandler rh = new ResponseHandler();
        
        return rh.waitForResponseIfNotAdditionalData_NoConsumer(request, podsistem3Q, connFactory);
    }
    
    @GET
    @Path("{idSni}")
    public Response dohvatiSveOceneZaAudioSnimak(@PathParam("idSni") int idSni){
        
        
        RequestDTO request = new RequestDTO("dohvatiSveOceneZaAudioSnimak", idSni);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem3Q, connFactory);
        ResponseHandler rh = new ResponseHandler();
        
        return rh.<SlusaDTO>waitForResponseWithAdditionalData(request, fromPodsistem3Q, connFactory);
    }
    
    
    @PUT
    public Response promeniOcenu(OcenaDTO ocenaDTO){
        RequestDTO request = new RequestDTO("promeniOcenu", ocenaDTO);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem3Q, connFactory);
        ResponseHandler rh = new ResponseHandler();
        
        return rh.waitForResponseIfNotAdditionalData_NoConsumer(request, fromPodsistem3Q, connFactory);
    }
    
    @DELETE
    @Path("{idO}")
    public Response izbrisiOcenu(@PathParam("idO") int idO){
        OcenaDTO ocenaDTO =new OcenaDTO();
        ocenaDTO.idOce = idO;
        
        RequestDTO request = new RequestDTO("izbrisiOcenu", ocenaDTO);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem3Q, connFactory);
        
        ResponseHandler rh = new ResponseHandler();
        
        return rh.waitForResponseIfNotAdditionalData_NoConsumer(request, fromPodsistem3Q, connFactory);
    }
   
}
