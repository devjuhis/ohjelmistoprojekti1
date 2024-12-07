# Tapahtuman poistaminen

Poistaa valitun tapahtuman, mikäli toiminnon tekevällä käyttäjällä on ADMIN-tason oikeudet.

**URL** : `/api/tapahtumat/{id}`

**URL Parameters** : `{id}`, jossa ID on poistettavan tapahtuman tapahtumaId.

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data** : `{}`

## Success Response

**Condition** : Poistettava tapahtuma tulee olla olemassa.

**Code** : `204 No Content`

**Content** : `{}`

## Error Responses

**Condition** : Jos tapahtumaa yritetään poistaa ID:llä, joka ei ole käytössä millään tapahtumalla.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### Or

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`
