# Yksittäisen hinnaston tiedot

Näyttää tiedon yksittäisestä hinnastosta.

**URL** : `/api/hinnastot/{id}`

**URL Parameters** : `{id}`, jossa ID on hinnaston hinnastoId.

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data**: `{}`

## Success Response

**Condition** : Hinnasto on olemassa, ja pyynnön tekevällä käyttäjällä on ADMIN-tason oikeudet.

**Code** : `200 OK`

**Content example**

```json
{
    "tapahtuma": {
        "tapahtumaId": 1,
        "nimi": "Uusi tapahtuma",
        "aika": "2024-12-14",
        "paikka": "Olympiastadion",
        "kuvaus": "Hyvä tapahtuma :D",
        "lippumaara": 600,
        "ennakkomyynti": "2024-12-13"
    },
    "hintaluokka": "opiskelija",
    "hinta": 12.0,
    "hinnastoid": 1
}
```

## Error Responses

**Condition** : Hinnastoa ei ole olemassa annetulla ID:llä.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### Or

!!Autentikointia ei ole vielä tehty!!

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`
