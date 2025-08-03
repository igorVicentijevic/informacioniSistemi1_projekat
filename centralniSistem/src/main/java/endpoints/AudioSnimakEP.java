/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoints;

import AdditionalComponents.RequestSender;
import AdditionalComponents.ResponseHandler;
import SharedLibrary.RequestDTO;
import SharedLibraryPodsistem2.AudioSnimakDTO;
import SharedLibraryPodsistem2.KategorijaDTO;
import SharedLibraryPodsistem3.OcenaDTO;
import SharedLibraryPodsistem3.PaketDTO;
import SharedLibraryPodsistem3.SlusaDTO;
import javax.annotation.Resource;
import javax.ejb.Stateless;
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
@Path("audioSnimak")
//@Stateless
public class AudioSnimakEP {
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
    @Path("slusa")
    public Response dodajSlusanje(SlusaDTO slusaDTO){
        RequestDTO request = new RequestDTO("dodajSlusanje",slusaDTO);
       RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem3Q, connFactory);
        ResponseHandler rh = new ResponseHandler();
        
        return rh.<KategorijaDTO>waitForResponseWithAdditionalData(request, fromPodsistem3Q, connFactory);
    }
  
    
    @GET
    @Path("slusa/{idSni}")
    public Response dohvatiSvaSlusanjaZaAudioSnimak(@PathParam("idSni") int idSni){
        
        
        RequestDTO request = new RequestDTO("dohvatiSvaSlusanjaZaAudioSnimak", idSni);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem3Q, connFactory);
        ResponseHandler rh = new ResponseHandler();
        
        return rh.<SlusaDTO>waitForResponseWithAdditionalData(request, fromPodsistem3Q, connFactory);
    }
    
    
    
    @GET
    public Response dohvatiSveAudioSnimke(){
        
        AudioSnimakDTO audioSnimakDTO = new AudioSnimakDTO();
        RequestDTO request = new RequestDTO("dohvatiSveSnimke",audioSnimakDTO);
        
        RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem2Q, connFactory);
        ResponseHandler rh = new ResponseHandler();
        
        return rh.<KategorijaDTO>waitForResponseWithAdditionalData(request, fromPodsistem2Q, connFactory);

    }
    @GET
    @Path("kategorija/{idSni}")
    public Response dohvatiMojeKategorije(@PathParam("idSni") int idSni){
        
        AudioSnimakDTO audioSnimakDTO = new AudioSnimakDTO();
        audioSnimakDTO.idSni = idSni;
        RequestDTO request = new RequestDTO("dohvatiKategorijeZaAudioSnimak",audioSnimakDTO);
        
        RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem2Q, connFactory);
        ResponseHandler rh = new ResponseHandler();
        
        return rh.<KategorijaDTO>waitForResponseWithAdditionalData(request, fromPodsistem2Q, connFactory);

    }
    
    @POST
    public Response kreirajAudioSnimak(AudioSnimakDTO audioSnimakDTO){
         RequestDTO request = new RequestDTO("kreirajSnimak",audioSnimakDTO);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem2Q, connFactory);
        
        return new ResponseHandler().waitForResponseIfNotAdditionalData_NoConsumer(request,fromPodsistem2Q,connFactory);
        
    }
    
    @PUT
    @Path("naziv/{idSni}/{noviNaziv}")
    public Response promeniNaziv(@PathParam("idSni") int idSni,@PathParam("noviNaziv") String noviNaziv){
        AudioSnimakDTO snimakDTO = new AudioSnimakDTO();
        snimakDTO.idSni = idSni;
        snimakDTO.naziv = noviNaziv;
        RequestDTO request = new RequestDTO("promeniNazivSnimka",snimakDTO);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request,podsistem2Q,connFactory);
        return new ResponseHandler().waitForResponseIfNotAdditionalData_NoConsumer(request,fromPodsistem2Q,connFactory);
        
    }
    
    @PUT
    @Path("kategorija/{idSni}/{idKat}")
    public Response dodajKategoriju(@PathParam("idSni") int idSni, @PathParam("idKat") int idKat){
        int[] keysToSend = new int[2];
        keysToSend[0] = idSni;
        keysToSend[1] = idKat;
        RequestDTO request = new RequestDTO("dodajKategoriju", keysToSend);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request,podsistem2Q,connFactory);
        return new ResponseHandler().waitForResponseIfNotAdditionalData_NoConsumer(request,fromPodsistem2Q,connFactory);
    }
    
    @DELETE
    @Path("/{idK}/{idSni}")
    public Response izbrisiSnimak(@PathParam("idK") int idK, @PathParam("idSni") int idSni){
        int[] keysToSend = new int[2];
        keysToSend[0] = idK;
        keysToSend[1] = idSni;
        RequestDTO request = new RequestDTO("izbrisiSnimak", keysToSend);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request,podsistem2Q,connFactory);
        return new ResponseHandler().waitForResponseIfNotAdditionalData_NoConsumer(request,fromPodsistem2Q,connFactory);
    }
    
    
}
