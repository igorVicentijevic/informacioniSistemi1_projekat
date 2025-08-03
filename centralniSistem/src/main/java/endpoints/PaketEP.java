/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoints;

import AdditionalComponents.RequestSender;
import AdditionalComponents.ResponseHandler;
import SharedLibrary.RequestDTO;
import SharedLibraryPodsistem3.PaketDTO;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
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
@Path("paket")
//@Stateless
public class PaketEP {
    @Resource(lookup="jms/__defaultConnectionFactory")
    private ConnectionFactory connFactory;
    
    @Resource(lookup="podsistem1Q")
    private  Queue podsistem1Q;
    
    @Resource(lookup="podsistem2Q")
    private Queue podsistem2Q;
    
    @Resource(lookup="podsistem3Q")
    private  Queue podsistem3Q;
    
    @Resource(lookup="podsistem3CentralniSistemQ")
    private  Queue fromPodsistem3Q;
    
    @GET
    public Response dohvatiSvePakete(){
        PaketDTO paketDTO = new PaketDTO();
        RequestDTO request = new RequestDTO("dohvatiSvePakete",paketDTO);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem3Q, connFactory);
        
        return new ResponseHandler().waitForResponseWithAdditionalData(request, podsistem3Q, connFactory);
    }
    @POST
    public Response kreirajPaket(PaketDTO paketDTO){
        RequestDTO request = new RequestDTO("kreirajPaket",paketDTO);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem3Q, connFactory);
        
        return new ResponseHandler().waitForResponseIfNotAdditionalData_NoConsumer(request,fromPodsistem3Q,connFactory);
        
    }
    
    @PUT
    @Path("mesecneCene/{idPak}/{cena}")
    public Response promeniCenu(@PathParam("idPak") int idPak,@PathParam("cena") float cena){ //List<MesecCena> mesecCena){
       
        Object[] objectsToSend = new Object[2];
        objectsToSend[0] = idPak;
        objectsToSend[1] = cena;
        RequestDTO request = new RequestDTO("promeniMesecnuCenu",objectsToSend);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem3Q, connFactory);
        
        return new ResponseHandler().waitForResponseIfNotAdditionalData_NoConsumer(request,fromPodsistem3Q,connFactory);
        
    }
}
