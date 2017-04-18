# Artwork gallery

## Usage

Launch all the tests (unit and integration ones):

```bash
mvn clean verify
```

## Decisions

During developed I made some decisions. Here is the list:

* **Art** class: in the text it is mentioned Date, I used a timestamp because it's usually convenient to have also the time of object creation
* **Art** class: I added a bit of semantic in the _askingPrice_ field, by forcing it to be positive
* I considered _Date created_ optional, since mandatory was not specified
* following interface segregation principle, I splitted the Gallery interface into more specific ones
* in method **Gallery#getArtByArtist** no order was specified, so I just returned in random order
* I changed a bit the signature (and the meaning) of method **Gallery#getRecentArt()** because it's a good practice to pass a global status (as timing actually is) from outside a method
* In the method **Gallery#getRecentArt()** I considered the limits excluded
* In the method **Gallery#getArtByPrice()** I considered the limits excluded, as it is with **Gallery#getRecentArt()**