/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListAccepters;

import SharedLibraryPodsistem2.AudioSnimakDTO;
import SharedLibraryPodsistem2.AudioSnimakDTO;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author igor
 */
@XmlRootElement(name = "audioSnimakDTOes")
@XmlAccessorType(XmlAccessType.FIELD)
public class AudioSnimciResponse {

    @XmlElement(name = "audioSnimakDTO")
    private List<AudioSnimakDTO> audioSnimci;

    public List<AudioSnimakDTO> getAudioSnimci() {
        return audioSnimci;
    }

    public void setAudioSnimci(List<AudioSnimakDTO> audioSnimci) {
        this.audioSnimci = audioSnimci;
    }
}