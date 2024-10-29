# Kaikkien tapahtumien tiedot

Näyttää kaikki tiedot kaikista tapahtumista.

**URL** : `/api/tapahtumat`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN- tai USER -tason oikeudet. 

**Data constraints** : `{}`

## Success Responses

**Condition** : Admin- ja User -tason käyttäjät näkevät kaikki ohjelmaan syötetyt tapahtumat.

**Code** : `200 OK`

**Content** : Tässä esimerkissä haetaan kaikki tapahtumat ja API-kutsun vastauksena näytetään kaikki kaksi tapahtumaa.

```json
[
    {
        "tapahtumaId": 1,
        "nimi": "Uusi tapahtuma",
        "aika": "2024-12-14",
        "paikka": "Olympiastadion",
        "kuvaus": "Hyvä tapahtuma :D",
        "lippumaara": 600,
        "ennakkomyynti": "2024-12-13"
    },
    {
        "tapahtumaId": 2,
        "nimi": "Postman Tour 2025",
        "aika": "2025-08-02",
        "paikka": "Ratinan stadion",
        "kuvaus": "Vuoden odotetuin",
        "lippumaara": 1000,
        "ennakkomyynti": "2025-08-01"
    }
]
```

### Or

!!Autentikointia ei ole vielä tehty!!

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`