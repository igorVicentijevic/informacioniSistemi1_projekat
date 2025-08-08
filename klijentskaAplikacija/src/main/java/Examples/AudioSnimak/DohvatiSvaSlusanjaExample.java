/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.AudioSnimak;

import ListAccepters.SlusanjaResponse;
import RetrofitObjects.AudioSnimakRequest;
import SharedLibraryPodsistem3.SlusaDTO;
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
public class DohvatiSvaSlusanjaExample extends Examples.Example{

    public DohvatiSvaSlusanjaExample(Retrofit retrofit) {
        super(retrofit,"DohvatiSvaSlusanja");
    }

    @Override
    public void exampleLogic() {
        AudioSnimakRequest audioSnimakRequest = this.retrofit.create(AudioSnimakRequest.class);

        int idSni = 1;
        
        Call<SlusanjaResponse> dohvatiSlusanjaCall = audioSnimakRequest.dohvatiSvaSlusanjaZaAudioSnimak(idSni);
        
         
        try {
            Response<SlusanjaResponse> response = dohvatiSlusanjaCall.execute();
            System.out.println("Status: "+response.code());
            SlusanjaResponse slusanjaResponse = response.body();
            for(SlusaDTO s: slusanjaResponse.getSlusanja())
                System.out.println(s.toXmlString());
            
        } catch (IOException ex) {
            Logger.getLogger(Main.Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
