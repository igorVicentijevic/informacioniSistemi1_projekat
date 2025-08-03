/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RetrofitObjects;

import SharedLibrary.RequestDTO;
import SharedLibraryPodsistem3.OcenaDTO;
import SharedLibraryPodsistem3.SlusaDTO;
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
public interface OcenaRequest {
    @POST
    public Call<Void> dodajOcenu(OcenaDTO ocenaDTO);
    
    @GET("{idSni}")
    public Call<OcenaDTO> dohvatiSveOceneZaAudioSnimak(@Path("idSni") int idSni);
    
    @PUT
    public Call<Void> promeniOcenu(OcenaDTO ocenaDTO);
    
    @DELETE
    public Call<Void> izbrisiOcenu(OcenaDTO ocenaDTO);
}
