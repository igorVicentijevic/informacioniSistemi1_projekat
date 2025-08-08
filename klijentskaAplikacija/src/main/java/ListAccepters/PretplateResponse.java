/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListAccepters;

import SharedLibraryPodsistem3.PaketDTO;
import SharedLibraryPodsistem3.PretplataDTO;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author igor
 */
@XmlRootElement(name = "PretplatDTOes")
@XmlAccessorType(XmlAccessType.FIELD)
public class PretplateResponse {

    @XmlElement(name = "pretplataDTO")
    private List<PretplataDTO> pretplate;

    public List<PretplataDTO> getPretplata() {
        return pretplate;
    }

    public void setPretplate(List<PretplataDTO> pretplate) {
        this.pretplate = pretplate;
    }
}