package it.micheleorsi.repository;

import it.micheleorsi.domain.Art;
import it.micheleorsi.domain.ArtType;
import org.junit.BeforeClass;

import java.time.LocalDateTime;
import java.time.Month;

public class RepositoryCommonTest
{
  protected static Art[] sampleList;
  static Art sampleArt;

  @BeforeClass
  public static void beforeClass()
  {
    sampleArt = new Art.Builder().withName("nameAdded").withArtistName("artistAdded").withType(ArtType.PAINTING).build();
    sampleList =  new Art[] {
      new Art.Builder().withName("art1").withArtistName("artist1").withType(ArtType.PAINTING)
                       .withAskingPrice("1000").build(),
    new Art.Builder().withName("art2").withArtistName("artist2").withType(ArtType.TAPESTRY)
                     .withDateTimeCreated(LocalDateTime.of(2016, Month.FEBRUARY, 10, 10, 12, 12)).build(),
    new Art.Builder().withName("art3").withArtistName("artist3").withType(ArtType.SCULPTURE)
                     .withAskingPrice("2000").build(),
    new Art.Builder().withName("art4").withArtistName("artist4").withType(ArtType.VASE)
                     .withDateTimeCreated(LocalDateTime.of(2016, Month.APRIL, 10, 10, 12, 12)).build(),
    new Art.Builder().withName("art5").withArtistName("artist1").withType(ArtType.PAINTING)
                     .withDateTimeCreated(LocalDateTime.of(2016, Month.MAY, 10, 10, 12, 12)).withAskingPrice("3000")
                     .build(),
    new Art.Builder().withName("art6").withArtistName("artist2").withType(ArtType.TAPESTRY)
                     .withAskingPrice("3500").build(),
    new Art.Builder().withName("art7").withArtistName("artist3").withType(ArtType.SCULPTURE)
                     .withDateTimeCreated(LocalDateTime.of(2016, Month.JULY, 10, 10, 12, 12)).build(),
    new Art.Builder().withName("art8").withArtistName("artist1").withType(ArtType.PAINTING)
                     .withDateTimeCreated(LocalDateTime.of(2016, Month.AUGUST, 10, 10, 12, 12)).withAskingPrice("4500")
                     .build(),
    new Art.Builder().withName("art9").withArtistName("artist2").withType(ArtType.VASE)
                     .withDateTimeCreated(LocalDateTime.of(2016, Month.SEPTEMBER, 10, 10, 12, 12)).build(),
    new Art.Builder().withName("art10").withArtistName("artist1").withType(ArtType.PAINTING)
                     .withAskingPrice("5500").build()
    };
  }

}
