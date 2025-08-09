/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RetrofitObjects;

import ListAccepters.PaketiResponse;
import SharedLibrary.RequestDTO;
import SharedLibraryPodsistem3.PaketDTO;
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
public interface PaketRequest {
    
//    @GET("paket")
//    public Call<PaketiResponse> dohvatiSvePakete();
    @GET("paket")
    public Call<List<PaketDTO>> dohvatiSvePakete();
    @POST("paket")
    public Call<ResponseBody> kreirajPaket(@Body PaketDTO paketDTO);
    
    @PUT("paket/mesecneCene/{idPak}/{cena}")
    public Call<ResponseBody> promeniCenu(@Path("idPak") int idPak,@Path("cena") float cena);
}
