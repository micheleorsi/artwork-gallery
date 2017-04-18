package it.micheleorsi.repository;

import it.micheleorsi.domain.Art;

import java.util.List;

public interface ArtSearchByArtist
{

  List<Art> getArtByArtist(String artist);

}
