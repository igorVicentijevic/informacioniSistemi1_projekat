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
public class DodavanjeKategorijeAudioSnimkuExample extends Examples.Example{

    public DodavanjeKategorijeAudioSnimkuExample(Retrofit retrofit) {
        super(retrofit,"DodavanjeKategorijeAudioSnimku");
    }

    @Override
    public void exampleLogic() {
        AudioSnimakRequest audioSnimakRequest = this.retrofit.create(AudioSnimakRequest.class);

       
        int idSni = 1;
        int idKat = 1;
              
        Call<ResponseBody> dodavanjeKategorijeAudioSnimkuCall = audioSnimakRequest.dodajKategoriju(idSni, idKat);
        
         
        try {
            Response<ResponseBody> response =dodavanjeKategorijeAudioSnimkuCall.execute();
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
