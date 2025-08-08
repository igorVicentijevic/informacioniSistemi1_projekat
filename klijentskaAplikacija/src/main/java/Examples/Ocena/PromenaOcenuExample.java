/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Ocena;

import Main.Main;
import RetrofitObjects.OcenaRequest;
import SharedLibraryPodsistem3.OcenaDTO;
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
public class PromenaOcenuExample extends Examples.Example{

    public PromenaOcenuExample(Retrofit retrofit) {
        super(retrofit,"PromenaOcene");
    }

    @Override
    public void exampleLogic() {
        OcenaRequest ocenaRequest = this.retrofit.create(OcenaRequest.class);

        OcenaDTO ocenaToChange = new OcenaDTO();
        ocenaToChange.idOce = 1;
        ocenaToChange.ocena = 4;
        
        Call<ResponseBody> promeniOcenuCall = ocenaRequest.promeniOcenu(ocenaToChange);
        
         
        try {
            Response<ResponseBody> response = promeniOcenuCall.execute();
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
