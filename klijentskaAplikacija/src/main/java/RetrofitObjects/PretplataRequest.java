/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RetrofitObjects;

import SharedLibrary.RequestDTO;
import SharedLibraryPodsistem3.PretplataDTO;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 *
 * @author igor
 */
public interface PretplataRequest {
    @POST
    public Call<Void> kreirajPretplatu(PretplataDTO pretplataDTO);
    
    @GET("{idK}")
    public Call<List<PretplataDTO>> dohvatiSvePretplateZaKorisnika(@Path("idK") int idK);
    
    @PUT("mesecneCene/{idPak}")
    public Call<Void> dodajKategoriju(@Path("idPak") int idPak);
}
