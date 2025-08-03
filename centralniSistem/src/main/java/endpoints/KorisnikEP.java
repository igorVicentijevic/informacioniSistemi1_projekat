/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoints;

import AdditionalComponents.RequestSender;
import AdditionalComponents.ResponseHandler;
import SharedLibrary.KorisnikDTO;
import SharedLibrary.MestoDTO;
import SharedLibrary.RequestDTO;
import SharedLibrary.Return;
import SharedLibraryPodsistem2.AudioSnimakDTO;
import SharedLibraryPodsistem3.SlusaDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 *
 * @author igor
 */
@Path("korisnik")
//@Stateless
public class KorisnikEP {
    
    @Resource(lookup="jms/__defaultConnectionFactory")
    private ConnectionFactory connFactory;
    
    @Resource(lookup="podsistem1Q")
    private Queue podsistem1Q;
    
    @Resource(lookup="podsistem1CentralniSistemQ")
    private Queue fromPodsistem1Q;

       
    @Resource(lookup="podsistem3Q")
    private  Queue podsistem3Q;
    
    @Resource(lookup="podsistem3CentralniSistemQ")
    private  Queue fromPodsistem3Q;


    @POST
    public Response kreirajKorisnika(KorisnikDTO korisnik){
       // Korisnik se zadaje u body http zahteva u XML formatu
       JMSContext context = connFactory.createContext();
       try{
        
        JMSProducer producer = context.createProducer();

        RequestDTO request = new RequestDTO("kreirajKorisnika",korisnik);
        
        ObjectMessage objMessage = context.createObjectMessage(request);
        producer.send(podsistem1Q, objMessage);
        
        //Waiting for response...
        
        JMSConsumer consumer = context.createConsumer(fromPodsistem1Q,"JMSCorrelationID = '" + request.command + "'");
        return new ResponseHandler().waitForResponseIfNotAdditionalData(consumer);
        
       }finally{
           context.close();
       }
    }
    
    @POST
    @Path("omiljeni/{idK}/{idSni}")
    public Response dodajSnimakUOmiljene(@PathParam("idK") int idK, @PathParam("idSni") int idSni){
       int[] keys = new int[2];
       keys[0] = idK;
       keys[1] = idSni;
       RequestDTO request = new RequestDTO("dodajSnimakUOmiljene",keys);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem3Q, connFactory);
        
        return new ResponseHandler().waitForResponseIfNotAdditionalData_NoConsumer(request,fromPodsistem3Q,connFactory);
        
    }
    
   
    
    
    
    @PUT
    @Path("email/{idK}/{newEmail}")
    public Response promeniEmail(@PathParam("idK") int idK,@PathParam("newEmail") String newEmail){
        
            JMSContext context = connFactory.createContext();
            try{
            JMSProducer producer = context.createProducer();

            KorisnikDTO korisnikNewEmail = new KorisnikDTO();
            korisnikNewEmail.email = newEmail;
            korisnikNewEmail.idK = idK;
            
            RequestDTO request = new RequestDTO("promeniEmail",korisnikNewEmail);

            ObjectMessage objMessage = context.createObjectMessage(request);
            producer.send(podsistem1Q, objMessage);
            
            JMSConsumer consumer = context.createConsumer(fromPodsistem1Q,"JMSCorrelationID = '" + request.command + "'");
            return new ResponseHandler().waitForResponseIfNotAdditionalData(consumer);
            //return Response.status(Response.Status.CREATED).entity("Poslat zahtev za promenu emaila").build();
        }finally{
            context.close();
        }
    }
    
    @GET
    @Path("omiljeni/{idK}")
    public Response dohvatiOmiljeneZaKorisnika(@PathParam("idK") int idK){
        
        RequestDTO request = new RequestDTO("dohvatiOmiljeneSnimkeZaKorisnika", idK);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem3Q, connFactory);
        ResponseHandler rh = new ResponseHandler();
        
        return rh.<AudioSnimakDTO>waitForResponseWithAdditionalData(request, fromPodsistem3Q, connFactory);
    }
    
    @PUT
    @Path("mesto/{idK}/{naziv}")
    public Response promeniMesto(@PathParam("idK") int idK,@PathParam("naziv") String naziv){
        
        JMSContext context = connFactory.createContext();
        try{
            JMSProducer producer = context.createProducer();


            KorisnikDTO korisnikNewMesto = new KorisnikDTO();

            MestoDTO mesto = new MestoDTO();
            mesto.naziv = naziv;     
            korisnikNewMesto.idK = idK;

            korisnikNewMesto.mesto = mesto;
            
            RequestDTO request = new RequestDTO("promeniMesto", korisnikNewMesto);

            ObjectMessage objMessage = context.createObjectMessage(request);
            producer.send(podsistem1Q, objMessage);
            
            JMSConsumer consumer = context.createConsumer(fromPodsistem1Q,"JMSCorrelationID = '" + request.command + "'");
            return new ResponseHandler().waitForResponseIfNotAdditionalData(consumer);

            //return Response.status(Response.Status.CREATED).entity("Zahtev za promenu mesta poslat").build();
        }finally{
            context.close();
        }
    }
    
    @GET
    public Response dohvatiSveKorisnike(){
        JMSContext context = connFactory.createContext();
   
        try{
            JMSProducer producer = context.createProducer();

            System.out.println("command:dohvatiSveKorisnike sent");
     
           
            RequestDTO request = new RequestDTO("dohvatiSveKorisnike",null);
            ObjectMessage objMessage = context.createObjectMessage(request);
            producer.send(podsistem1Q, objMessage);
            
            

            JMSConsumer consumer = context.createConsumer(fromPodsistem1Q,"JMSCorrelationID = '" +request.command + "'");
            
            Message msg = consumer.receive(10000);

            if(msg == null)
                return Response.status(Response.Status.GATEWAY_TIMEOUT).build();
            if(!(msg instanceof ObjectMessage))
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


            ObjectMessage om = (ObjectMessage) msg;

            Return r =null;
            try {
                r = (Return) om.getObject();


            } catch (JMSException ex) {
                Logger.getLogger(MestoEP.class.getName()).log(Level.SEVERE, null, ex);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

            List<KorisnikDTO> sviKorisnici = (List<KorisnikDTO>) r.getReturnData();

            if(sviKorisnici == null || sviKorisnici.isEmpty())
                return Response.status(Response.Status.NO_CONTENT).build();

            return Response.status(Response.Status.OK).entity(new GenericEntity<List<KorisnikDTO>>(sviKorisnici){}).build();
        }
        finally{
            context.close();
            
        }
    }
}
