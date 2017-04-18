package it.micheleorsi.repository;

import it.micheleorsi.domain.Art;

import java.math.BigDecimal;
import java.util.List;

public interface ArtByPrice
{
  List<Art> getArtByPrice(BigDecimal lowerLimit, BigDecimal upperLimit);

  List<Art> getArtWithPrice();

  List<Art> getArtByPriceHigherThan(BigDecimal lowerLimit);

  List<Art> getArtByPriceLowerThan(BigDecimal upperLimit);

}
