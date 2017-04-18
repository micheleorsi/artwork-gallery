package it.micheleorsi.repository;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public abstract class ArtGalleryRepositoryContractTest extends RepositoryCommonTest
{
  protected ArtGalleryRepository underTest;

  @Test
  public void getAllArt_ReturnsAll()
  {
    assertThat(underTest.getAllArt(),containsInAnyOrder(sampleList));
  }

  @Test
  public void addArt_addAPieceOfArt()
  {
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.length)));
    underTest.addArt(sampleList[0]);
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.length+1)));
  }

  @Test
  public void deleteArt_deleteAPieceOfArt()
  {
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.length)));
    underTest.deleteArt(sampleList[0]);
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.length-1)));
  }

  @Test
  public void deleteArt_withNoArtPresent()
  {
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.length)));
    underTest.deleteArt(sampleArt);
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.length)));
  }

  @Test
  public void deleteArt_withDuplicates()
  {
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.length)));
    underTest.addArt(sampleList[0]);
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.length+1)));
    underTest.deleteArt(sampleList[0]);
    assertThat(underTest.getAllArt().size(),is(equalTo(sampleList.length-1)));
  }

}
