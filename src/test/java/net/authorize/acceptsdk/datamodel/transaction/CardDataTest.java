package net.authorize.acceptsdk.datamodel.transaction;

import junit.framework.Assert;
import net.authorize.acceptsdk.exception.AcceptInvalidCardException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Kiran Bollepalli on 14,July,2016.
 * kbollepa@visa.com
 */
public class CardDataTest {

  private String cardNumber = "4111111111111111";
  private String month = "11";
  private String year = "2020";
  private String cvvCode = "111";
  private String zipCode = "bangalore";
  private String cardHolderName = "kiran bollepalli";

  @Before public void setUp() throws Exception {

  }

  @Test public void testCardNumber() throws Exception {
    cardNumber = "4111111111111111";
    month = "11";
    year = "2020";
    CardData card = new CardData.Builder(cardNumber, month, year).build();
    Assert.assertNotNull(card);
  }

  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Test public void testCardNumberForNull() throws AcceptInvalidCardException {
    cardNumber = null;
    month = "11";
    year = "2020";
    expectedException.expect(AcceptInvalidCardException.class);
    expectedException.expectMessage(AcceptInvalidCardException.INVALID_CARD_NUMBER);
    CardData card = new CardData.Builder(cardNumber, month, year).build();
  }

  @Test public void testCardNumberMinimumLength() throws AcceptInvalidCardException {
    cardNumber = "4111";
    month = "11";
    year = "2020";
    expectedException.expect(AcceptInvalidCardException.class);
    expectedException.expectMessage(AcceptInvalidCardException.INVALID_CARD_NUMBER);
    CardData card = new CardData.Builder(cardNumber, month, year).build();
  }

  @Test public void testCardNumberMaximumLength() throws AcceptInvalidCardException {
    cardNumber = "411111111111111111111111111111111111111";
    month = "11";
    year = "2020";
    expectedException.expect(AcceptInvalidCardException.class);
    expectedException.expectMessage(AcceptInvalidCardException.INVALID_CARD_NUMBER);
    CardData card = new CardData.Builder(cardNumber, month, year).build();
  }

  @Test public void testCardNumberForChars() throws AcceptInvalidCardException {
    cardNumber = "41111AAAAA";
    month = "11";
    year = "2020";
    expectedException.expect(AcceptInvalidCardException.class);
    expectedException.expectMessage(AcceptInvalidCardException.INVALID_CARD_NUMBER);
    CardData card = new CardData.Builder(cardNumber, month, year).build();
  }

  @Test public void testExpirationMonth() throws AcceptInvalidCardException {
    cardNumber = "4111111111111111";
    month = "aa";
    year = "2020";
    expectedException.expect(AcceptInvalidCardException.class);
    expectedException.expectMessage(AcceptInvalidCardException.INVALID_CARD_EXPIRATION_MONTH);
    CardData card = new CardData.Builder(cardNumber, month, year).build();
  }

  @Test public void testCVV() throws AcceptInvalidCardException {
    cardNumber = "4111111111111111";
    month = "11";
    year = "2020";
    cvvCode = "111";
    CardData card = new CardData.Builder(cardNumber, month, year).setCVVCode(cvvCode).build();
  }

  @Test public void testCVVForException() throws AcceptInvalidCardException {
    cardNumber = "4111111111111111";
    month = "11";
    year = "2020";
    cvvCode = "111a";
    expectedException.expect(AcceptInvalidCardException.class);
    expectedException.expectMessage(AcceptInvalidCardException.INVALID_CVV);
    CardData card = new CardData.Builder(cardNumber, month, year).setCVVCode(cvvCode).build();
  }

  @Test public void testZip() throws AcceptInvalidCardException {
    cardNumber = "4111111111111111";
    month = "11";
    year = "2020";
    cvvCode = "111";
    zipCode = "b";
    CardData card = new CardData.Builder(cardNumber, month, year).setCVVCode(cvvCode)
        .setZipCode(zipCode)
        .build();
  }

  @Test public void testZipForException() throws AcceptInvalidCardException {
    cardNumber = "4111111111111111";
    month = "11";
    year = "2020";
    cvvCode = "111";
    zipCode = null;
    expectedException.expect(AcceptInvalidCardException.class);
    expectedException.expectMessage(AcceptInvalidCardException.INVALID_ZIP);
    CardData card = new CardData.Builder(cardNumber, month, year).setCVVCode(cvvCode)
        .setZipCode(zipCode)
        .build();
  }

  @Test public void testCardHolderName() throws AcceptInvalidCardException {
    cardNumber = "4111111111111111";
    month = "11";
    year = "2020";
    cvvCode = "111";
    zipCode = "bangalore";
    cardHolderName = "kiran bollepalli";
    CardData card = new CardData.Builder(cardNumber, month, year).setCVVCode(cvvCode)
        .setZipCode(zipCode)
        .setCardHolderName(cardHolderName)
        .build();
  }

  @Test public void testCardHolderNameForException() throws AcceptInvalidCardException {
    cardNumber = "4111111111111111";
    month = "11";
    year = "2020";
    cvvCode = "111";
    zipCode = "bangalore";
    cardHolderName =
        "kiran bollepalli afafakfhaskjfadjsfjasdfhasdfadsjfdasjfhasdfhasdhfjhadsfhadfhfjadfn"
            + " sdfadffasdfadsfasdfasdfa adsfasdfadsfdasfa";
    expectedException.expect(AcceptInvalidCardException.class);
    expectedException.expectMessage(AcceptInvalidCardException.INVALID_CARD_HOLDER_NAME);
    CardData card = new CardData.Builder(cardNumber, month, year).setCVVCode(cvvCode)
        .setZipCode(zipCode)
        .setCardHolderName(cardHolderName)
        .build();
  }
}