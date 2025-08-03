/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

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
        
         KorisnikRequest korisnikRequest = retrofit.create(KorisnikRequest.class);
//        
     //    Call<KorisniciResponse> korisnikCall = korisnikRequest.dohvatiSveKorisnike();
     KorisnikDTO toCreate = new KorisnikDTO();
     toCreate.email = "jasam@jasam";
     toCreate.godiste=2001;
     toCreate.ime = "Nisam";
     toCreate.pol = "M";
        MestoDTO mesto = new MestoDTO();
        mesto.naziv = "Bor";
     toCreate.mesto = mesto;
     
        Call<Void> kreirajKorisnikaCall = korisnikRequest.kreirajKorisnika(toCreate);
        
        
        try {
            Response<Void> response = kreirajKorisnikaCall.execute();
            System.out.println(response.body().toString());
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
           
}
