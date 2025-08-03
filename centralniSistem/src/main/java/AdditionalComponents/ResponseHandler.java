/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdditionalComponents;

import SharedLibrary.RequestDTO;
import SharedLibrary.Return;
import endpoints.MestoEP;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 *
 * @author igor
 */
public class ResponseHandler {
   
     //private RequestDTO request;
     
     
     
//     public ResponseHandler(RequestDTO request){
//         this.request = request;
//     }
     public Response waitForResponseIfNotAdditionalData_NoConsumer(RequestDTO request,Queue fromQ, ConnectionFactory connFactory){

         JMSContext context = connFactory.createContext();
         JMSConsumer consumer = context.createConsumer(fromQ,"JMSCorrelationID = '" + request.command + "'");
         
         Response response = waitForResponseIfNotAdditionalData(consumer);
         context.close();
         return response;
     }
     
        public <T> Response waitForResponseWithAdditionalData(RequestDTO request,Queue fromQ, ConnectionFactory connFactory){
            JMSContext context = connFactory.createContext();
            JMSConsumer consumer = context.createConsumer(fromQ,"JMSCorrelationID = '" + request.command + "'");
            
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

               List<T> data = (List<T>) r.getReturnData();

               if(data== null || data.isEmpty())
                   return Response.status(Response.Status.NO_CONTENT).build();

               return Response.status(Response.Status.OK).entity(new GenericEntity<List<T>>(data){}).build();
        }
        public Response waitForResponseIfNotAdditionalData(JMSConsumer consumer){
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

           String returnInfo = (String) r.getReturnData();


           return Response.status(Response.Status.OK).entity(returnInfo).build();
       }
   }
