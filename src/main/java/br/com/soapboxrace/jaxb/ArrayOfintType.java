package br.com.soapboxrace.jaxb;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfintType", propOrder = {"_int"})
@XmlRootElement(name = "ArrayOfint")
public class ArrayOfintType
{
    @XmlElement(required = true)
    private List<Integer> _int;

    public List<Integer> getInt()
    {
        return _int;
    }

    public void setInt(List<Integer> _int)
    {
        this._int = _int;
    }
}
