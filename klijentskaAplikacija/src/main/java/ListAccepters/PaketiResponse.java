/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListAccepters;

import SharedLibrary.MestoDTO;
import SharedLibraryPodsistem3.PaketDTO;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author igor
 */
@XmlRootElement(name = "PaketDTOes")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaketiResponse {

    @XmlElement(name = "paketDTO")
    private List<PaketDTO> paketi;

    public List<PaketDTO> getPaketi() {
        return paketi;
    }

    public void setPaketi(List<PaketDTO> paketi) {
        this.paketi = paketi;
    }
}
