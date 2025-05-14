# google-geocode

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=esign-consulting_google-geocode&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=esign-consulting_google-geocode)
[![GitHub tag](https://img.shields.io/github/tag/esign-consulting/google-geocode.svg)](https://github.com/esign-consulting/google-geocode/releases/latest)
[![Maven Central](https://img.shields.io/maven-central/v/br.com.esign/google-geocode.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22br.com.esign%22%20AND%20a:%22google-geocode%22)

Simple Java library for the [Google Geocoding API](https://developers.google.com/maps/documentation/geocoding/start).

## pom.xml

The library is provided through Maven since the release of the version 2.0.0. Starting in the version 3.0.0, it is compiled with Java 11. Just include the following dependency in your pom.xml:

```xml
<dependency>
    <groupId>br.com.esign</groupId>
    <artifactId>google-geocode</artifactId>
    <version>3.0.0</version>
</dependency>
```

## Code examples

```java
GoogleGeocode googleGeocode = new GoogleGeocode(your_api_key, address); // the address must not be encoded
String jsonString = googleGeocode.getJsonString(); // may throw IOException
```

Alternatively, an object representing the Google Geocoding API json response can be returned:

```java
GoogleGeocode googleGeocode = new GoogleGeocode(your_api_key, latitude, longitude); // reverse geocoding
GeocodeResponse geocodeResponse = googleGeocode.getResponseObject(); // may throw IOException
```

This object is usefull to get the response content:

```java
if (googleResponse.isStatusOK()) {
   String country = getCountryShortName(); // returns the country short name of the first result
   // more code
}
```

Any result in the list of results can be accessed through two ways:

```java
// returns the formatted address of the third result
// or null if the index is out of bounds
geocodeResponse.setIndex(2);
String formattedAddress = geocodeResponse.getFormattedAddress();
```

Or

```java
// returns the geometry object of the position in the results list defined by the index parameter
// or null if it is out of bounds
Geometry geometry = geocodeResponse.getGeometry(index);
```

The Google Geocoding API language parameter can also be defined:

```java
GoogleGeocode googleGeocode = new GoogleGeocode(your_api_key, address);
googleGeocode.setLanguage(GoogleGeocodeLanguage.PORTUGUESE_BRAZIL);
```

Enjoy! :smile:
