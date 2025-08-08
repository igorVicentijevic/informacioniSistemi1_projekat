/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.AudioSnimak;

import RetrofitObjects.AudioSnimakRequest;
import SharedLibrary.KorisnikDTO;
import SharedLibraryPodsistem2.AudioSnimakDTO;
import SharedLibraryPodsistem3.SlusaDTO;
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
public class KreirajSlusanjeExample extends Examples.Example{

    public KreirajSlusanjeExample(Retrofit retrofit) {
        super(retrofit,"KreirajSlusanje");
    }

    private SlusaDTO kreirajSlusaDTO(){
        SlusaDTO slusaDTO = new SlusaDTO();
        slusaDTO.datumVremePocetkaSlusanja = new Date();
        slusaDTO.idK = 2;
        slusaDTO.idSni = 2;
        slusaDTO.odslusanoUSekundama = 20;
        slusaDTO.sekundaOdKojeJePoceloSlusanje = 10;
        return slusaDTO;
    }
    
    @Override
    public void exampleLogic() {
        AudioSnimakRequest audioSnimakRequest = this.retrofit.create(AudioSnimakRequest.class);

        SlusaDTO toCreate = new SlusaDTO();
        
        Call<ResponseBody> kreirajSlusanjeCall = audioSnimakRequest.dodajSlusanje(toCreate);
        
         
        try {
            Response<ResponseBody> response =  kreirajSlusanjeCall.execute();
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
