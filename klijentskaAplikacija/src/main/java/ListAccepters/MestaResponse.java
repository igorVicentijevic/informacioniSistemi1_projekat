/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListAccepters;

import SharedLibrary.MestoDTO;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
/**
 *
 * @author igor
 */
@XmlRootElement(name = "mestoDTOes")
@XmlAccessorType(XmlAccessType.FIELD)
public class MestaResponse {

    @XmlElement(name = "mestoDTO")
    private List<MestoDTO> mesta;

    public List<MestoDTO> getMesta() {
        return mesta;
    }

    public void setKorisnici(List<MestoDTO> mesta) {
        this.mesta = mesta;
    }
}
