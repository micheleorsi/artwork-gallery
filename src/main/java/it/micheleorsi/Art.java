package it.micheleorsi;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public final class Art implements Comparable<Art>
{
  private final String name;
  private final ArtType type;
  private final String artistName;
  private final Optional<BigDecimal> askingPrice;
  private final Optional<LocalDateTime> dateTimeCreated;

  private Art(Builder builder)
  {
    this.name = builder.name;
    this.type = builder.type;
    this.artistName = builder.artistName;
    this.askingPrice = builder.askingPrice;
    this.dateTimeCreated = builder.dateTimeCreated;
  }

  public ArtType getType()
  {
    return type;
  }

  public Optional<BigDecimal> getAskingPrice()
  {
    return askingPrice;
  }

  public String getArtistName()
  {
    return artistName;
  }

  public Optional<LocalDateTime> getDateTimeCreated()
  {
    return dateTimeCreated;
  }

  public String getName()
  {

    return name;
  }

  @Override
  public int hashCode()
  {
    return Objects.hashCode(this.name);
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Art art = (Art) obj;
    return Objects.equals(name, art.name)
      && Objects.equals(artistName, art.artistName)
      && Objects.equals(type, art.type);
  }

  @Override
  public String toString()
  {
    final StringBuilder sb = new StringBuilder("Art{");
    sb.append("name='").append(name).append('\'');
    sb.append(", type=").append(type);
    sb.append(", artistName='").append(artistName).append('\'');
    sb.append(", askingPrice=").append(askingPrice);
    sb.append(", dateTimeCreated=").append(dateTimeCreated);
    sb.append('}');
    return sb.toString();
  }

  @Override
  public int compareTo(Art o)
  {
    return this.getName().compareTo(o.getName());
  }

  public static class Builder
  {
    private String name;
    private ArtType type;
    private String artistName;
    private Optional<BigDecimal> askingPrice = Optional.empty();
    private Optional<LocalDateTime> dateTimeCreated = Optional.empty();

    public Builder withName(String name)
    {
      this.name = name;
      return this;
    }

    public Builder withType(ArtType type)
    {
      this.type = type;
      return this;
    }

    public Builder withArtistName(String artistName)
    {
      this.artistName = artistName;
      return this;
    }

    public Art build()
    {
      if(name == null || type == null || artistName == null)
      {
        throw new IllegalStateException("mandatory fields needed");
      }
      if(askingPrice.orElse(BigDecimal.ONE).compareTo(BigDecimal.ZERO)<=0)
      {
        throw new IllegalArgumentException("asking price should be positive");
      }
      return new Art(this);
    }

    public Builder withAskingPrice(String askingPrice)
    {
      this.askingPrice = Optional.ofNullable(new BigDecimal(askingPrice));
      return this;
    }

    public Builder withDateTimeCreated(LocalDateTime dateTimeCreated)
    {
      this.dateTimeCreated = Optional.ofNullable(dateTimeCreated);
      return this;
    }
  }
}
