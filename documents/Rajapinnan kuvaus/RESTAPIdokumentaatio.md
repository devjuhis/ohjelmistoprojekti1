# Rajapinnan kuvaus

Lopullisen toimivan palvelun base-URL tulee olemaan 'http://ticketguru.com'.

## Open Endpoints

Kaikille avoin endpoint kirjautumiseen. HUOM. kirjautumistoimintoa ei vielä tehty.

* [Login](login.md) : `POST /api/login/`

## Endpoints that require Authentication

Käyttäjä-taulun REST-rajapinnan käyttö vaatii kirjautumista, sekä admin-tason käyttäjän oikeuksia. HUOM. kirjautumistoimintoa ei vielä tehty.

### Kirjautuneen käyttäjän tiedot

!! KIRJAUTUMISTOIMINTOA EI VIELÄ TEHTY !!
Each endpoint manipulates or displays information related to the User whose
Token is provided with the request:

* [Show info](user/get.md) : `GET /api/user/`
* [Update info](user/put.md) : `PUT /api/user/`

### Käyttäjien hallinta

Endpointit käyttäjien hallintaan. Ainoastaan admin-tason käyttäjä voi hallita muita käyttäjiä, joten perustason käyttäjiltä sekä kirjautumattomilta käyttäjiltä käyttö on estetty.

* [Näytä kaikki käyttäjät](kayttajat/getAll.md) : `GET /api/kayttajat`
* [Näytä yksittäinen käyttäjä](kayttajat/get.md) : `GET /api/kayttajat/{id}`
* [Luo uusi käyttäjä](kayttajat/post.md) : `POST /api/kayttajat`
* [Päivitä käyttäjän yhtä tai useampaa tietoa](kayttajat/patch.md) : `PATCH /api/kayttajat/{id}`
* [Poista käyttäjä](accounts/delete.md) : `DELETE /api/kayttajat/{id}`