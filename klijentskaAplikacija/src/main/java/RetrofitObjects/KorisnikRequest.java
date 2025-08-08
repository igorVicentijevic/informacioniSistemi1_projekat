/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RetrofitObjects;

import ListAccepters.AudioSnimciResponse;
import ListAccepters.KorisniciResponse;
import SharedLibrary.KorisnikDTO;
import SharedLibraryPodsistem2.AudioSnimakDTO;
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
public interface KorisnikRequest {
    @POST("korisnik")
    public Call<ResponseBody> kreirajKorisnika(@Body KorisnikDTO korisnik);
    
    
    @POST("korisnik/omiljeni/{idK}/{idSni}")
    public Call<ResponseBody> dodajSnimakUOmiljene(@Path("idK") int idK, @Path("idSni") int idSni);

    
    @PUT("korisnik/email/{idK}/{newEmail}")
    public Call<ResponseBody> promeniEmail(@Path("idK") int idK,@Path("newEmail") String newEmail);
    
    @GET("korisnik/omiljeni/{idK}")
    public Call<AudioSnimciResponse> dohvatiOmiljeneZaKorisnika(@Path("idK") int idK);
    
    @PUT("korisnik/mesto/{idK}/{naziv}")
    public Call<ResponseBody> promeniMesto(@Path("idK") int idK,@Path("naziv") String naziv);
    
    @GET("korisnik")
    public Call<KorisniciResponse> dohvatiSveKorisnike();
}
