/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples.Kategorija;

import ListAccepters.KategorijeResponse;
import ListAccepters.MestaResponse;
import Main.Main;
import RetrofitObjects.KategorijaRequest;
import RetrofitObjects.MestoRequest;
import SharedLibrary.MestoDTO;
import SharedLibraryPodsistem2.KategorijaDTO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.util.List;
/**
 *
 * @author igor
 */
public class DohvatanjeSvihKategorijaExample extends Examples.Example {

    public DohvatanjeSvihKategorijaExample(Retrofit retrofit) {
        super(retrofit,"Dohvatanje svih kategorija");
    }

    @Override
    public void exampleLogic() {
        KategorijaRequest kategorijaRequest = this.retrofit.create(KategorijaRequest.class);
        
        Call<List<KategorijaDTO>> dohvatiKategorijeCall = kategorijaRequest.dohvatiSveKategorije();
        
         
        try {
            Response<List<KategorijaDTO>> response = dohvatiKategorijeCall.execute();
            System.out.println("Status: "+response.code());
            List<KategorijaDTO> kategorije = response.body();
            for(KategorijaDTO k:kategorije)
                System.out.println(k.toXmlString());
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        Call<KategorijeResponse> dohvatiKategorijeCall = kategorijaRequest.dohvatiSveKategorije();
//        
//         
//        try {
//            Response<KategorijeResponse> response = dohvatiKategorijeCall.execute();
//            System.out.println("Status: "+response.code());
//            KategorijeResponse kategorijeResponse = response.body();
//            for(KategorijaDTO k:kategorijeResponse.getKategorije())
//                System.out.println(k.toXmlString());
//            
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
