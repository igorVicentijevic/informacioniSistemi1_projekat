/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SharedLibrary;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author igor
 */
@XmlRootElement
public class KategorijaDTO implements Serializable {

    public int idKat;
   
   public String naziv;
   public KategorijaDTO(){
       
   }

    public KategorijaDTO(int idKat, String naziv) {
        this.idKat = idKat;
        this.naziv = naziv;
    }


    
}
