package it.micheleorsi;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


public class InMemoryGalleryTest
{
  private Gallery underTest;
  private static List<Art> sampleList = new LinkedList<>();

  static {
    sampleList.add(new Art.Builder().withName("art1").withArtistName("artist1").withType(ArtType.PAINTING)
      .withAskingPrice("1000").build());
    sampleList.add(new Art.Builder().withName("art2").withArtistName("artist2").withType(ArtType.TAPESTRY)
      .withDateTimeCreated(LocalDateTime.of(2016, Month.FEBRUARY,10,10,12,12)).build());
    sampleList.add(new Art.Builder().withName("art3").withArtistName("artist3").withType(ArtType.SCULPTURE)
      .withAskingPrice("2000").build());
    sampleList.add(new Art.Builder().withName("art4").withArtistName("artist4").withType(ArtType.VASE)
      .withDateTimeCreated(LocalDateTime.of(2016, Month.APRIL,10,10,12,12)).build());
    sampleList.add(new Art.Builder().withName("art5").withArtistName("artist1").withType(ArtType.PAINTING)
      .withDateTimeCreated(LocalDateTime.of(2016, Month.MAY,10,10,12,12)).withAskingPrice("3000").build());
    sampleList.add(new Art.Builder().withName("art6").withArtistName("artist2").withType(ArtType.TAPESTRY)
      .withAskingPrice("3500").build());
    sampleList.add(new Art.Builder().withName("art7").withArtistName("artist3").withType(ArtType.SCULPTURE)
      .withDateTimeCreated(LocalDateTime.of(2016, Month.JULY,10,10,12,12)).build());
    sampleList.add(new Art.Builder().withName("art8").withArtistName("artist1").withType(ArtType.PAINTING)
      .withDateTimeCreated(LocalDateTime.of(2016, Month.AUGUST,10,10,12,12)).withAskingPrice("4500").build());
    sampleList.add(new Art.Builder().withName("art9").withArtistName("artist2").withType(ArtType.VASE)
      .withDateTimeCreated(LocalDateTime.of(2016, Month.SEPTEMBER,10,10,12,12)).build());
    sampleList.add(new Art.Builder().withName("art10").withArtistName("artist1").withType(ArtType.PAINTING)
      .withAskingPrice("5500").build());
  }

  private final Art sampleArt = new Art.Builder().withName("nameAdded").withArtistName("artistAdded").withType(ArtType.PAINTING).build();

  @Before
  public void setup()
  {
    underTest = new InMemoryGallery(sampleList.toArray(new Art[sampleList.size()]));
  }

  @Test
  public void getAllArt_ReturnsAll()
  {
    assertThat(underTest.getAllArt(),is(equalTo(sampleList)));
  }

  @Test
  public void addArt_addAPieceOfArt()
  {
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.size())));

    underTest.addArt(sampleList.get(0));
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.size()+1)));

  }

  @Test
  public void deleteArt_deleteAPieceOfArt()
  {
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.size())));

    underTest.deleteArt(sampleList.get(0));
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.size()-1)));
  }

  @Test
  public void deleteArt_withNoArtPresentHasNoEffect()
  {
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.size())));
    underTest.deleteArt(sampleArt);
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.size())));
  }

  @Test
  public void deleteArt_withDuplicatesDeletesAll()
  {
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.size())));
    underTest.addArt(sampleList.get(0));
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.size()+1)));
    underTest.deleteArt(sampleList.get(0));
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.size()-1)));
  }

  @Test
  public void getArtists_returnsArtistsInAlphabeticalOrder()
  {
    List<String> artistNames = Arrays.asList("artist1", "artist2", "artist3", "artist4");
    assertThat(underTest.getArtists(Comparator.naturalOrder()),is(equalTo(artistNames)));
  }

  @Test
  public void getArtists_returnsArtistsInAlphabeticalReversedOrder()
  {
    List<String> artistNames = Arrays.asList("artist4", "artist3", "artist2", "artist1");
    assertThat(underTest.getArtists(Comparator.reverseOrder()),is(equalTo(artistNames)));
  }

  @Test
  public void getArtists_isEmptyWhenListIsEmpty()
  {
    underTest = new InMemoryGallery(new Art[0]);
    assertThat(underTest.getArtists(Comparator.naturalOrder()).isEmpty(),is(true));
  }

  @Test
  public void getArtByArtist_returnsRightListOfPiecesOfArt()
  {
    List<Art> returnedList = underTest.getArtByArtist("artist1");
    assertThat(returnedList,containsInAnyOrder(sampleList.get(0),
      sampleList.get(4), sampleList.get(7), sampleList.get(9)));
  }

  @Test
  public void getArtByArtist_returnsNoArtsWhenListIsEmpty()
  {
    underTest = new InMemoryGallery(new Art[0]);
    List<Art> returnedList = underTest.getArtByArtist("artist1");
    assertThat(returnedList.size(),is(equalTo(0)));
  }

  @Test
  public void getRecentArt_returnsRightPiecesOfArt()
  {
    List<Art> returnedList = underTest.getRecentArt(
      sampleList.get(1).getDateTimeCreated().get(),
      sampleList.get(8).getDateTimeCreated().get());
    assertThat(returnedList,containsInAnyOrder(sampleList.get(3), sampleList.get(4),
      sampleList.get(6), sampleList.get(7)));
  }

  @Test
  public void getRecentArt_returnsArtsWithBoundsExcluded()
  {
    List<Art> returnedList = underTest.getRecentArt(
      sampleList.get(1).getDateTimeCreated().get(),
      sampleList.get(4).getDateTimeCreated().get());
    assertThat(returnedList,hasItem(sampleList.get(3)));
  }

  @Test
  public void getArtByPrice_returnsRightPiecesOfArt()
  {
    List<Art> returnedList = underTest.getArtByPrice(Optional.of(new BigDecimal("2100")),Optional.of(new BigDecimal("4200")));
    assertThat(returnedList,containsInAnyOrder(sampleList.get(4),
      sampleList.get(5)));
  }

  @Test
  public void getArtByPrice_returnsRightPiecesOfArtWhenLowerLimitIsNull()
  {
    List<Art> returnedList = underTest.getArtByPrice(Optional.empty(),Optional.of(new BigDecimal("4200")));
    assertThat(returnedList,containsInAnyOrder(sampleList.get(0),sampleList.get(2),
      sampleList.get(4), sampleList.get(5)));
  }

  @Test
  public void getArtByPrice_returnsRightPiecesOfArtWhenUpperLimitIsNull()
  {
    List<Art> returnedList = underTest.getArtByPrice(Optional.of(new BigDecimal("2100")),Optional.empty());
    assertThat(returnedList,containsInAnyOrder(sampleList.get(4),sampleList.get(5),
      sampleList.get(7), sampleList.get(9)));
  }

}
