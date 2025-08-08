package Examples.Pretplata;


import Main.Main;
import RetrofitObjects.PaketRequest;
import RetrofitObjects.PretplataRequest;
import SharedLibrary.KorisnikDTO;
import SharedLibraryPodsistem3.PaketDTO;
import SharedLibraryPodsistem3.PretplataDTO;
import java.io.IOException;
import java.util.Date;
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
public class KreirajPretplatuKorisnikaNaPaketExample extends Examples.Example{

    public KreirajPretplatuKorisnikaNaPaketExample(Retrofit retrofit) {
        super(retrofit,"KreirajPretplatuKorisnikaNaPaket");
    }
    
    public PretplataDTO kreirajPretplataDTO(){
        PretplataDTO pretplataDTO = new PretplataDTO();
        pretplataDTO.datumVremePretplate = new Date();
        pretplataDTO.korisnik = new KorisnikDTO();
        pretplataDTO.korisnik.idK = 1;
        pretplataDTO.paket = new PaketDTO();
        pretplataDTO.paket.idPak = 1;
        return pretplataDTO;
    }

    @Override
    public void exampleLogic() {
        PretplataRequest pretplataRequest = this.retrofit.create(PretplataRequest.class);

        PretplataDTO toCreate = kreirajPretplataDTO();
              
        Call<ResponseBody> kreirajPretplatuCall = pretplataRequest.kreirajPretplatu(toCreate);
         
        try {
            Response<ResponseBody> response = kreirajPretplatuCall.execute();
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
