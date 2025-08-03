/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoints;

import AdditionalComponents.ResponseHandler;
import SharedLibrary.MestoDTO;
import SharedLibrary.RequestDTO;
import SharedLibrary.Return;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 *
 * @author igor
 */
@Path("mesto")
//@Stateless
public class MestoEP {
    
    @Resource(lookup="jms/__defaultConnectionFactory")
    private ConnectionFactory connFactory;
    
    @Resource(lookup="podsistem1Q")
    private Queue podsistem1Q;

    
    @Resource(lookup="podsistem1CentralniSistemQ")
    private Queue fromPodsistem1Q;
    
    @POST
    public Response kreirajGrad(MestoDTO mesto){
        //Grad se zadaje kao body http zahteva u XML formatu
        //TODO
        JMSContext context = connFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE);
        try{
        
        JMSProducer producer = context.createProducer();
              
//        TextMessage commandMessage = context.createTextMessage("command:kreirajGrad");
//        producer.send(podsistem1Q, commandMessage);
        
        mesto.naziv=mesto.naziv.trim();
        RequestDTO request = new RequestDTO("kreirajGrad", mesto);
        ObjectMessage objMessage = context.createObjectMessage(request);
        producer.send(podsistem1Q, objMessage);
        
        //Waiting for response
        JMSConsumer consumer = context.createConsumer(fromPodsistem1Q,"JMSCorrelationID = '" +request.command + "'");
        return new ResponseHandler().waitForResponseIfNotAdditionalData(consumer);
        }
        finally{
            context.close();
        }
    }
    
    @GET
    public Response dohvatiSvaMesta(){
        JMSContext context = connFactory.createContext();
   
        try{
            JMSProducer producer = context.createProducer();
//            String commandName = "dohvatiSvaMesta";
//            TextMessage commandMessage = context.createTextMessage("command:"+commandName);
//            producer.send(podsistem1Q, commandMessage);
            System.out.println("command:dohvatiSvaMesta sent");
            //TODO napraviti da ne mora da se salje objektna poruka
            MestoDTO mestoDTO = new MestoDTO();
            RequestDTO request = new RequestDTO("dohvatiSvaMesta",mestoDTO);
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

            List<MestoDTO> svaMesta = (List<MestoDTO>) r.getReturnData();

            if(svaMesta == null || svaMesta.isEmpty())
                return Response.status(Response.Status.NO_CONTENT).build();

            return Response.status(Response.Status.OK).entity(new GenericEntity<List<MestoDTO>>(svaMesta){}).build();
        }
        finally{
            context.close();
            
        }
        
        
    }

   
}
