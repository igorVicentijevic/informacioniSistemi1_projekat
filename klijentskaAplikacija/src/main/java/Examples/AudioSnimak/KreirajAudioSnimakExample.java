/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.AudioSnimak;

import RetrofitObjects.AudioSnimakRequest;
import RetrofitObjects.KategorijaRequest;
import SharedLibrary.KorisnikDTO;
import SharedLibraryPodsistem2.AudioSnimakDTO;
import SharedLibraryPodsistem2.KategorijaDTO;
import java.io.IOException;
import java.util.Date;
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
public class KreirajAudioSnimakExample extends Examples.Example {

    public KreirajAudioSnimakExample(Retrofit retrofit) {
        super(retrofit,"KreirajAudioSnimak");
    }

    private AudioSnimakDTO createAudioSnimakDTO(){
        AudioSnimakDTO audioSnimakDTO = new AudioSnimakDTO();
        audioSnimakDTO.naziv = "IS1 snimak";
        audioSnimakDTO.trajanje = new Date();
        audioSnimakDTO.vremePostavljanja = new Date();
        KorisnikDTO vlasnik = new KorisnikDTO();
        vlasnik.idK = 11;
        audioSnimakDTO.vlasnik = vlasnik;
        
        
        return audioSnimakDTO;
    }
    
    @Override
    public void exampleLogic() {
        AudioSnimakRequest audioSnimakRequest = this.retrofit.create(AudioSnimakRequest.class);

        AudioSnimakDTO toCreate = createAudioSnimakDTO();
        Call<ResponseBody> kreirajAudioSnimakCall = audioSnimakRequest.kreirajAudioSnimak(toCreate);
        
         
        try {
            Response<ResponseBody> response = kreirajAudioSnimakCall.execute();
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
