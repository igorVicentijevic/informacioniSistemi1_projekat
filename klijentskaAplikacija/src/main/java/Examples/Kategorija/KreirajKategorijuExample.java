/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Kategorija;

import RetrofitObjects.KategorijaRequest;
import SharedLibraryPodsistem2.KategorijaDTO;
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
public class KreirajKategorijuExample extends Examples.Example{

    public KreirajKategorijuExample(Retrofit retrofit) {
        super(retrofit,"KreirajKategoriju");
    }

    private KategorijaDTO createKategorijaDTO(){
        KategorijaDTO kategorijaDTO = new KategorijaDTO();
        
        kategorijaDTO.naziv="Komedija";
        return kategorijaDTO;
    }
    
    @Override
    public void exampleLogic() {
        KategorijaRequest kategorijaRequest = this.retrofit.create(KategorijaRequest.class);

        KategorijaDTO toCreate = createKategorijaDTO();
        Call<ResponseBody> kreirajKategorijuCall = kategorijaRequest.kreirajKategoriju(toCreate);
        
         
        try {
            Response<ResponseBody> response = kreirajKategorijuCall.execute();
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
