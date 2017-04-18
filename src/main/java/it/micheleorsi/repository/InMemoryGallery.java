package it.micheleorsi.repository;

import it.micheleorsi.domain.Art;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InMemoryGallery implements ArtGalleryRepository,ArtByPrice,ArtSearchByArtist,ArtSearchByDate,ArtistRepository
{
  private final List<Art> list;

  public InMemoryGallery(Art... arts)
  {
    list = new CopyOnWriteArrayList<>(Arrays.asList(arts));
  }

  @Override
  public void addArt(Art art)
  {
    list.add(art);
  }

  @Override
  public void deleteArt(Art art)
  {
    list.removeIf(obj -> obj.equals(art));
  }

  @Override
  public List<Art> getAllArt()
  {
    return list;
  }

  @Override
  public List<String> getArtists(Comparator<String> tComparator)
  {
    return getArtStream()
      .map(Art::getArtistName)
      .sorted(tComparator)
      .distinct()
      .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
  }

  @Override
  public List<Art> getArtByArtist(String artist)
  {
    return getArtStream()
      .filter(art -> art.getArtistName().equals(artist))
      .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
  }

  @Override
  public List<Art> getRecentArt(LocalDateTime elderLimit, LocalDateTime newerLimit)
  {
    return getArtStream()
      .filter(art -> Optional.ofNullable(art.getDateTimeCreated()).orElse(LocalDateTime.MIN).isAfter(elderLimit)
                 && Optional.ofNullable(art.getDateTimeCreated()).orElse(LocalDateTime.MAX).isBefore(newerLimit))
      .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
  }

  @Override
  public List<Art> getArtByPrice(BigDecimal lowerLimit, BigDecimal upperLimit)
  {
    return getArtStream()
      .filter(withAskingPrice().and(higherThan(lowerLimit).and(lowerThan(upperLimit))))
      .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
  }

  @Override
  public List<Art> getArtWithPrice()
  {
    return getArtStream()
      .filter(withAskingPrice())
      .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
  }

  @Override
  public List<Art> getArtByPriceHigherThan(BigDecimal lowerLimit)
  {
    return getArtStream()
      .filter(withAskingPrice().and(higherThan(lowerLimit)))
      .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
  }

  @Override
  public List<Art> getArtByPriceLowerThan(BigDecimal upperLimit)
  {
    return getArtStream()
       .filter(withAskingPrice().and(lowerThan(upperLimit)))
       .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
  }

  private Stream<Art> getArtStream()
  {
    return list
       .parallelStream();
  }

  private Predicate<Art> higherThan(BigDecimal lowerLimit)
  {
    return art -> art.getAskingPrice().compareTo(lowerLimit) > 0;
  }

  private Predicate<Art> lowerThan(BigDecimal upperLimit)
  {
    return art -> art.getAskingPrice().compareTo(upperLimit) < 0;
  }

  private Predicate<Art> withAskingPrice()
  {
    return art -> art.getAskingPrice() != null;
  }

}
