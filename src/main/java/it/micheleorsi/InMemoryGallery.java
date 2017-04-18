package it.micheleorsi;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;

public class InMemoryGallery implements Gallery
{
  private final List<Art> list;

  public InMemoryGallery(Art... arts)
  {
    list = new Vector<>(Arrays.asList(arts));
  }

  @Override
  public void addArt(Art art)
  {
    list.add(art);
  }

  @Override
  public void deleteArt(Art art)
  {
    list.removeIf(a -> a.equals(art));
  }

  @Override
  public List<Art> getAllArt()
  {
    return list;
  }

  @Override
  public List<String> getArtists(Comparator<String> tComparator)
  {
    return list
      .parallelStream()
      .map(Art::getArtistName)
      .sorted(tComparator)
      .distinct()
      .collect(Collectors.toList());
  }

  @Override
  public List<Art> getArtByArtist(String artist)
  {
    return list
      .parallelStream()
      .filter(a -> a.getArtistName().equals(artist))
      .collect(Collectors.toList());
  }

  @Override
  public List<Art> getRecentArt(LocalDateTime elderLimit, LocalDateTime newerLimit)
  {
    return list
      .parallelStream()
      .filter(a -> a.getDateTimeCreated().orElse(LocalDateTime.MIN).isAfter(elderLimit)
                 && a.getDateTimeCreated().orElse(LocalDateTime.MAX).isBefore(newerLimit))
      .collect(Collectors.toList());
  }

  @Override
  public List<Art> getArtByPrice(Optional<BigDecimal> lowerLimit, Optional<BigDecimal> upperLimit)
  {
    return list
      .parallelStream()
      .filter(a -> a.getAskingPrice().isPresent() == true)
      .filter(b -> b.getAskingPrice().get().compareTo(lowerLimit.orElse(BigDecimal.ZERO))>0
                && b.getAskingPrice().get().compareTo(upperLimit.orElse(b.getAskingPrice().get().add(new BigDecimal("1"))))<0)
      .collect(Collectors.toList());
  }
}
