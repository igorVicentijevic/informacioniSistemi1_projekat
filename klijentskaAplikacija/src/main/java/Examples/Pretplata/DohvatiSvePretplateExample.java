/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Pretplata;

import ListAccepters.KorisniciResponse;
import ListAccepters.PretplateResponse;
import Main.Main;
import RetrofitObjects.KorisnikRequest;
import RetrofitObjects.PretplataRequest;
import SharedLibrary.KorisnikDTO;
import SharedLibraryPodsistem3.PretplataDTO;
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
public class DohvatiSvePretplateExample extends Examples.Example {

    public DohvatiSvePretplateExample(Retrofit retrofit) {
        super(retrofit,"DohvatiPretplate");
    }

    @Override
    public void exampleLogic() {
        PretplataRequest pretplataRequest = this.retrofit.create(PretplataRequest.class);

        int idK = 1;
        
        
        Call<PretplateResponse> dohvatiPretplateCall = pretplataRequest.dohvatiSvePretplateZaKorisnika(idK);
        
        
         
        try {
            Response<PretplateResponse> response = dohvatiPretplateCall.execute();
            
            System.out.println("Status: "+response.code());
            PretplateResponse pretplateResponse = response.body();
            for(PretplataDTO p: pretplateResponse.getPretplata())
                System.out.println(p.toXmlString());
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
