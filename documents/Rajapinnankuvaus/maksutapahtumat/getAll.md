# Kaikkien maksutapahtumien tiedot

Näyttää tiedot kaikista maksutapahtumista.

**URL** : `/api/maksutapahtumat`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava USER- tai ADMIN-tason oikeudet.

**Data**: `{}`

## Success Response

**Condition** : Pyynnön tekevällä käyttäjällä on USER- tai ADMIN-tason oikeudet.

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

**Condition** : Endpointia käyttävällä käyttäjällä ei ole USER- tai ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`

### OR

**Condition** : Maksutapahtumia ei ole.

**Code** : `404 NOT FOUND`

**Content** : `{}`
