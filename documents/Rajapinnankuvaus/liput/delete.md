# Lipun poistaminen

Poistaa valitun lipun, mikäli toiminnon tekevällä käyttäjällä on USER-tason oikeudet.

**URL** : `/api/liput/{id}`

**URL Parameters** : `{id}`, jossa ID on poistettavan lipun lippuId.

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava USER-tason oikeudet. 

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


**Condition** : Endpointia käyttävällä käyttäjällä ei ole USER-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`
