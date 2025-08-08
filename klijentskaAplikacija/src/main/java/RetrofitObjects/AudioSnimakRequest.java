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
    
    @POST("slusa")
    public Call<ResponseBody> dodajSlusanje(SlusaDTO slusaDTO);
  
    
    
    @GET("slusa/{idSni}")
    public Call<SlusanjaResponse> dohvatiSvaSlusanjaZaAudioSnimak(@Path("idSni") int idSni);
    
    
    @GET
    public Call<AudioSnimciResponse> dohvatiSveAudioSnimke();
    
    
    @GET("kategorija/{idSni}")
    public Call<KategorijeResponse> dohvatiMojeKategorije(@Path("idSni") int idSni);
    
    @POST
    public Call<ResponseBody> kreirajAudioSnimak(AudioSnimakDTO audioSnimakDTO);
    
   
    @PUT("naziv/{idSni}/{noviNaziv}")
    public Call<ResponseBody> promeniNaziv(@Path("idSni") int idSni,@Path("noviNaziv") String noviNaziv);
    
   
    @PUT("kategorija/{idSni}/{idKat}")
    public Call<ResponseBody> dodajKategoriju(@Path("idSni") int idSni, @Path("idKat") int idKat);
    
    @DELETE("/{idK}/{idSni}")
    public Call<ResponseBody> izbrisiSnimak(@Path("idK") int idK, @Path("idSni") int idSni);
}
