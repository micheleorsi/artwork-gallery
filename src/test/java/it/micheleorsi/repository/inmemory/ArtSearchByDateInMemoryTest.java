package it.micheleorsi.repository.inmemory;

import it.micheleorsi.repository.ArtSearchByDateContractTest;
import it.micheleorsi.repository.InMemoryGallery;
import org.junit.Before;

public class ArtSearchByDateInMemoryTest extends ArtSearchByDateContractTest
{

  @Before
  public void setup()
  {
    underTest = new InMemoryGallery(sampleList);
  }

}
