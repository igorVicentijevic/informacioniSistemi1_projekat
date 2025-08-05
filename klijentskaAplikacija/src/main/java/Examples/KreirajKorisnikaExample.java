/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

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
public class KreirajKorisnikaExample extends Example {

    public KreirajKorisnikaExample(Retrofit retrofit) {
        super(retrofit,"kreirajKorisnika");
    }
    
    private KorisnikDTO createKorisnikDTO(){
            KorisnikDTO toCreate = new KorisnikDTO();
            toCreate.email = "jasamGde3@jasam";
            toCreate.godiste=2001;
            toCreate.ime = "NisamGde3";
            toCreate.pol = "M";
            MestoDTO mesto = new MestoDTO();
            mesto.naziv = "Bor";
            toCreate.mesto = mesto;
            return toCreate;
    }
    

    @Override
    public void exampleLogic() {
        KorisnikRequest korisnikRequest = this.retrofit.create(KorisnikRequest.class);

        KorisnikDTO toCreate = createKorisnikDTO();
        Call<ResponseBody> kreirajKorisnikaCall = korisnikRequest.kreirajKorisnika(toCreate);
        
         
        try {
            Response<ResponseBody> response = kreirajKorisnikaCall.execute();
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
