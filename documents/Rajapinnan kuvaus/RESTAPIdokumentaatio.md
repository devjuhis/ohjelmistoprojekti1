# Rajapinnan kuvaus

Lopullisen toimivan palvelun base-URL tulee olemaan 'http://ticketguru.com'.

## Avoimet endpointit

Kaikille avoin endpoint kirjautumiseen. HUOM. kirjautumistoimintoa ei vielä tehty.

* [Login](login.md) : `POST /api/login/`

## Endpointit jotka vaativat autentikaation

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
* [Poista käyttäjä](kayttajat/delete.md) : `DELETE /api/kayttajat/{id}`

### Tapahtumien hallinta

Endpointit tapahtumien hallintaan. Ainoastaan admin-tason käyttäjä voi lisätä, muokata ja poistaa tapahtumia, mutta sekä admin- että user-tasolla voi hakea kaikkien tai yksittäisen tapahtuman tiedot. Kirjautumattomilta käyttäjiltä käyttö on estetty.

* [Näytä kaikki tapahtumat](tapahtumat/getAll.md) : `GET /api/tapahtumat`
* [Näytä kaikki tapahtumat](tapahtumat/getAllFuture.md) : `GET /api/tapahtumat/tulevat`
* [Näytä yksittäinen tapahtuma](tapahtumat/get.md) : `GET /api/tapahtumat/{id}`
* [Luo uusi tapahtuma](tapahtumat/post.md) : `POST /api/tapahtumat`
* [Päivitä tapahtuman yhtä tai useampaa tietoa](tapahtumat/patch.md) : `PATCH /api/tapahtumat/{id}`
* [Poista tapahtuma](tapahtumat/delete.md) : `DELETE /api/tapahtumat/{id}`

### Hinnastojen hallinta

Endpointit hinnastojen hallintaan. Ainoastaan admin-tason käyttäjä voi hallita hinnastoja, joten perustason käyttäjiltä sekä kirjautumattomilta käyttäjiltä käyttö on estetty.

* [Näytä kaikki hinnastot](hinnastot/getAll.md) : `GET /api/hinnastot`
* [Näytä yksittäinen hinnasto](hinnastot/get.md) : `GET /api/hinnasto/{id}`
* [Luo uusi hinnasto](hinnastot/post.md) : `POST /api/hinnastot`
* [Päivitä hinnastoa](hinnastot/patch.md) : `PATCH /api/hinnastot/{id}`
* [Poista hinnasto](hinnastot/delete.md) : `DELETE /api/hinnastot/{id}`

### Lippujen hallinta

Endpointit lippujen hallintaan. Ainoastaan admin-tason käyttäjä voi hallita lippuja, joten perustason käyttäjiltä sekä kirjautumattomilta käyttäjiltä käyttö on estetty.

* [Näytä kaikki liput](liput/getAll.md) : `GET /api/liput`
* [Näytä yksittäinen lippu](liput/get.md) : `GET /api/liput/{id}`
* [Näytä yksittäisen tapahtuman liput](liput/getTapahtumaLiput.md) : `GET /api/tapahtumat/{id}/liput`
* [Näytä yksittäisen tapahtuman poistetut liput](liput/getTapahtumaRemovedLiput.md) : `GET /api/tapahtumat/{id}/poistetutliput`
* [Luo uusi lippu](liput/post.md) : `POST /api/liput`
* [Päivitä lippua](liput/put.md) : `PUT /api/liput/{id}`
* [Poista lippu](liput/delete.md) : `DELETE /api/liput/{id}`
* [Soft delete lippu](liput/softDelete.md) : `DELETE /api/liput/softdelete/{id}`

### Maksutapahtumien hallinta

Endpointit maksutapahtumien hallintaan. Ainoastaan admin-tason käyttäjä voi nähdä kaikki maksutapahtumat, joten user-tason käyttäjiltä sekä kirjautumattomilta käyttäjiltä käyttö on estetty. User-tason käyttäjällä oikeus luoda maksutapahtuma sekä nähdä luotu maksutapahtuma.

* [Näytä kaikki maksutapahtumat](maksutapahtumat/getAll.md) : `GET /api/maksutapahtumat`
* [Näytä yksittäinen maksutapahtuma](maksutapahtumat/get.md) : `GET /api/maksutapahtumat/{id}`
* [Näytä yksittäisen maksutapahtuman liput](maksutapahtumat/getTickets.md) : `GET /api/maksutapahtumat/{id}/liput`
* [Luo uusi maksutapahtuma](maksutapahtumat/post.md) : `POST /api/maksutapahtumat`
* [Maksutapahtuman soft delete](maksutapahtumat/patchSoftDelete.md) : `PATCH /api/maksutapahtumat/{id}`

## Endpointit myyntitapahtuman luomiseen

Endpointit uuden maksutapahtuman luomiseen.

* [Luo uusi maksutapahtuma](maksutapahtumat/post.md) : `POST /api/maksutapahtumat`
* [Luo uusi lippu](liput/post.md) : `POST /api/liput`
* [Näytä yksittäinen maksutapahtuma](maksutapahtumat/get.md) : `GET /api/maksutapahtumat/{id}`
* [Näytä yksittäisen maksutapahtuman liput](maksutapahtumat/getTickets.md) : `GET /api/maksutapahtumat/{id}/liput`
* [Näytä yksittäisen tapahtuman hinnastot]: `GET /api/tapahtumat/{id}/hinnastot`
