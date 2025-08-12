/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RetrofitObjects;

import ListAccepters.AudioSnimciResponse;
import ListAccepters.KategorijeResponse;
import ListAccepters.SlusanjaResponse;
import SharedLibraryPodsistem2.AudioSnimakDTO;
import SharedLibraryPodsistem2.KategorijaDTO;
import SharedLibraryPodsistem3.SlusaDTO;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 *
 * @author igor
 */
public interface AudioSnimakRequest {
    
    @POST("audioSnimak/slusa")
    public Call<ResponseBody> dodajSlusanje(@Body SlusaDTO slusaDTO);
  
    
    
    @GET("audioSnimak/slusa/{idSni}")
    public Call<List<SlusaDTO>> dohvatiSvaSlusanjaZaAudioSnimak(@Path("idSni") int idSni);
    
    
    @GET("audioSnimak")
    public Call<List<AudioSnimakDTO>> dohvatiSveAudioSnimke();
    
    
    @GET("audioSnimak/kategorija/{idSni}")
    public Call<List<KategorijaDTO>> dohvatiMojeKategorije(@Path("idSni") int idSni);
    
    @POST("audioSnimak")
    public Call<ResponseBody> kreirajAudioSnimak(@Body AudioSnimakDTO audioSnimakDTO);
    
   
    @PUT("audioSnimak/naziv/{idSni}/{noviNaziv}")
    public Call<ResponseBody> promeniNaziv(@Path("idSni") int idSni,@Path("noviNaziv") String noviNaziv);
    
   
    @PUT("audioSnimak/kategorija/{idSni}/{idKat}")
    public Call<ResponseBody> dodajKategoriju(@Path("idSni") int idSni, @Path("idKat") int idKat);
    
    @DELETE("audioSnimak/{idK}/{idSni}")
    public Call<ResponseBody> izbrisiSnimak(@Path("idK") int idK, @Path("idSni") int idSni);
}
