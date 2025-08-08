/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SharedLibraryPodsistem3;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author igor
 */
@XmlRootElement
public class SlusaDTO implements Serializable{

    public Integer idS;
    public Integer idK;
    public Integer idSni;
    public Date datumVremePocetkaSlusanja;
    public int sekundaOdKojeJePoceloSlusanje;
    public int odslusanoUSekundama;
   public String toXmlString() throws Exception {
        JAXBContext context = JAXBContext.newInstance(this.getClass());
        Marshaller marshaller = context.createMarshaller();

        // Pretty print
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter writer = new StringWriter();
        marshaller.marshal(this, writer);

        return writer.toString();
     }
}
