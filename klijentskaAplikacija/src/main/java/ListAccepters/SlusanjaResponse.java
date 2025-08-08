/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListAccepters;

import SharedLibraryPodsistem2.AudioSnimakDTO;
import SharedLibraryPodsistem3.SlusaDTO;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author igor
 */
@XmlRootElement(name = "slusaDTOes")
@XmlAccessorType(XmlAccessType.FIELD)
public class SlusanjaResponse {

    @XmlElement(name = "slusaDTO")
    private List<SlusaDTO> slusanja;

    public List<SlusaDTO> getSlusanja() {
        return slusanja;
    }

    public void setSlusanja(List<SlusaDTO> slusanja) {
        this.slusanja = slusanja;
    }
}
