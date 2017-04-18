package it.micheleorsi;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface Gallery
{
  void addArt(Art art);

  void deleteArt(Art art);

  List<Art> getAllArt();

  List<String> getArtists(Comparator<String> tComparator);

  List<Art> getArtByArtist(String artist);

  List<Art> getRecentArt(LocalDateTime elderLimit, LocalDateTime newerLimit);

  List<Art> getArtByPrice(Optional<BigDecimal> lowerLimit, Optional<BigDecimal> upperLimit);
}
