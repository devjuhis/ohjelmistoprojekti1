# Kaikkien maksutapahtumien tiedot

Näyttää tiedot kaikista maksutapahtumista.

**URL** : `/api/maksutapahtumat`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data**: `{}`

## Success Response

**Condition** : Pyynnön tekevällä käyttäjällä tulee olla ADMIN-tason oikeudet.

**Code** : `200 OK`

**Content example**

```json
[
    {
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
    {
        "maksutapahtumaId": 2,
        "hintayhteensa": 0.0,
        "aikaleima": "2024-10-20T13:13:54.453078",
        "kayttaja": {
            "etunimi": "matti",
            "sukunimi": "esimerkki",
            "salasana": "salasana",
            "kayttajatunnus": "matti123",
            "oikeus": "ADMIN",
            "kayttajaid": 1
        },
        "removed": false
    }
]
```

## Error Responses

!!Autentikointia ei ole vielä tehty!!

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`