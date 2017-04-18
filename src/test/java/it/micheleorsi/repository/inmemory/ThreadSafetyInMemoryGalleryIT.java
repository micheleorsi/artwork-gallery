package it.micheleorsi.repository.inmemory;

import it.micheleorsi.domain.Art;
import it.micheleorsi.domain.ArtType;
import it.micheleorsi.repository.InMemoryGallery;
import it.micheleorsi.repository.RepositoryCommonTest;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ThreadSafetyInMemoryGalleryIT extends RepositoryCommonTest
{

  private InMemoryGallery underTest;

  @Before
  public void setup()
  {
    Art art1 = new Art.Builder().withName("artfirst").withArtistName("artist1").withType(ArtType.PAINTING)
                                .withAskingPrice("1000").build();
    Art art2 = new Art.Builder().withName("artsecond").withArtistName("artist2").withType(ArtType.TAPESTRY)
                                .withDateTimeCreated(LocalDateTime.of(2016, Month.FEBRUARY,10,10,12,12)).build();
    underTest = new InMemoryGallery(art1, art2);
  }

  @Test
  public void aLotOfThreadsInParallel() throws InterruptedException
  {
    assertThat(underTest.getAllArt().size(),is(equalTo(2)));
    LocalDateTime tsstart = LocalDateTime.now();
    int counter = 1000;
    IntStream.range(0, counter)
             .parallel()
             .forEach(this::simulateJob);
    System.out.println("millis: "+Duration.between(tsstart,LocalDateTime.now()).toMillis());
    assertThat(underTest.getAllArt().size(),is(equalTo(2)));
  }

  private void simulateJob(int index)
  {
    Art localArt = new Art.Builder().withName("art"+ index)
                                    .withArtistName("artist")
                                    .withType(ArtType.PAINTING)
                                    .withAskingPrice("1000").build();

    underTest.addArt(localArt);
    underTest.getAllArt();
    underTest.getArtists(Comparator.naturalOrder());
    underTest.getArtByArtist("artist");
    underTest.getRecentArt(LocalDateTime.MIN,LocalDateTime.MAX);
    underTest.getArtByPrice(BigDecimal.ZERO,BigDecimal.TEN);
    try
    {
      Thread.sleep(100);
    }
    catch (InterruptedException e)
    {
      throw new RuntimeException(e);
    }
    underTest.deleteArt(localArt);
  }
}
