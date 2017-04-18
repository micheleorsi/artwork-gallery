package it.micheleorsi;

import org.junit.Test;

import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class ArtTest
{

  @Test
  public void createdWithMandatoryFieldsOnly()
  {
    new Art.Builder().withName("testName")
                     .withType(ArtType.PAINTING)
                     .withArtistName("artistName")
                     .build();
  }

  @Test(expected = IllegalStateException.class)
  public void allMandatoryFieldsAreNeededForCreation()
  {
    new Art.Builder().build();
  }

  @Test(expected = IllegalStateException.class)
  public void nameMissingForCreation()
  {
    new Art.Builder().withType(ArtType.PAINTING)
                     .withArtistName("artistName")
                     .build();
  }

  @Test(expected = IllegalStateException.class)
  public void typeMissingForCreation()
  {
    new Art.Builder().withName("testName")
                     .withArtistName("artistName")
                     .build();
  }

  @Test(expected = IllegalStateException.class)
  public void artistNameMissingForCreation()
  {
    new Art.Builder().withName("testName")
                     .withType(ArtType.PAINTING)
                     .build();
  }

  @Test
  public void creationParametersMatchArtObjectFields()
  {
    Art underTest = new Art.Builder().withName("testName")
                                     .withType(ArtType.PAINTING)
                                     .withArtistName("artistName")
                                     .withAskingPrice("10")
                                     .withDateTimeCreated(LocalDateTime.of(2016, Month.APRIL, 12, 10, 10, 20))
                                     .build();

    assertThat(underTest.getName(), is("testName"));
    assertThat(underTest.getType(), is(ArtType.PAINTING));
    assertThat(underTest.getArtistName(), is("artistName"));
    assertThat(underTest.getAskingPrice().get(), is(new BigDecimal("10")));
    assertThat(underTest.getDateTimeCreated().get(), is(LocalDateTime.of(2016, Month.APRIL, 12, 10, 10, 20)));
  }

  @Test
  public void equalityCheckWithReference()
  {
    Art underTest = new Art.Builder()
      .withName("name")
      .withArtistName("artistName")
      .withType(ArtType.PAINTING)
      .withAskingPrice("12")
      .build();

    assertThat(underTest==underTest,is(true));
    assertThat(underTest, is(equalTo(underTest)));
  }

  @Test
  public void inequalityCheckWithAnotherClassType()
  {
    Art underTest = new Art.Builder()
      .withName("name")
      .withArtistName("artistName")
      .withType(ArtType.PAINTING)
      .withAskingPrice("12")
      .build();

    assertThat(underTest, is(not(equalTo("wrongClassType"))));
  }

  @Test
  public void equalityCheckWithSameFieldsDifferentReference()
  {
    String name = "commonName";
    String artistName = "commonArtistName";
    ArtType artType = ArtType.TAPESTRY;
    Art firstTest = new Art.Builder()
      .withName(name)
      .withArtistName(artistName)
      .withType(artType)
      .build();
    Art secondTest = new Art.Builder()
      .withName(name)
      .withArtistName(artistName)
      .withType(artType)
      .build();

    assertThat(firstTest==secondTest,is(false));
    assertThat(firstTest, is(equalTo(secondTest)));
  }

  @Test
  public void equalityCheckWithOtherFields()
  {
    String name = "commonName";
    String artistName = "commonArtistName";
    ArtType artType = ArtType.TAPESTRY;
    Art firstTest = new Art.Builder()
      .withName(name)
      .withArtistName(artistName)
      .withType(artType)
      .withAskingPrice("12")
      .build();
    Art secondTest = new Art.Builder()
      .withName(name)
      .withArtistName(artistName)
      .withType(artType)
      .withAskingPrice("11")
      .withDateTimeCreated(LocalDateTime.of(2016, Month.APRIL, 12, 10, 10, 20))
      .build();

    assertThat(firstTest, is(equalTo(secondTest)));
  }

  @Test
  public void inequalityCheckWithNull()
  {
    Art firstTest = new Art.Builder()
      .withName("name")
      .withArtistName("artistName")
      .withType(ArtType.PAINTING)
      .withAskingPrice("12")
      .build();

    assertThat(firstTest, is(not(equalTo(null))));
  }

  @Test
  public void ifTwoObjectsAreEqual_theyHaveSameHashCode()
  {
    String name = "commonName";
    String artistName = "commonArtistName";
    ArtType artType = ArtType.TAPESTRY;
    Art firstTest = new Art.Builder()
      .withName(name)
      .withArtistName(artistName)
      .withType(artType)
      .build();
    Art secondTest = new Art.Builder()
      .withName(name)
      .withArtistName(artistName)
      .withType(artType)
      .build();

    assertThat(firstTest,is(equalTo(secondTest)));
    assertThat(firstTest.hashCode(), is(equalTo(secondTest.hashCode())));
  }

  @Test
  public void ifTwoObjectsHaveTheSameHashCode_theyMayNotBeEqual()
  {
    String name = "commonName";
    ArtType artType = ArtType.TAPESTRY;
    Art firstTest = new Art.Builder()
      .withName(name)
      .withArtistName("artistName1")
      .withType(artType)
      .build();
    Art secondTest = new Art.Builder()
      .withName(name)
      .withArtistName("artistName2")
      .withType(artType)
      .build();

    assertThat(firstTest.hashCode(), is(equalTo(secondTest.hashCode())));
    assertThat(firstTest,is(not(equalTo(secondTest))));
  }

  @Test
  public void classIsImmutable()
  {
    Modifier.isFinal(Art.class.getModifiers());
  }

  @Test(expected = IllegalArgumentException.class)
  public void askingPriceShouldBePositive()
  {
    new Art.Builder().withName("testName")
                     .withType(ArtType.PAINTING)
                     .withArtistName("artistName")
                     .withAskingPrice("-1")
                     .build();
  }

  @Test
  public void allFieldsArePresentInStringRepresentation()
  {
    String underTest = new Art.Builder().withName("testName")
                          .withType(ArtType.PAINTING)
                          .withArtistName("artistName")
                          .withAskingPrice("12345678")
                          .withDateTimeCreated(LocalDateTime.of(2017,Month.APRIL,17,10,11,12))
                          .build().toString();
    assertThat(underTest,containsString("testName"));
    assertThat(underTest,containsString(ArtType.PAINTING.toString()));
    assertThat(underTest,containsString("artistName"));
    assertThat(underTest,containsString("12345678"));
    assertThat(underTest,containsString("2017-04-17T10:11:12"));

  }
}
