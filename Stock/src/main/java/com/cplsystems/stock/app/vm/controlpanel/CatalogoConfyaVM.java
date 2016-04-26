package com.cplsystems.stock.app.vm.controlpanel;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.cplsystems.stock.app.vm.controlpanel.utils.ControlPanelMetaclass;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class CatalogoConfyaVM extends ControlPanelMetaclass {
	private static final long serialVersionUID = -8141487067470696501L;

	@Init
	public void init() {
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		
	}

	private static SOAPMessage createSoapRequest() throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
		soapEnvelope.addNamespaceDeclaration("end", "http://endpoint.concretepage.com/");
		SOAPBody soapBody = soapEnvelope.getBody();
		SOAPElement soapElement = soapBody.addChildElement("getWelcomeMsg", "end");
		SOAPElement element1 = soapElement.addChildElement("arg0");
		element1.addTextNode("EveryOne");
		soapMessage.saveChanges();
		System.out.println("----------SOAP Request------------");
		soapMessage.writeTo(System.out);
		return soapMessage;
	}

	private static void createSoapResponse(SOAPMessage soapResponse) throws Exception {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		System.out.println("\n----------SOAP Response-----------");
		StreamResult result = new StreamResult(System.out);
		transformer.transform(sourceContent, result);
	}
	// ***************************************************
	
}
