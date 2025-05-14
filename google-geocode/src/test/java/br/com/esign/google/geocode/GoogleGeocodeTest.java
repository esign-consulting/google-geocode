package br.com.esign.google.geocode;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import br.com.esign.google.geocode.model.AddressComponentType;
import br.com.esign.google.geocode.model.GeocodeResponse;

public class GoogleGeocodeTest {
	
	private final static String apiKey = System.getProperty("google.geocode.api.key");

	@Test
	public void testGetResponseObject1() throws IOException {
		GoogleGeocode googleGeocode = new GoogleGeocode(apiKey, "-23.544969", "-46.641846");
		GeocodeResponse geocodeResponse = googleGeocode.getResponseObject();
		assertNotNull(geocodeResponse);
		assertTrue(geocodeResponse.isStatusOK());
	}

	@Test
	public void testGetResponseObject2() throws IOException {
		final String address = "R. Sete de Abril, 346 - República, São Paulo - SP, 01044-000, Brasil";
		GoogleGeocode googleGeocode = new GoogleGeocode(apiKey, address);
		googleGeocode.setLanguage(GoogleGeocodeLanguage.PORTUGUESE_BRAZIL);
		GeocodeResponse geocodeResponse = googleGeocode.getResponseObject();
		assertNotNull(geocodeResponse);
		assertTrue(geocodeResponse.isStatusOK());
		assertEquals(address, geocodeResponse.getFormattedAddress());
		assertEquals("346", geocodeResponse.getAddressComponentByType(AddressComponentType.STREET_NUMBER).getShortName());
		assertEquals("República", geocodeResponse.getSublocalityLongName());
		assertEquals("São Paulo", geocodeResponse.getAdministrativeAreaLevel2LongName());
		assertEquals("BR", geocodeResponse.getCountryShortName());
	}

	@Test
	public void testGetResponseObject3() throws IOException {
		GoogleGeocode googleGeocode = new GoogleGeocode("fakeApiKey", "-23.544969", "-46.641846");
		GeocodeResponse geocodeResponse = googleGeocode.getResponseObject();
		assertNotNull(geocodeResponse);
		assertFalse(geocodeResponse.isStatusOK());
		assertNotNull(geocodeResponse.getErrorMessage());
		assertNull(geocodeResponse.getGeometry());
		assertNull(geocodeResponse.getAdministrativeAreaLevel1LongName());
		assertNull(geocodeResponse.getLocalityLongName());
		assertNull(geocodeResponse.getNeighborhoodLongName());
	}
	
}