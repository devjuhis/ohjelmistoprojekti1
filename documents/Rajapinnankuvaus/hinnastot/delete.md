# Hinnaston poistaminen

Poistaa valitun hinnaston, mikäli toiminnon tekevällä käyttäjällä on ADMIN-tason oikeudet.

**URL** : `/api/hinnastot/{id}`

**URL Parameters** : `{id}`, jossa ID on poistettavan hinnaston hinnastoId.

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data** : `{}`

## Success Response

**Condition** : Poistettava hinta tulee olla olemassa tai hinnasto ei ole linkitetty olemassa olevaan lippuu.

**Code** : `204 No Content`

**Content** : `{}`

## Error Responses

**Condition** : Jos hinnastoa yritetään poistaa ID:llä, joka ei ole käytössä millään hinnasto tai hinnasto on linkitetty olemassa olevaan lippuun.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### Or

!!Autentikointia ei ole vielä tehty!!

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`
