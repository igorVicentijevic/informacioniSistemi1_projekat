package Examples.Korisnik;


import Main.Main;
import RetrofitObjects.KorisnikRequest;
import SharedLibrary.KorisnikDTO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author igor
 */
public class DodajAudioSnimakUOmiljeneExample extends Examples.Example{

    public DodajAudioSnimakUOmiljeneExample(Retrofit retrofit) {
        super(retrofit,"DodajAudioSnimakUOmiljene");
    }

    @Override
    public void exampleLogic() {
        KorisnikRequest korisnikRequest = this.retrofit.create(KorisnikRequest.class);

        int idK = 1;
        int idSni = 1;
        
        Call<ResponseBody> dodajUOmiljeneCall = korisnikRequest.dodajSnimakUOmiljene(idK, idSni);
        
         
        try {
            Response<ResponseBody> response = dodajUOmiljeneCall.execute();
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
