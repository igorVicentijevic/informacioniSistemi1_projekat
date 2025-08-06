/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.AudioSnimak;

import RetrofitObjects.AudioSnimakRequest;
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
public class BrisanjeAudioSnimkaExample extends Examples.Example{

    public BrisanjeAudioSnimkaExample(Retrofit retrofit) {
        super(retrofit,"BrisanjeAudioSnimka");
    }

    @Override
    public void exampleLogic() {
        AudioSnimakRequest audioSnimakRequest = this.retrofit.create(AudioSnimakRequest.class);

       
        int idK = 1;
        int idSni = 1;
              
        Call<ResponseBody> brisanjeAudioSnimkaCall = audioSnimakRequest.izbrisiSnimak(idK, idSni);
        
         
        try {
            Response<ResponseBody> response =brisanjeAudioSnimkaCall.execute();
            System.out.println("Status: "+response.code());
            if(response.body()!= null)
                System.out.println(response.body().string());
            
        } catch (IOException ex) {
            Logger.getLogger(Main.Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
