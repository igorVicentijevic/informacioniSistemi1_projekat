/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Korisnik;

import Examples.Example;
import ListAccepters.AudioSnimciResponse;
import ListAccepters.KorisniciResponse;
import Main.Main;
import RetrofitObjects.KorisnikRequest;
import SharedLibrary.KorisnikDTO;
import SharedLibraryPodsistem2.AudioSnimakDTO;
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
public class DohvatiOmiljeneSnimkeExample extends Example {

    public DohvatiOmiljeneSnimkeExample(Retrofit retrofit) {
        super(retrofit,"DohvatiOmiljeneSnimke");
    }

    @Override
    public void exampleLogic() {
        KorisnikRequest korisnikRequest = this.retrofit.create(KorisnikRequest.class);

        int idK = 1;
        
        Call<AudioSnimciResponse> dohvatiSnimkeCall = korisnikRequest.dohvatiOmiljeneZaKorisnika(idK);
        
         
        try {
            Response<AudioSnimciResponse> response = dohvatiSnimkeCall.execute();
            System.out.println("Status: "+response.code());
            AudioSnimciResponse audioSnimakResponse = response.body();
            for(AudioSnimakDTO a:audioSnimakResponse.getAudioSnimci())
                System.out.println(a.toXmlString());
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
