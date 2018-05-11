# Laskinprojekti

## Release
[jcalculator.jar](https://github.com/kotommi/otm-harjoitustyo/releases/tag/final)

## Dokumentaatio
[Käyttöohje](https://github.com/kotommi/otm-harjoitustyo/blob/master/dokumentaatio/ohje.md)

[Tuntikirjanpito](https://github.com/kotommi/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Vaatimusmäärittely](https://github.com/kotommi/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/kotommi/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testaus](https://github.com/kotommi/otm-harjoitustyo/blob/master/dokumentaatio/testi.md)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _jcalculator.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/kotommi/otm-harjoitustyo/blob/master/jcalculator/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
