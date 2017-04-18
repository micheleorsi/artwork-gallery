package it.micheleorsi.repository.inmemory;

import it.micheleorsi.repository.ArtSearchByArtistContractTest;
import it.micheleorsi.repository.InMemoryGallery;
import org.junit.Before;

public class ArtSearchByArtistInMemoryTest extends ArtSearchByArtistContractTest
{

  @Before
  public void setup()
  {
    underTest = new InMemoryGallery(sampleList);
  }

}
