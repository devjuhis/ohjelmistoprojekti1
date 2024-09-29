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
        "hintayhteensa": 215.15,
        "aikaleima": "2024-09-28 12:48:44.020719",
        "kayttajaid": 1
    },
    {
        "hintayhteensa": 55.00,
        "aikaleima": "2024-09-28 12:48:44.020719",
        "kayttajaid": 2
    }
]
```

## Error Responses

!!Autentikointia ei ole vielä tehty!!

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`