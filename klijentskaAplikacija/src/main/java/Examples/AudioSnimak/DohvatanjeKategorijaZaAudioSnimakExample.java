/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.AudioSnimak;

import Examples.Example;
import ListAccepters.AudioSnimciResponse;
import ListAccepters.KategorijeResponse;
import Main.Main;
import RetrofitObjects.AudioSnimakRequest;
import SharedLibraryPodsistem2.AudioSnimakDTO;
import SharedLibraryPodsistem2.KategorijaDTO;
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
public class DohvatanjeKategorijaZaAudioSnimakExample extends Example{

    public DohvatanjeKategorijaZaAudioSnimakExample(Retrofit retrofit) {
        super(retrofit,"DohvatanjeKategorijaZaAudioSnimak");
    }

    @Override
    public void exampleLogic() {
       AudioSnimakRequest audioSnimakRequest = this.retrofit.create(AudioSnimakRequest.class);

        int idSni = 1;
        Call<KategorijeResponse> dohvatiKategorijeCall = audioSnimakRequest.dohvatiMojeKategorije(idSni);
        
         
        try {
            Response<KategorijeResponse> response = dohvatiKategorijeCall.execute();
            System.out.println("Status: "+response.code());
            KategorijeResponse kategorijeResponse = response.body();
            for(KategorijaDTO k:kategorijeResponse.getKategorije())
                System.out.println(k.toXmlString());
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
