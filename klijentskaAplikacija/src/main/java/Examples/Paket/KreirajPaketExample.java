/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Paket;

import Main.Main;
import RetrofitObjects.KorisnikRequest;
import RetrofitObjects.PaketRequest;
import SharedLibrary.KorisnikDTO;
import SharedLibrary.MestoDTO;
import SharedLibraryPodsistem3.PaketDTO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 *
 * @author igor
 */
public class KreirajPaketExample extends Examples.Example {
    
    public KreirajPaketExample(Retrofit retrofit) {
        super(retrofit,"kreirajPaket");
    }
    
    private PaketDTO kreirajPaketDTO(){
            PaketDTO paketDTO = new PaketDTO();
            return paketDTO;
    }
    

    @Override
    public void exampleLogic() {
        PaketRequest paketRequest = this.retrofit.create(PaketRequest.class);

        PaketDTO toCreate = kreirajPaketDTO();
        Call<ResponseBody> kreirajPaketCall = paketRequest.kreirajPaket(toCreate);
        
         
        try {
            Response<ResponseBody> response = kreirajPaketCall.execute();
            System.out.println("Status: "+response.code());
            if(response.body()!= null)
                System.out.println(response.body().string());
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
