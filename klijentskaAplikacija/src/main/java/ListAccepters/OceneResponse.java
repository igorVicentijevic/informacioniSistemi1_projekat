/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListAccepters;

import SharedLibraryPodsistem3.OcenaDTO;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
/**
 *
 * @author igor
 */
@XmlRootElement(name = "ocenaDTOes")
@XmlAccessorType(XmlAccessType.FIELD)
public class OceneResponse {

    @XmlElement(name = "ocenaDTO")
    private List<OcenaDTO> ocene;

    public List<OcenaDTO> getOcene() {
        return ocene;
    }

    public void setAudioSnimci(List<OcenaDTO> audioSnimci) {
        this.ocene = ocene;
    }
}
