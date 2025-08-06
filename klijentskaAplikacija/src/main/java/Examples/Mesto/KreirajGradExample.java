/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Mesto;

import Examples.Example;
import Main.Main;
import RetrofitObjects.KorisnikRequest;
import RetrofitObjects.MestoRequest;
import SharedLibrary.KorisnikDTO;
import SharedLibrary.MestoDTO;
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
public class KreirajGradExample extends Example {

    public KreirajGradExample(Retrofit retrofit) {
        super(retrofit,"kreirajGrad");
    }
    private MestoDTO createMestoDTO(){
        MestoDTO mestoDTO = new MestoDTO();
        mestoDTO.naziv = "Moskva";
        return mestoDTO;
    }   
    
    @Override
    public void exampleLogic() {
        MestoRequest mestoRequest = this.retrofit.create(MestoRequest.class);

        MestoDTO toCreate = createMestoDTO();
        Call<ResponseBody> kreirajMestoCall = mestoRequest.kreirajGrad(toCreate);
        
         
        try {
            Response<ResponseBody> response = kreirajMestoCall.execute();
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
