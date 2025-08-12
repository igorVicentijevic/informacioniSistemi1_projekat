/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Ocena;

import Examples.Example;
import ListAccepters.OceneResponse;
import ListAccepters.PretplateResponse;
import Main.Main;
import RetrofitObjects.OcenaRequest;
import RetrofitObjects.PretplataRequest;
import SharedLibraryPodsistem3.OcenaDTO;
import SharedLibraryPodsistem3.PretplataDTO;
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
public class DohvatiOceneExample extends Example {

    public DohvatiOceneExample(Retrofit retrofit) {
        super(retrofit,"DohvatiOcene");
    }

    
    @Override
    public void exampleLogic() {
        OcenaRequest ocenaRequest = this.retrofit.create(OcenaRequest.class);

        int idSni = 1;
        
        Call<List<OcenaDTO>> dohvatiOceneCall = ocenaRequest.dohvatiSveOceneZaAudioSnimak(idSni);
        
        
         
        try {
            Response<List<OcenaDTO>> response = dohvatiOceneCall.execute();
            
            System.out.println("Status: "+response.code());
            List<OcenaDTO> ocene = response.body();
            for(OcenaDTO o: ocene)
                System.out.println(o.toXmlString());
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
//        Call<OceneResponse> dohvatiOceneCall = ocenaRequest.dohvatiSveOceneZaAudioSnimak(idSni);
//        
//        
//         
//        try {
//            Response<OceneResponse> response = dohvatiOceneCall.execute();
//            
//            System.out.println("Status: "+response.code());
//            OceneResponse oceneResponse = response.body();
//            for(OcenaDTO o: oceneResponse.getOcene())
//                System.out.println(o.toXmlString());
//            
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
