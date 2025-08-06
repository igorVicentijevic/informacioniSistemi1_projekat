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
public class PromenaNazivaAudioSnimkaExample extends Examples.Example{

    public PromenaNazivaAudioSnimkaExample(Retrofit retrofit) {
        super(retrofit,"PromenaNazivaAudioSnimka");
    }

    

    @Override
    public void exampleLogic() {
       
        AudioSnimakRequest audioSnimakRequest = this.retrofit.create(AudioSnimakRequest.class);

       
        int idSni = 1;
        String noviNaziv = "NoviNaziv";
              
        Call<ResponseBody> promeniNazivaAudioSnimkaCall = audioSnimakRequest.promeniNaziv(idSni, noviNaziv);
        
         
        try {
            Response<ResponseBody> response =promeniNazivaAudioSnimkaCall.execute();
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
