/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Korisnik;

import Examples.Example;
import ListAccepters.KorisniciResponse;
import Main.Main;
import RetrofitObjects.KorisnikRequest;
import SharedLibrary.KorisnikDTO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 *
 * @author igor
 */
public class DohvatiKorisnikeExample extends Example {

    public DohvatiKorisnikeExample(Retrofit retrofit) {
        super(retrofit,"dohvatiSveKorisnike");
    }

    @Override
    public void exampleLogic() {
        KorisnikRequest korisnikRequest = this.retrofit.create(KorisnikRequest.class);

        
        Call<KorisniciResponse> kreirajKorisnikaCall = korisnikRequest.dohvatiSveKorisnike();
        
         
        try {
            Response<KorisniciResponse> response = kreirajKorisnikaCall.execute();
            System.out.println("Status: "+response.code());
            KorisniciResponse korisniciResponse = response.body();
            for(KorisnikDTO k: korisniciResponse.getKorisnici())
                System.out.println(k.toXmlString());
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
