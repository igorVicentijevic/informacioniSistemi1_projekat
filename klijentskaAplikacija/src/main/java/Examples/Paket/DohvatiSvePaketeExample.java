/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Paket;

import ListAccepters.KorisniciResponse;
import ListAccepters.PaketiResponse;
import Main.Main;
import RetrofitObjects.KorisnikRequest;
import RetrofitObjects.PaketRequest;
import SharedLibrary.KorisnikDTO;
import SharedLibraryPodsistem3.PaketDTO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.util.List;
/**
 *
 * @author igor
 */
public class DohvatiSvePaketeExample extends Examples.Example {

    public DohvatiSvePaketeExample(Retrofit retrofit) {
        super(retrofit,"DohvatiSvePakete");
    }

    @Override
    public void exampleLogic() {
        PaketRequest paketRequest = this.retrofit.create(PaketRequest.class);

        
        //Call<PaketiResponse> dohvatiPaketeCall = paketRequest.dohvatiSvePakete();
        Call<List<PaketDTO>> dohvatiPaketeCall = paketRequest.dohvatiSvePakete();

         
        try {
//            Response<PaketiResponse> response = dohvatiPaketeCall.execute();
//            System.out.println("Status: "+response.code());
//            PaketiResponse paketiResponse = response.body();
//            for(PaketDTO p: paketiResponse.getPaketi())
//                System.out.println(p.toXmlString());
            Response<List<PaketDTO>> response = dohvatiPaketeCall.execute();
            System.out.println("Status: "+response.code());
            List<PaketDTO> paketi = response.body();
            for(PaketDTO p: paketi)
                System.out.println(p.toXmlString());
  
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
