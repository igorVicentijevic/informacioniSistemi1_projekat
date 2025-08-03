/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RetrofitObjects;

import SharedLibrary.RequestDTO;
import SharedLibraryPodsistem3.PaketDTO;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 *
 * @author igor
 */
public interface PaketRequest {
    
    @GET
    public Call<List<PaketDTO>> dohvatiSvePakete();
    @POST
    public Call<Void> kreirajPaket(PaketDTO paketDTO);
    
    @PUT("mesecneCene/{idPak}/{cena}")
    public Call<Void> promeniCenu(@Path("idPak") int idPak,@Path("cena") float cena);
}
