# Yksittäisen maksutapahtuman tiedot

Näyttää tiedon yksittäisestä maksutapahtumasta.

**URL** : `/api/maksutapahtumat/{id}`

**URL Parameters** : `{id}`, jossa ID on maksutapahtuman maksutapahtumaId.

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data**: `{}`

## Success Response

**Condition** : Maksutapahtuman tulee olla olemassa ja pyynnön tekevällä käyttäjällä on ADMIN-tason oikeudet.

**Code** : `200 OK`

**Content example**

```json
{
    "hintayhteensa": 215.15,
    "aikaleima": "2024-09-28 12:48:44.020719",
    "kayttajaid": 1
}
```

## Error Responses

**Condition** : Maksutapahtumaa ei ole olemassa annetulla maksutapahtumaId:llä.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### Or

!!Autentikointia ei ole vielä tehty!!

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`