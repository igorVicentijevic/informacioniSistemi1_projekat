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
public class IzbrisiOcenuExample extends Examples.Example{

    public IzbrisiOcenuExample(Retrofit retrofit) {
        super(retrofit,"IzbrisiOcenu");
    }

    
    @Override
    public void exampleLogic() {
        OcenaRequest ocenaRequest = this.retrofit.create(OcenaRequest.class);

        //OcenaDTO ocenaToDelete = new OcenaDTO();
        int idOce = 1;
        
        Call<ResponseBody> izbrisiOcenuCall = ocenaRequest.izbrisiOcenu(idOce);
        
         
        try {
            Response<ResponseBody> response = izbrisiOcenuCall.execute();
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
