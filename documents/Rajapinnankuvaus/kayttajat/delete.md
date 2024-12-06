# Käyttäjän poistaminen

Poistaa valitun käyttäjän, mikäli toiminnon tekevällä käyttäjällä on ADMIN-tason oikeudet.

**URL** : `/api/kayttajat/{id}`

**URL Parameters** : `{id}`, jossa ID on poistettavan käyttäjän kayttajaId.

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data** : `{}`

## Success Response

**Condition** : Poistettava käyttäjä tulee olla olemassa.

**Code** : `204 No Content`

**Content** : `{}`

## Error Responses

**Condition** : Jos käyttäjää yritetään poistaa ID:llä, joka ei ole käytössä millään käyttäjällä.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### Or

**Condition** : Pyynnössä ei ole mukana toimivaa JWT-tokenia tai endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`