package de.spring.webservices.binders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class XSDateTimeCustomBinder extends XmlAdapter<String, Date> {

	@Override
    public Date unmarshal(final String dateTime) throws Exception {
		// X pattern just works from Java >= 1.7
		final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
		formatter.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

		return formatter.parse(dateTime);
    }

	@Override
    public String marshal(final Date dateTime) throws Exception {
		// X pattern just works from Java >= 1.7
		final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
		formatter.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		
		return formatter.format(dateTime);
    }
}
