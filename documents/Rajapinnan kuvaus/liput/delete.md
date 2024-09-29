# Lipun poistaminen

Poistaa valitun lipun, mikäli toiminnon tekevällä käyttäjällä on ADMIN-tason oikeudet.

**URL** : `/api/liput/{id}`

**URL Parameters** : `{id}`, jossa ID on poistettavan käyttäjän kayttajaId.

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data** : `{}`

## Success Response

**Condition** : Poistettava lippu tulee olla olemassa.

**Code** : `204 No Content`

**Content** : `{}`

## Error Responses

**Condition** : Jos lippua yritetään poistaa ID:llä, joka ei ole käytössä millään lipulla.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### Or

!!Autentikointia ei ole vielä tehty!!

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`