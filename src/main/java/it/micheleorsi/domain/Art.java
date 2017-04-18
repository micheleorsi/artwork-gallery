package it.micheleorsi.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public final class Art
{
  private final String name;
  private final ArtType type;
  private final String artistName;
  private final BigDecimal askingPrice;
  private final LocalDateTime dateTimeCreated;

  public Art(String name, ArtType type, String artistName, BigDecimal askingPrice,
    LocalDateTime dateTimeCreated)
  {
    this.name = name;
    this.type = type;
    this.artistName = artistName;
    this.askingPrice = askingPrice;
    this.dateTimeCreated = dateTimeCreated;
  }

  public ArtType getType()
  {
    return type;
  }

  public BigDecimal getAskingPrice()
  {
    return askingPrice;
  }

  public String getArtistName()
  {
    return artistName;
  }

  public LocalDateTime getDateTimeCreated()
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

  public static class Builder
  {
    private String name;
    private ArtType type;
    private String artistName;
    private BigDecimal askingPrice;
    private LocalDateTime dateTimeCreated;

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
      if(Optional.ofNullable(askingPrice).orElse(BigDecimal.ONE).compareTo(BigDecimal.ZERO)<=0)
      {
        throw new IllegalArgumentException("asking price should be positive");
      }
      return new Art(
        this.name,
        this.type,
        this.artistName,
        Optional.ofNullable(this.askingPrice).orElse(null),
        Optional.ofNullable(this.dateTimeCreated).orElse(null)
      );
    }

    public Builder withAskingPrice(String askingPrice)
    {
      this.askingPrice = new BigDecimal(askingPrice);
      return this;
    }

    public Builder withDateTimeCreated(LocalDateTime dateTimeCreated)
    {
      this.dateTimeCreated = dateTimeCreated;
      return this;
    }
  }
}
