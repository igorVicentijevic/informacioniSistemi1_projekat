/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Ocena;

import Main.Main;
import RetrofitObjects.KorisnikRequest;
import RetrofitObjects.OcenaRequest;
import SharedLibrary.KorisnikDTO;
import SharedLibrary.MestoDTO;
import SharedLibraryPodsistem3.OcenaDTO;
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
public class KreirajOcenuExample extends Examples.Example{

    public KreirajOcenuExample(Retrofit retrofit) {
        super(retrofit,"KreirajOcenu");
    }

    private OcenaDTO kreirajOcenuDTO(){
            OcenaDTO toCreate = new OcenaDTO();
            toCreate.datumVremeDavanjaOcena = new Date();
            toCreate.idK = 1;
            toCreate.idSni=1;
            toCreate.ocena = 2;
            return toCreate;
    }
    

    @Override
    public void exampleLogic() {
        OcenaRequest ocenaRequest = this.retrofit.create(OcenaRequest.class);

        OcenaDTO toCreate = kreirajOcenuDTO();
        Call<ResponseBody> kreirajOcenuCall = ocenaRequest.dodajOcenu(toCreate);
        
         
        try {
            Response<ResponseBody> response = kreirajOcenuCall.execute();
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
