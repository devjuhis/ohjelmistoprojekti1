# Yksittäisen tapahtuman liput

Näyttää tiedot tapahtuman lipuista.

**URL** : `/api/tapahtuma/{id}/liput`

**URL Parameters** : `{id}`, jossa ID on tapahtuman id.

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava USER-tason oikeudet. 

**Data**: `{}`

## Success Response

**Condition** : Lippu on olemassa, ja pyynnön tekevällä käyttäjällä on USER-tason oikeudet.

**Code** : `200 OK`

**Content** : Tässä esimerkissä käyttäjä näkee tapahtuman 1 kaikki liput

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
            "hintayhteensa": 55.0,
            "aikaleima": "2024-09-29T19:03:30.831911",
            "kayttaja": {
                "etunimi": "matti",
                "sukunimi": "esimerkki",
                "salasana": "salasana",
                "kayttajatunnus": "matti123",
                "oikeus": "ADMIN",
                "kayttajaid": 1
            }
        },
        "kaytetty": 0,
        "maara": 1,
        "removed": false
    },
    {
        "lippuId": 2,
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
            "hintayhteensa": 55.0,
            "aikaleima": "2024-09-29T19:03:30.831911",
            "kayttaja": {
                "etunimi": "matti",
                "sukunimi": "esimerkki",
                "salasana": "salasana",
                "kayttajatunnus": "matti123",
                "oikeus": "ADMIN",
                "kayttajaid": 1
            }
        },
        "kaytetty": 0,
        "maara": 1,
        "removed": false
    },
    {
        "lippuId": 3,
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
            "hintayhteensa": 55.0,
            "aikaleima": "2024-09-29T19:03:30.831911",
            "kayttaja": {
                "etunimi": "matti",
                "sukunimi": "esimerkki",
                "salasana": "salasana",
                "kayttajatunnus": "matti123",
                "oikeus": "ADMIN",
                "kayttajaid": 1
            }
        },
        "kaytetty": 0,
        "maara": 1,
        "removed": false
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

