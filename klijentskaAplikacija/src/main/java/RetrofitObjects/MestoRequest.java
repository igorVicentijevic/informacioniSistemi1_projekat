/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RetrofitObjects;

import SharedLibrary.MestoDTO;
import SharedLibrary.RequestDTO;
import SharedLibrary.Return;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 *
 * @author igor
 */
public interface MestoRequest {
    @POST
    public Call<Void> kreirajGrad(MestoDTO mesto);
    
    @GET
    public Call<List<MestoDTO>> dohvatiSvaMesta();
}
