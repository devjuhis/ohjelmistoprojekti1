# Yksittäisen tapahtuman poistetut liput

Näyttää tiedot tapahtuman poistetuista lipuista.

**URL** : `/api/tapahtuma/{id}/poistetutliput`

**URL Parameters** : `{id}`, jossa ID on tapahtuman id.

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava USER-tason oikeudet. 

**Data**: `{}`

## Success Response

**Condition** : Lippu on olemassa, ja pyynnön tekevällä käyttäjällä on USER-tason oikeudet.

**Code** : `200 OK`

**Content** : Tässä esimerkissä käyttäjä näkee tapahtuman 1 kaikki poistetut liput

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
            "hintayhteensa": 24.0,
            "aikaleima": "2024-10-15T13:39:26.044925",
            "kayttaja": {
                "etunimi": "matti",
                "sukunimi": "esimerkki",
                "salasana": "salasana",
                "kayttajatunnus": "matti123",
                "oikeus": "ADMIN",
                "kayttajaid": 1
            }
        },
        "kaytetty": false,
        "maara": 1,
        "removed": true
    },
    {
        "lippuId": 4,
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
            "hintayhteensa": 24.0,
            "aikaleima": "2024-10-15T13:39:26.044925",
            "kayttaja": {
                "etunimi": "matti",
                "sukunimi": "esimerkki",
                "salasana": "salasana",
                "kayttajatunnus": "matti123",
                "oikeus": "ADMIN",
                "kayttajaid": 1
            }
        },
        "kaytetty": false,
        "maara": 1,
        "removed": true
    }
]
```

## Error Responses

**Condition** : Tapahtumaa ei ole olemassa annetulla ID:llä.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### OR

**Condition** : Käyttäjällä ei ole USER-tason oikeuksia

**Code** : `403 FORBIDDEN`

**Content** : `{}
