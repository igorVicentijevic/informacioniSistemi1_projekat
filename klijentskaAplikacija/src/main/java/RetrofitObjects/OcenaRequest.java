/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RetrofitObjects;

import ListAccepters.OceneResponse;
import SharedLibrary.RequestDTO;
import SharedLibraryPodsistem3.OcenaDTO;
import SharedLibraryPodsistem3.SlusaDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import java.util.List;
/**
 *
 * @author igor
 */
public interface OcenaRequest {
    @POST("ocena")
    public Call<ResponseBody> dodajOcenu(@Body OcenaDTO ocenaDTO);
    
    @GET("ocena/{idSni}")
    public Call<List<OcenaDTO>> dohvatiSveOceneZaAudioSnimak(@Path("idSni") int idSni);
    
    @PUT("ocena")
    public Call<ResponseBody> promeniOcenu(@Body OcenaDTO ocenaDTO);
    
    @DELETE("ocena/{idO}")
    public Call<ResponseBody> izbrisiOcenu(@Path("idO") int idO);
}
