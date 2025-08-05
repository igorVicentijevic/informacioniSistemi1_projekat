/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

import ListAccepters.KorisniciResponse;
import ListAccepters.MestaResponse;
import Main.Main;
import RetrofitObjects.KorisnikRequest;
import RetrofitObjects.MestoRequest;
import SharedLibrary.KorisnikDTO;
import SharedLibrary.MestoDTO;
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
public class DohvatiMestaExample extends Example{

    public DohvatiMestaExample(Retrofit retrofit) {
        super(retrofit,"dohvatiSvaMesta");
    }

    @Override
    public void exampleLogic() {
        MestoRequest mestoRequest = this.retrofit.create(MestoRequest.class);

        
        Call<MestaResponse> dohvatiMestaCall = mestoRequest.dohvatiSvaMesta();
        
         
        try {
            Response<MestaResponse> response = dohvatiMestaCall.execute();
            System.out.println("Status: "+response.code());
            MestaResponse mestaResponse = response.body();
            for(MestoDTO m: mestaResponse.getMesta())
                System.out.println(m.toXmlString());
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
