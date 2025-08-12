/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RetrofitObjects;

import ListAccepters.PretplateResponse;
import SharedLibrary.RequestDTO;
import SharedLibraryPodsistem3.PretplataDTO;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 *
 * @author igor
 */
public interface PretplataRequest {
    @POST("pretplata")
    public Call<ResponseBody> kreirajPretplatu(@Body PretplataDTO pretplataDTO);
    
//    @GET("{idK}")
//    public Call<PretplateResponse> dohvatiSvePretplateZaKorisnika(@Path("idK") int idK);
    @GET("pretplata/{idK}")
    public Call<List<PretplataDTO>> dohvatiSvePretplateZaKorisnika(@Path("idK") int idK);
    
    @PUT("pretplata/mesecneCene/{idPak}")
    public Call<ResponseBody> dodajKategoriju(@Path("idPak") int idPak);
}
