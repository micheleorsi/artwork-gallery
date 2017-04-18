package it.micheleorsi.repository.inmemory;

import it.micheleorsi.repository.ArtGalleryRepositoryContractTest;
import it.micheleorsi.repository.InMemoryGallery;
import org.junit.Before;

public class ArtGalleryRepositoryInMemoryTest extends ArtGalleryRepositoryContractTest
{
  @Before
  public void setup()
  {
    underTest = new InMemoryGallery(sampleList);
  }

}
