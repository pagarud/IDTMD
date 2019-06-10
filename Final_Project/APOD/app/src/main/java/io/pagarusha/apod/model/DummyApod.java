package io.pagarusha.apod.model;

public class DummyApod {
    
    private static Apod dummy;

    public static Apod getApod() {
        new DummyApod();
        return dummy;
    }
    
    public DummyApod() {
        dummy = new Apod();
        setFields(dummy);
    }

    private void setFields(Apod dummy) {
        dummy.setCopyright("Juan Carlos Casado");
        dummy.setDate("2019-06-09");
        dummy.setExplanation("Why does the shadow of this volcano look like a triangle? The Mount Teide volcano itself does not have the strictly pyramidal shape that its geometric shadow might suggest.  The triangle shadow phenomena is not unique to the Mt. Teide, though, and is commonly seen from the tops of other large mountains and volcanoes. A key reason for the strange dark shape is that the observer is looking down the long corridor of a sunset (or sunrise) shadow that extends to the horizon. Even if the huge volcano were a perfect cube and the resulting shadow were a long rectangular box, that box would appear to taper off at its top as its shadow extended far into the distance, just as parallel train tracks do. The featured spectacular image shows Pico Viejo crater in the foreground, located on Tenerife in the Canary Islands of Spain. The nearly full moon is seen nearby shortly after its total lunar eclipse.   Explore the Universe: Random APOD Generator");
        dummy.setHdurl("https://apod.nasa.gov/apod/image/1906/teideshadow_casado_2000.jpg");
        dummy.setMediaType("image");
        dummy.setServiceVersion("v1");
        dummy.setTitle("A Triangular Shadow of a Large Volcano");
        dummy.setUrl("https://apod.nasa.gov/apod/image/1906/teideshadow_casado_1080.jpg");
    }
}
