/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListAccepters;

import SharedLibrary.KorisnikDTO;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlRootElement(name = "korisnikDTOes")
@XmlAccessorType(XmlAccessType.FIELD)
public class KorisniciResponse {

    @XmlElement(name = "korisnikDTO")
    private List<KorisnikDTO> korisnici;

    public List<KorisnikDTO> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<KorisnikDTO> korisnici) {
        this.korisnici = korisnici;
    }
}