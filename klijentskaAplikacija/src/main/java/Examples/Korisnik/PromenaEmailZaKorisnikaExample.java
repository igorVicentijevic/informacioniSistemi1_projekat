/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Korisnik;

import Examples.Example;
import Main.Main;
import RetrofitObjects.KorisnikRequest;
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
public class PromenaEmailZaKorisnikaExample extends Example{

    public PromenaEmailZaKorisnikaExample(Retrofit retrofit) {
        super(retrofit,"PromenaEmailaZaKorisnika");
    }


//    private KorisnikDTO createNewEmailKorisnikDTO(){
//            KorisnikDTO toChangeEmail = new KorisnikDTO();
//            toChangeEmail.idK = 12;
//            toChangeEmail.email = "jasamGde34@jasam";
//            
//            return toChangeEmail;
//    }
    

    @Override
    public void exampleLogic() {
       
        KorisnikRequest korisnikRequest = this.retrofit.create(KorisnikRequest.class);

        //KorisnikDTO toChangeEmail = createNewEmailKorisnikDTO();
              
        Call<ResponseBody> promeniEmailKorisnikaCall = korisnikRequest.promeniEmail(12, "jasamde34@jasam");
        
         
        try {
            Response<ResponseBody> response =promeniEmailKorisnikaCall.execute();
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
