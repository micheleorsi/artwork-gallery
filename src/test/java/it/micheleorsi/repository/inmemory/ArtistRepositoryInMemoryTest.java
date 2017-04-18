package it.micheleorsi.repository.inmemory;

import it.micheleorsi.repository.ArtistRepositoryContractTest;
import it.micheleorsi.repository.InMemoryGallery;
import org.junit.Before;

public class ArtistRepositoryInMemoryTest extends ArtistRepositoryContractTest
{

  @Before
  public void setup()
  {
    underTest = new InMemoryGallery(sampleList);
  }

}
