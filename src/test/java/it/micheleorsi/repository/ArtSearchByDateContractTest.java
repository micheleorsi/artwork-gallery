package it.micheleorsi.repository;

import it.micheleorsi.domain.Art;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public abstract class ArtSearchByDateContractTest extends RepositoryCommonTest
{
  protected ArtSearchByDate underTest;

  @Test
  public void getRecentArt_listArtsBetweenTimestamp()
  {
    List<Art> returnedList = underTest.getRecentArt(
      sampleList[1].getDateTimeCreated(),
      sampleList[8].getDateTimeCreated());
    assertThat(returnedList,containsInAnyOrder(sampleList[3], sampleList[4],
      sampleList[6], sampleList[7]));
  }

  @Test
  public void getRecentArt_listArtsBetweenTimestampWithBoundsExcluded()
  {
    List<Art> returnedList = underTest.getRecentArt(
      sampleList[1].getDateTimeCreated(),
      sampleList[4].getDateTimeCreated());
    assertThat(returnedList,hasItem(sampleList[3]));
  }

}
