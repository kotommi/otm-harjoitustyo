![class diagram](http://yuml.me/56b1a7e2.png)
Sovelluksen hajautusta kuvaava pakkaus/luokkakaavio:
![pakkauskaavio](link)
Sovelluksen toimintaa kuvaava sekvenssikaavio:
![sekvenssikaavio](link)
Kuvattuna peruslaskimen evaluointisekvenssi. Käyttäjä kirjoittaa lausekkeensa ja painaa =-nappulaa tai enteriä.
DoubleEvaluator hoitaa Stringin parsimisen koneen ymmärtämään logiikkaan ja laskee lopputuloksen. Result tallennetaan luokkamuuttujaan jotta sitä voi käyttää UIn kautta halutessaan. Sitten double-muotoinen result käännetään Stringiksi Util-luokan avulla, joka mm. muotoilee sen mukavan näköiseksi. 
Kun tämä on suoritetttu resultstring lisätään tuloskenttään ja syötekenttä tyhjennetään. Nyt käyttäjä näkee tuloksensa ja voi jatkaa toimiaan
