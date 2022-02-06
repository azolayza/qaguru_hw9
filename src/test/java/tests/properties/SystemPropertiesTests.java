package tests.properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SystemPropertiesTests {

    //        System.getProperty("key");
    //        System.setProperty("key", "value");

    @Test
    void SomeTest1(){
        String someValue = System.getProperty("someKey");
        System.out.println(someValue); //null

        System.setProperty("someKey", "redValue");
        someValue = System.getProperty("someKey");
        System.out.println(someValue);
    }

    @Test
    void SomeTest2(){
        System.setProperty("someKey", "redValue");
        String someValue = System.getProperty("someKey");
        System.out.println(someValue);
    }

    @Test
    void SomeTest3(){
        String someValue = System.getProperty("someKey", "blue value");
        System.out.println(someValue);
    }

    @Test
    void SomeTest4(){
        Boolean someValue = Boolean.parseBoolean(
                System.getProperty("someKey", "true"));
        assertTrue(someValue);
        System.out.println(someValue);
    }

    @Test
    @Tag("properties")
    void SomeTest5(){
        String browser = System.getProperty("browser");
        System.out.println(browser);
    }

    @Test
    @Tag("properties")
    void someTest6() {
        String browser = System.getProperty("browser", "chrome");
        String version = System.getProperty("version", "91");
        String browserSize = System.getProperty("browserSize", "300x300");

        System.out.println(browser);
        System.out.println(version);
        System.out.println(browserSize);
    }

}
