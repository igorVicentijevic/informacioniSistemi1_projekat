/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RetrofitObjects;

import SharedLibraryPodsistem2.AudioSnimakDTO;
import SharedLibraryPodsistem2.KategorijaDTO;
import SharedLibraryPodsistem3.SlusaDTO;
import java.util.List;
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
    public Call<Void> dodajSlusanje(SlusaDTO slusaDTO);
  
    
    
    @GET("slusa/{idSni}")
    public Call<List<SlusaDTO>> dohvatiSvaSlusanjaZaAudioSnimak(@Path("idSni") int idSni);
    
    
    @GET
    public Call<List<AudioSnimakDTO>> dohvatiSveAudioSnimke();
    
    
    @GET("kategorija/{idSni}")
    public Call<KategorijaDTO> dohvatiMojeKategorije(@Path("idSni") int idSni);
    
    @POST
    public Call<Void> kreirajAudioSnimak(AudioSnimakDTO audioSnimakDTO);
    
   
    @PUT("naziv/{idSni}/{noviNaziv}")
    public Call<Void> promeniNaziv(@Path("idSni") int idSni,@Path("noviNaziv") String noviNaziv);
    
   
    @PUT("kategorija/{idSni}/{idKat}")
    public Call<Void> dodajKategoriju(@Path("idSni") int idSni, @Path("idKat") int idKat);
    
    @DELETE("/{idK}/{idSni}")
    public Call<Void> izbrisiSnimak(@Path("idK") int idK, @Path("idSni") int idSni);
}
