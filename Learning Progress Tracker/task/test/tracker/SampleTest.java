package tracker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SampleTest {

    @Test
    public void testEmailValidation() {
        assertTrue(InputsFormatter.validEmail("jdoe@mail.net"));
        assertTrue(InputsFormatter.validEmail("ane.doe@yahoo.com"));
        assertTrue(InputsFormatter.validEmail("name@domain.com"));
        assertTrue(InputsFormatter.validEmail("maryj@google.com"));
        assertTrue(InputsFormatter.validEmail("jsmith@hotmail.com"));
        assertTrue(InputsFormatter.validEmail("125367at@zzz90.z9"));
        assertTrue(InputsFormatter.validEmail("1@1.1"));

        assertFalse(InputsFormatter.validEmail("email"));
        assertFalse(InputsFormatter.validEmail("Hello world"));
        assertFalse(InputsFormatter.validEmail("12345@.ru"));
        assertFalse(InputsFormatter.validEmail("@google.com"));
        assertFalse(InputsFormatter.validEmail("emailemail.xyz"));
        assertFalse(InputsFormatter.validEmail("email@emailxyz"));
        assertFalse(InputsFormatter.validEmail("email@e@mail.xyz"));
    }

    @Test
    public void testFirstNameValidation() {
        assertTrue(InputsFormatter.validFirstName("John"));
        assertTrue(InputsFormatter.validFirstName("Anny"));
        assertTrue(InputsFormatter.validFirstName("Jean-Claude"));
        assertTrue(InputsFormatter.validFirstName("Mary"));
        assertTrue(InputsFormatter.validFirstName("Al"));
        assertTrue(InputsFormatter.validFirstName("Robert"));
        assertTrue(InputsFormatter.validFirstName("Ed"));
        assertTrue(InputsFormatter.validFirstName("na'me"));
        assertTrue(InputsFormatter.validFirstName("n'a"));
        assertTrue(InputsFormatter.validFirstName("nA"));

        assertFalse(InputsFormatter.validFirstName("n"));
        assertFalse(InputsFormatter.validFirstName("'name"));
        assertFalse(InputsFormatter.validFirstName("-name"));
        assertFalse(InputsFormatter.validFirstName("name-"));
        assertFalse(InputsFormatter.validFirstName("name'"));
        assertFalse(InputsFormatter.validFirstName("nam-'e"));
        assertFalse(InputsFormatter.validFirstName("na'-me"));
        assertFalse(InputsFormatter.validFirstName("na--me"));
        assertFalse(InputsFormatter.validFirstName("na''me"));
        assertFalse(InputsFormatter.validFirstName("námé"));
    }

    @Test
    public void testLastNameValidation() {
        assertTrue(InputsFormatter.validLastName("Smith"));
        assertTrue(InputsFormatter.validLastName("Doolittle"));
        assertTrue(InputsFormatter.validLastName("O'Connor"));
        assertTrue(InputsFormatter.validLastName("Emelianenko"));
        assertTrue(InputsFormatter.validLastName("Owen"));
        assertTrue(InputsFormatter.validLastName("Jemison Van de Graaff"));
        assertTrue(InputsFormatter.validLastName("Eden"));
        assertTrue(InputsFormatter.validLastName("s-u"));
        assertTrue(InputsFormatter.validLastName("me su aa-b'b"));
        assertTrue(InputsFormatter.validLastName("me"));

        assertFalse(InputsFormatter.validLastName("s"));
        assertFalse(InputsFormatter.validLastName("-surname"));
        assertFalse(InputsFormatter.validLastName("'surname"));
        assertFalse(InputsFormatter.validLastName("surnam''e"));
        assertFalse(InputsFormatter.validLastName("surn--ame"));
        assertFalse(InputsFormatter.validLastName("s'-urname"));
        assertFalse(InputsFormatter.validLastName("su-'rname"));
        assertFalse(InputsFormatter.validLastName("surname-"));
        assertFalse(InputsFormatter.validLastName("surname'"));
        assertFalse(InputsFormatter.validLastName("surnámé"));
    }
}
