# Yksittäisen lipun tiedot

Näyttää tiedon yksittäisestä lipusta.

**URL** : `/api/liput/{id}`

**URL Parameters** : `{id}`, jossa ID on lipun lippuId.

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava USER-tason oikeudet. 

**Data**: `{}`

## Success Response

**Condition** : Lippu on olemassa, ja pyynnön tekevällä käyttäjällä on USER-tason oikeudet.

**Code** : `200 OK`

**Content example**

```json
{
    "lippuId": 1,
    "tapahtuma": {
        "tapahtumaId": 1,
        "nimi": "Uusi tapahtuma",
        "aika": "2024-12-14",
        "paikka": "Olympiastadion",
        "kuvaus": "Hyvä tapahtuma :D",
        "lippumaara": 600,
        "ennakkomyynti": "2024-12-13"
    },
    "hinnasto": {
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
    },
    "maksutapahtuma": {
        "maksutapahtumaId": 1,
        "hintayhteensa": 36.0,
        "aikaleima": "2024-10-15T13:39:26.044925",
        "kayttaja": {
            "kayttajaid": 1
        }
    },
    "kaytetty": false,
    "maara": 1,
    "removed": false,
    "koodi": 688a082e-21ac-4735-ae2b-786703164f2d,
    "luontiaika": 2024-12-07T13:41:44.982957
}
```

## Error Responses

**Condition** : Lippua ei ole olemassa annetulla ID:llä.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### Or

**Condition** : Lippu on poistettu soft deletellä

**Code** : `410 Gone`

**Content** : `{}`

### OR

**Condition** : Käyttäjällä ei ole USER-tason oikeuksia

**Code** : `403 FORBIDDEN`

**Content** : `{}`
