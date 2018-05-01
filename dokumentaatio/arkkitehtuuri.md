## Luokkakaavio
![class diagram](http://yuml.me/56b1a7e2.png)


## Sovelluksen hajautusta kuvaava pakkaus/luokkakaavio:
![pakkauskaavio](https://github.com/kotommi/otm-harjoitustyo/blob/master/dokumentaatio/package.png)


## Sovelluksen toimintaa kuvaava sekvenssikaavio:
![sekvenssikaavio](https://github.com/kotommi/otm-harjoitustyo/blob/master/dokumentaatio/Basic%20evaluation%20sequence.png)

Kuvattuna peruslaskimen evaluointisekvenssi. Käyttäjä kirjoittaa lausekkeensa ja painaa =-nappulaa tai enteriä.
DoubleEvaluator hoitaa Stringin parsimisen koneen ymmärtämään logiikkaan ja laskee lopputuloksen. Result tallennetaan luokkamuuttujaan jotta sitä voi käyttää UIn kautta halutessaan. Sitten double-muotoinen result käännetään Stringiksi Util-luokan avulla, joka mm. muotoilee sen mukavan näköiseksi. 
Kun tämä on suoritetttu resultstring lisätään tuloskenttään ja syötekenttä tyhjennetään. Nyt käyttäjä näkee tuloksensa ja voi jatkaa toimiaan


## Tiedon tallennus
Pakkauksen jcalculator.database luokka ScrollbackDao hoitaa laskuhistorian tallennuksen tietokantaan.


## Tiedostot
Sovellus tallentaa oletuksena historiaa tietokantaan scrollback.db.
Tätä voi muuttaa tarvittaessa konfiguraatio-tiedostossa config.properties muokkaamalla riviä dbFile=scrollback.db   
