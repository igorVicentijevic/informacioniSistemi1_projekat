/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RetrofitObjects;

import ListAccepters.KategorijeResponse;
import SharedLibrary.RequestDTO;
import SharedLibraryPodsistem2.KategorijaDTO;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 *
 * @author igor
 */
public interface KategorijaRequest {
    @POST
    public Call<ResponseBody> kreirajKategoriju(KategorijaDTO kategorija);
    
    @GET
    public Call<KategorijeResponse> dohvatiSveKategorije();
}
