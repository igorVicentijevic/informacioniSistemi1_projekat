/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.AudioSnimak;

import ListAccepters.AudioSnimciResponse;
import Main.Main;
import RetrofitObjects.AudioSnimakRequest;
import SharedLibraryPodsistem2.AudioSnimakDTO;
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
public class DohvatanjeSvihAudioSnimkaExample extends Examples.Example {

    public DohvatanjeSvihAudioSnimkaExample(Retrofit retrofit) {
        super(retrofit,"DohvatanjeSvihAudioSnimka");
    }

    @Override
    public void exampleLogic() {
       AudioSnimakRequest audioSnimakRequest = this.retrofit.create(AudioSnimakRequest.class);

        
        Call<AudioSnimciResponse> dohvatiKategorijeCall = audioSnimakRequest.dohvatiSveAudioSnimke();
        
         
        try {
            Response<AudioSnimciResponse> response = dohvatiKategorijeCall.execute();
            System.out.println("Status: "+response.code());
            AudioSnimciResponse audioSnimakResponse = response.body();
            for(AudioSnimakDTO a:audioSnimakResponse.getAudioSnimci())
                System.out.println(a.toXmlString());
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
