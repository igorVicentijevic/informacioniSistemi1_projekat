/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SharedLibraryPodsistem3;

import SharedLibrary.KorisnikDTO;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@XmlRootElement
public class PretplataDTO implements Serializable{
    public Integer idPre;

    
    public KorisnikDTO korisnik;

    
    public PaketDTO paket;

    public Date datumVremePretplate;
}
