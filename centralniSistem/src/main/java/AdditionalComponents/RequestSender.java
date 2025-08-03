/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdditionalComponents;

import SharedLibrary.RequestDTO;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

/**
 *
 * @author igor
 */
public class RequestSender {
    
    
    public void sendRequest(RequestDTO request,Queue destinationQueue,ConnectionFactory connFactory)
    {
      
        JMSContext context = connFactory.createContext();    
        JMSProducer producer = context.createProducer();  
        ObjectMessage objMessage = context.createObjectMessage(request);
        producer.send(destinationQueue, objMessage);
    }
    
    
}
