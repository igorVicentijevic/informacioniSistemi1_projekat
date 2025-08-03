/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoints;

import AdditionalComponents.RequestSender;
import AdditionalComponents.ResponseHandler;
import SharedLibrary.RequestDTO;
import SharedLibraryPodsistem2.KategorijaDTO;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author igor
 */
@Path("kategorija")
//@Stateless
public class KategorijaEP {
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
    
    @POST
    public Response kreirajKategoriju(KategorijaDTO kategorija){//Kategorija kategorija){
        //Kategorija se zadaje u body http zahteva u XML formatu
        RequestDTO request = new RequestDTO("kreirajKategoriju",kategorija);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem2Q, connFactory);
        
        return new ResponseHandler().waitForResponseIfNotAdditionalData_NoConsumer(request, fromPodsistem2Q, connFactory);
    }
    
    @GET
    public Response dohvatiSveKategorije(){
        
        RequestDTO request = new RequestDTO("dohvatiSveKategorije",null);
        RequestSender sender = new RequestSender();
        sender.sendRequest(request, podsistem2Q, connFactory);
        ResponseHandler rh = new ResponseHandler();
        
        return rh.<KategorijaDTO>waitForResponseWithAdditionalData(request, fromPodsistem2Q, connFactory);

    }
}
