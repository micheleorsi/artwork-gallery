package it.micheleorsi.repository.inmemory;

import it.micheleorsi.repository.ArtByPriceContractTest;
import it.micheleorsi.repository.InMemoryGallery;
import org.junit.Before;

public class ArtByPriceInMemoryTest extends ArtByPriceContractTest
{
  @Before
  public void setup()
  {
    underTest = new InMemoryGallery(sampleList);
  }
}
