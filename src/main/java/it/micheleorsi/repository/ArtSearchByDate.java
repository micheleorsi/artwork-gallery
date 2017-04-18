package it.micheleorsi.repository;

import it.micheleorsi.domain.Art;

import java.time.LocalDateTime;
import java.util.List;

public interface ArtSearchByDate
{

  List<Art> getRecentArt(LocalDateTime elderLimit, LocalDateTime newerLimit);

}
