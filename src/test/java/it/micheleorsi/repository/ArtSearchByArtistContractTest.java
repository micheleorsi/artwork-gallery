package it.micheleorsi.repository;

import it.micheleorsi.domain.Art;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public abstract class ArtSearchByArtistContractTest extends RepositoryCommonTest
{
  protected ArtSearchByArtist underTest;

  @Test
  public void getArtByArtist_listArts()
  {
    List<Art> returnedList = underTest.getArtByArtist("artist1");
    assertThat(returnedList,containsInAnyOrder(sampleList[0],
      sampleList[4], sampleList[7], sampleList[9]));
  }

  @Test
  public void getArtByArtist_noArtsWhenListIsEmpty()
  {
    underTest = new InMemoryGallery();
    List<Art> returnedList = underTest.getArtByArtist("artist1");
    assertThat(returnedList.size(),is(equalTo(0)));
  }

}
