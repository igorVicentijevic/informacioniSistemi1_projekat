/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RetrofitObjects;

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
    public Call<Void> kreirajKorisnika(@Body KorisnikDTO korisnik);
    
    
    @POST("korisnik/omiljeni/{idK}/{idSni}")
    public Call<Void> dodajSnimakUOmiljene(@Path("idK") int idK, @Path("idSni") int idSni);

    
    @PUT("korisnik/email/{idK}/{newEmail}")
    public Call<Void> promeniEmail(@Path("idK") int idK,@Path("newEmail") String newEmail);
    
    @GET("korisnik/omiljeni/{idK}")
    public Call<List<AudioSnimakDTO>> dohvatiOmiljeneZaKorisnika(@Path("idK") int idK);
    
    @PUT("korisnik/mesto/{idK}/{naziv}")
    public Call<Void> promeniMesto(@Path("idK") int idK,@Path("naziv") String naziv);
    
    @GET("korisnik")
    public Call<KorisniciResponse> dohvatiSveKorisnike();
}
