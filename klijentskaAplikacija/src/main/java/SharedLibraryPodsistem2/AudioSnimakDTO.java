/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SharedLibraryPodsistem2;

import SharedLibrary.KorisnikDTO;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author igor
 */
@XmlRootElement
public class AudioSnimakDTO implements Serializable {
   
    public Integer idSni;
    
    public Date vremePostavljanja;
    
    public Date trajanje;
    
    public String naziv;
    
    public KorisnikDTO vlasnik;

}
