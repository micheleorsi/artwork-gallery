package it.micheleorsi.repository;

import java.util.Comparator;
import java.util.List;

public interface ArtistRepository
{

  List<String> getArtists(Comparator<String> tComparator);

}
