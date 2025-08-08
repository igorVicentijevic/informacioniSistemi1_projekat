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
    public Call<ResponseBody> dodajOcenu(OcenaDTO ocenaDTO);
    
    @GET("{idSni}")
    public Call<OceneResponse> dohvatiSveOceneZaAudioSnimak(@Path("idSni") int idSni);
    
    @PUT
    public Call<ResponseBody> promeniOcenu(OcenaDTO ocenaDTO);
    
    @DELETE
    public Call<ResponseBody> izbrisiOcenu(OcenaDTO ocenaDTO);
}
