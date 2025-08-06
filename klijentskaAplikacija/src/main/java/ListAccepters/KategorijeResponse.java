/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListAccepters;

import SharedLibraryPodsistem2.KategorijaDTO;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
/**
 *
 * @author igor
 */
@XmlRootElement(name = "kategorijaDTOes")
@XmlAccessorType(XmlAccessType.FIELD)
public class KategorijeResponse {

    @XmlElement(name = "kategorijaDTO")
    private List<KategorijaDTO> kategorije;

    public List<KategorijaDTO> getKategorije() {
        return kategorije;
    }

    public void setKategorije(List<KategorijaDTO> kategorije) {
        this.kategorije = kategorije;
    }
}