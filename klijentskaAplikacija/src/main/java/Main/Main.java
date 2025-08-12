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
import Examples.AudioSnimak.BrisanjeAudioSnimkaExample;
import Examples.AudioSnimak.DodavanjeKategorijeAudioSnimkuExample;
import Examples.AudioSnimak.DohvatanjeKategorijaZaAudioSnimakExample;
import Examples.AudioSnimak.DohvatanjeSvihAudioSnimkaExample;
import Examples.AudioSnimak.DohvatiSvaSlusanjaExample;
import Examples.AudioSnimak.KreirajAudioSnimakExample;
import Examples.AudioSnimak.KreirajSlusanjeExample;
import Examples.AudioSnimak.PromenaNazivaAudioSnimkaExample;
import Examples.Kategorija.DohvatanjeSvihKategorijaExample;
import Examples.Kategorija.KreirajKategorijuExample;
import Examples.Korisnik.DodajAudioSnimakUOmiljeneExample;
import Examples.Korisnik.DohvatiKorisnikeExample;
import Examples.Korisnik.DohvatiOmiljeneSnimkeExample;
import Examples.Korisnik.PromenaEmailZaKorisnikaExample;
import Examples.Korisnik.PromenaMestaZaKorisnikaExample;
import Examples.Mesto.KreirajGradExample;
import Examples.Ocena.DohvatiOceneExample;
import Examples.Ocena.IzbrisiOcenuExample;
import Examples.Ocena.KreirajOcenuExample;
import Examples.Ocena.PromenaOcenuExample;
import Examples.Paket.DohvatiSvePaketeExample;
import Examples.Paket.KreirajPaketExample;
import Examples.Paket.PromenaMesecneCeneZaPaketExample;
import Examples.Pretplata.DohvatiSvePretplateExample;
import Examples.Pretplata.KreirajPretplatuKorisnikaNaPaketExample;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 *
 * @author igor
 */
public class Main {
    
    public static final String API_URL  = "http://localhost:8080/centralniSistem/api/";
    public static void main(String[] args) {
        Retrofit retrofitJSON =
        new Retrofit.Builder()
            .baseUrl(API_URL)     
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create()) // JSON
            //.addConverterFactory(SimpleXmlConverterFactory.create()) // XML
            .build();
        Retrofit retrofitXML =
        new Retrofit.Builder()
            .baseUrl(API_URL)     
            //.addConverterFactory(GsonConverterFactory.create())
            //.addConverterFactory(JacksonConverterFactory.create()) // JSON
            //.addConverterFactory(SimpleXmlConverterFactory.create()) // XML
              .addConverterFactory(JaxbConverterFactory.create())//XML

            .build();
        
        //AudioSnimak
//        Example example = new DohvatanjeSvihAudioSnimkaExample(retrofitJSON);
//        example.demonstrate();
          
//         Example example = new BrisanjeAudioSnimkaExample(retrofitJSON);
//         example.demonstrate();

//         Example example = new DodavanjeKategorijeAudioSnimkuExample(retrofitJSON);
//         example.demonstrate();

//            Example example = new DohvatiSvaSlusanjaExample(retrofitJSON);
//            example.demonstrate();
            
//           Example example = new DohvatanjeKategorijaZaAudioSnimakExample(retrofitJSON);
//           example.demonstrate();
            
//            Example example = new KreirajAudioSnimakExample(retrofitJSON);
//            example.demonstrate();
        
//            Example example = new KreirajSlusanjeExample(retrofitJSON);
//            example.demonstrate();! PK 

//            Example example = new PromenaNazivaAudioSnimkaExample(retrofitJSON);
//            example.demonstrate();

        //KATEGORIJA
        
//             Example example = new DohvatanjeSvihKategorijaExample(retrofitJSON );
//             example.demonstrate();
               
//               Example example = new KreirajKategorijuExample(retrofitJSON);
//               example.demonstrate();

        //Korisnik
//            Example example = new DodajAudioSnimakUOmiljeneExample(retrofitJSON);
//            example.demonstrate();

//            Example example = new DohvatiKorisnikeExample(retrofitXML);
//            example.demonstrate();
//
//            Example example = new DohvatiOmiljeneSnimkeExample(retrofitXML);
//            example.demonstrate();

//                Example example = new KreirajKorisnikaExample(retrofitXML);
//                example.demonstrate();
            
//            Example example = new PromenaEmailZaKorisnikaExample(retrofitXML);
//            example.demonstrate();

//            Example example = new PromenaMestaZaKorisnikaExample(retrofitXML);
//            example.demonstrate();
//               
         //MESTO
         
//         Example example = new DohvatiMestaExample(retrofitXML);
//         example.demonstrate();
            
//            Example example = new KreirajGradExample(retrofitXML);
//            example.demonstrate();

        //OCENE
//        Example example = new DohvatiOceneExample(retrofitJSON);
//        example.demonstrate();

//          TODO  Example example = new IzbrisiOcenuExample(retrofitJSON);
//            example.demonstrate();
        
//            Example example = new KreirajOcenuExample(retrofitJSON);//PARSER
//            example.demonstrate();

//          Example example = new PromenaOcenuExample(retrofitJSON);//PARSER
//          example.demonstrate();

        //PAKET
        
//        Example example = new DohvatiSvePaketeExample(retrofitJSON);
//        example.demonstrate();

//        Example example = new KreirajPaketExample(retrofitJSON);
//        example.demonstrate();

//        Example example = new PromenaMesecneCeneZaPaketExample(retrofitJSON);
//        example.demonstrate();

        //PRETPLATA
        
//          Example example = new DohvatiSvePretplateExample(retrofitJSON);
//          example.demonstrate();

//            Example example = new KreirajPretplatuKorisnikaNaPaketExample(retrofitJSON);
//            example.demonstrate(); !!DATE PARSER U RESTU

        
            
        
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
        
            
//            Example example = new DohvatiMestaExample(retrofit);
//            example.demonstrate();
            
//            Example example = new DohvatiSvePaketeExample(retrofit);
//            example.demonstrate();

//            Example example = new DohvatiSvePretplateExample(retrofit);
//            example.demonstrate();
    }
    
   
           
}
