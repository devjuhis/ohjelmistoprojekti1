# Kaikkien käyttäjien tiedot

Näyttää kaikki tiedot kaikista käyttäjistä.

**URL** : `/api/kayttajat`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data constraints** : `{}`

## Success Responses

**Condition** : Admin-tason käyttäjä näkee kaikki ohjelman käyttäjät.

**Code** : `200 OK`

**Content** : Tässä esimerkissä ADMIN-tason käyttäjä näkee kaksi käyttäjää. Käyttäjätunnus matti123 on ADMIN-tason käyttäjä,  kun taas käyttäjätunnus minna123 on perustason käyttäjä.

```json
[
    {
        "etunimi": "matti",
        "sukunimi": "esimerkki",
        "salasana": "salasana",
        "kayttajatunnus": "matti123",
        "oikeus": "ADMIN",
        "kayttajaid": 1
    },
    {
        "etunimi": "minna",
        "sukunimi": "esimerkki",
        "salasana": "salasana",
        "kayttajatunnus": "minna123",
        "oikeus": "USER",
        "kayttajaid": 2
    }
]
```

### Or

**Condition** : Pyynnössä ei ole mukana toimivaa JWT-tokenia tai endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`