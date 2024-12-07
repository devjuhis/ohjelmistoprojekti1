# Kaikkien hinnastojen tiedot

Näyttää kaikki tiedot kaikista hinnastoista.

**URL** : `/api/hinnastot`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data constraints** : `{}`

## Success Responses

**Condition** : Admin-tason käyttäjä näkee kaikki ohjelman hinnastot.

**Code** : `200 OK`

**Content** : Tässä esimerkissä ADMIN-tason käyttäjä näkee kaksi hinnastoa.

```json
[
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
    },
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
        "hintaluokka": "elakelainen",
        "hinta": 11.0,
        "hinnastoid": 2
    }
]
```

### Or

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`
