# Kaikkien tulevien tapahtumien tiedot

Näyttää kaikki tiedot tulevista tapahtumista.

**URL** : `/api/tapahtumat/tulevat`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN- tai USER -tason oikeudet. 

**Data constraints** : `{}`

## Success Responses

**Condition** : Admin- ja User -tason käyttäjät näkevät ohjelmaan syötetyt tapahtumat.

**Code** : `200 OK`

**Content** : Tässä esimerkissä haetaan kaikki tulevat tapahtumat ja API-kutsun vastauksena näytetään kaikki kaksi tapahtumaa, joiden ajankohta on tulevaisuudessa.

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

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN- tai USER-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`