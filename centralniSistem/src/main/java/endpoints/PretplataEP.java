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
import SharedLibraryPodsistem3.PretplataDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("pretplata")
public class PretplataEP {
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
    
    @POST
    //@Path("{idK}/{idPak}/{datumVremePretplate}")
    public Response kreirajPretplatu(PretplataDTO pretplataDTO){//@PathParam("idK") int idK, @PathParam("idPak") int idPak,@PathParam("datumVremePretplate") String datumVremePretplateString){
         
            RequestDTO request = new RequestDTO("dodajPretplatu", pretplataDTO);
            RequestSender sender = new RequestSender();
            sender.sendRequest(request,podsistem3Q,connFactory);
            return new ResponseHandler().waitForResponseIfNotAdditionalData_NoConsumer(request,fromPodsistem3Q,connFactory);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Date datumVremePretplate;
//        try {
//            datumVremePretplate = sdf.parse(datumVremePretplateString);
//            Object[] objectsToSend = new Object[3];
//            objectsToSend[0] = idK;
//            objectsToSend[1] = idPak;
//            objectsToSend[2] = datumVremePretplate;
//            RequestDTO request = new RequestDTO("dodajPretplatu", objectsToSend);
//            RequestSender sender = new RequestSender();
//            sender.sendRequest(request,podsistem3Q,connFactory);
//            return new ResponseHandler().waitForResponseIfNotAdditionalData_NoConsumer(request,fromPodsistem3Q,connFactory);
//        } catch (ParseException ex) {
//            Logger.getLogger(PretplataEP.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        
        
    }
    
    @GET
    @Path("{idK}")
    public Response dohvatiSvePretplateZaKorisnika(@PathParam("idK") int idK){
        PretplataDTO pretplataDTO = new PretplataDTO();
       RequestDTO request = new RequestDTO("dohvatiSvePretplateZaKorisnika", pretplataDTO);
            RequestSender sender = new RequestSender();
            sender.sendRequest(request,podsistem3Q,connFactory);
            return new <PretplataDTO>ResponseHandler().waitForResponseWithAdditionalData(request, podsistem3Q, connFactory);
    }
    
    @PUT
    @Path("mesecneCene/{idPak}")
    public Response dodajKategoriju(@PathParam("idPak") int idPak){ //List<MesecCena> mesecCena){
        return Response.status(Response.Status.CREATED).entity("Mesecne cene promenjene").build();
    }
}
