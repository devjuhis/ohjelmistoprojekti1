# Maksutapahtuman lippujen tiedot

Näyttää tiedot maksutapahtumaan liittyvistä lipuista.

**URL** : `/api/maksutapahtumat/{id}/liput`

**URL Parameters** : `{id}`, jossa ID on maksutapahtuman maksutapahtumaId.

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava USER-tason oikeudet. 

**Data**: `{}`

## Success Response

**Condition** : Maksutapahtuman tulee olla olemassa ja pyynnön tekevällä käyttäjällä on USER-tason oikeudet.

**Code** : `200 OK`

**Content example**

```json
[
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
            "aikaleima": "2024-10-20T13:10:19.27427",
            "kayttaja": {
                "etunimi": "matti",
                "sukunimi": "esimerkki",
                "salasana": "salasana",
                "kayttajatunnus": "matti123",
                "oikeus": "ADMIN",
                "kayttajaid": 1
            },
            "removed": false
        },
        "kaytetty": false,
        "maara": 1,
        "removed": false
    }
]
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