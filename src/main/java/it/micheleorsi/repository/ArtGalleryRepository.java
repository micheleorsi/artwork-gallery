package it.micheleorsi.repository;

import it.micheleorsi.domain.Art;

import java.util.List;

public interface ArtGalleryRepository
{

  void addArt(Art art);

  void deleteArt(Art art);

  List<Art> getAllArt();

}
