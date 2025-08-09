/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SharedLibraryPodsistem3;

import JSON.CustomDateDeserializer;
import SharedLibrary.KorisnikDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
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

   @JsonDeserialize(using = CustomDateDeserializer.class)
    public Date datumVremePretplate;

    
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
