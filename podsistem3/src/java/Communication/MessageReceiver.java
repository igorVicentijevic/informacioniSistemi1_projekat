/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import Commands.Command;
import Commands.CommandFactory;
import SharedLibrary.RequestDTO;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import podsistem3.Main;

/**
 *
 * @author igor
 */
public class MessageReceiver extends Thread{
//       private Queue<String> commandsQ = new LinkedList<String>();
//       private Queue<ObjectMessage> objectMsgQ = new LinkedList<ObjectMessage>();
       
    
    
    //private JMSConsumer consumer;
   // private JMSContext context;

    private static CommandFactory commandFactory = new CommandFactory();
    
    private void tryExecutingCommand(RequestDTO r){
      
        String commandName = r.command;
        Command command = commandFactory.getCommand(commandName);
        
        System.out.println("Trying to execute command "+r.command+"...");
        command.execute(r.o);
            
    }

    
    
    @Override
    public void run() {
        JMSContext context = Main.connFactory.createContext();
        JMSConsumer consumer = context.createConsumer(Main.podsistem3Q);
        
        while(true){
            Message msg = consumer.receive();
            handleMessageReceived(msg);
        }
//        consumer.setMessageListener(new MessageListener(){
//             @Override
//             public void onMessage(Message message) {
//                  handleMessageReceived(message);
//             }
//
//        });
//        while(true);
        
    }
    
    private boolean flush = false;
    public void setFlush(boolean value){
        flush = value;
    }

    private void handleMessageReceived(Message message){
        
        if(flush)
            return;
        
        try {
            if(message instanceof ObjectMessage){
               ObjectMessage om = (ObjectMessage) message;
                RequestDTO request = (RequestDTO) om.getObject();        
                tryExecutingCommand(request);
            }
            else{
                System.out.println("Received message is not type of ObjectMessage");
                return;
            }
           
         } catch (JMSException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
   

    
    
       
       
    
}
