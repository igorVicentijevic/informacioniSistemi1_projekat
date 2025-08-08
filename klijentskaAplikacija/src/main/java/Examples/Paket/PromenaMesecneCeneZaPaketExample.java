/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Paket;

import Main.Main;
import RetrofitObjects.KorisnikRequest;
import RetrofitObjects.PaketRequest;
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
public class PromenaMesecneCeneZaPaketExample extends Examples.Example {

    public PromenaMesecneCeneZaPaketExample(Retrofit retrofit) {
        super(retrofit, "PromenaMesecneCeneZaPaket");
    }

    @Override
    public void exampleLogic() {
       
        PaketRequest paketRequest = this.retrofit.create(PaketRequest.class);

        int idPak = 1;
        float cena = 100;
              
        Call<ResponseBody> promenaMesecneCeneCall = paketRequest.promeniCenu(idPak,cena);
         
        try {
            Response<ResponseBody> response =promenaMesecneCeneCall.execute();
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
