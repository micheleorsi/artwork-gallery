package it.micheleorsi.repository;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

public abstract class ArtistRepositoryContractTest extends RepositoryCommonTest
{

  protected ArtistRepository underTest;

  @Test
  public void getArtists_returnAllTheArtists()
  {
    List<String> artists = underTest.getArtists(Comparator.naturalOrder());
    assertThat(artists.size(),is(equalTo(4)));
  }

  @Test
  public void getArtists_inAlphabeticalOrder()
  {
    List<String> artists = underTest.getArtists(Comparator.naturalOrder());
    String previous = artists.get(0);
    for(String artist: artists)
    {
      assertThat(artist,greaterThanOrEqualTo(previous));
    }
  }

  @Test
  public void getArtists_inAlphabeticalReversedOrder()
  {
    List<String> artists = underTest.getArtists(Comparator.reverseOrder());
    String previous = artists.get(0);
    for(String artist: artists)
    {
      assertThat(previous,greaterThanOrEqualTo(artist));
    }
  }

  @Test
  public void getArtists_emptyWhenListIsEmpty()
  {
    underTest = new InMemoryGallery();
    assertThat(underTest.getArtists(Comparator.naturalOrder()).isEmpty(),is(true));
  }
}
