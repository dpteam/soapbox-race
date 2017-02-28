package br.com.soapboxrace.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfstringType", propOrder = { "string" })
@XmlRootElement(name = "ArrayOfstring")
public class ArrayOfstringType {
	@XmlElement(required = true)
	protected List<String> string;

	public List<String> getString() {
		return string;
	}

	public void setString(String value) {
		this.string = Collections.singletonList(value);
	}

	public void setString(List<String> string) {
		this.string = string;
	}
	
	public void add(String string) {
		if (this.string == null)
			this.string = new ArrayList<>();
		this.string.add(string);
	}
}