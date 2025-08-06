/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Examples.Mesto.DohvatiMestaExample;
import Examples.Korisnik.KreirajKorisnikaExample;
import ListAccepters.KorisniciResponse;
import RetrofitObjects.KorisnikRequest;
import SharedLibrary.KorisnikDTO;
import SharedLibrary.MestoDTO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jaxb.JaxbConverterFactory;
import Examples.*;

/**
 *
 * @author igor
 */
public class Main {
    
    public static final String API_URL  = "http://localhost:8080/centralniSistem/api/";
    public static void main(String[] args) {
        Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl(API_URL)     
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(JaxbConverterFactory.create())
            .build();
        
        //PODSISTEM 1 FUNKCIONALNOSTI
        
        
//       Example example = new KreirajKorisnikaExample(retrofit);
//       example.demonstrate();

//         Example example = new DohvatiKorisnikeExample(retrofit);
//         example.demonstrate();
//
//        Example example = new KreirajGradExample(retrofit);
//        example.demonstrate();
//
//          Example example = new PromenaEmailZaKorisnikaExample(retrofit);
//          example.demonstrate();

//          Example example = new PromenaMestaZaKorisnikaExample(retrofit);
//          example.demonstrate();

            
        //PODSISTEM 2 FUNKCIONALNOSTI
        
            
            Example example = new DohvatiMestaExample(retrofit);
            example.demonstrate();
       
    }
    
   
           
}
