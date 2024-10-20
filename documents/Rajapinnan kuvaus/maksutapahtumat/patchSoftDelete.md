# Maksutapahtuman Soft Delete

Merkitään maksutapahtuma poistetuksi päivittämällä removed-kenttää.

**URL** : `/api/maksutapahtumat/{id}`

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava USER-tason oikeudet. 


## Success Response

**Condition** : Maksutapahtuma on olemassa sekä removed-kenttä on false.

**Code** : `200 OK`

**Content example**

```json
{
    "maksutapahtumaId": 2,
    "hintayhteensa": 0.0,
    "aikaleima": "2024-10-20T13:03:08.960367",
    "kayttaja": {
        "etunimi": "matti",
        "sukunimi": "esimerkki",
        "salasana": "salasana",
        "kayttajatunnus": "matti123",
        "oikeus": "ADMIN",
        "kayttajaid": 1
    },
    "removed": true
}
```

## Error Responses

**Condition** : Jos annettun maksutapahtuman id:llä ei löydy

**Code** : `404 NOT FOUND`

**Content example**: `{}`

### Or

**Condition** : Jos annettu maksutapahtuma on merkitty poistetuksi

**Code** : `410 GONE`

**Content example**: `{}`

### Or

!!Autentikointia ei ole vielä tehty!!

**Condition** : Endpointia käyttävällä käyttäjällä ei ole USER-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`
