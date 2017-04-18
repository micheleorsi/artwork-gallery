package it.micheleorsi.repository;

import it.micheleorsi.domain.Art;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public abstract class ArtByPriceContractTest extends RepositoryCommonTest
{
  protected ArtByPrice underTest;

  @Test
  public void getArtByPrice_listArtsBetweenPrices()
  {
    List<Art> returnedList = underTest.getArtByPrice(new BigDecimal("2100"),new BigDecimal("4200"));
    assertThat(returnedList,containsInAnyOrder(sampleList[4],
      sampleList[5]));
  }

  @Test
  public void getArtByPrice_listArtsWithPrices()
  {
    List<Art> returnedList = underTest.getArtWithPrice();
    assertThat(returnedList,containsInAnyOrder(sampleList[0],sampleList[2],sampleList[5],
      sampleList[9],sampleList[4],sampleList[7]));
  }

  @Test
  public void getArtByPriceLowerThan_listArtsBetweenPrices()
  {
    List<Art> returnedList = underTest.getArtByPriceLowerThan(new BigDecimal("4200"));
    assertThat(returnedList,containsInAnyOrder(sampleList[0],sampleList[2],
      sampleList[4], sampleList[5]));
  }

  @Test
  public void getArtByPriceHigherThan_listArtsBetweenPrices()
  {
    List<Art> returnedList = underTest.getArtByPriceHigherThan(new BigDecimal("2100"));
    assertThat(returnedList,containsInAnyOrder(sampleList[4],sampleList[5],
      sampleList[7], sampleList[9]));
  }

}
