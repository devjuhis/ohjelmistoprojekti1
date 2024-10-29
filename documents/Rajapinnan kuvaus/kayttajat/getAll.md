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
        "kayttajaId": 1,
        "etunimi": "matti",
        "sukunimi": "esimerkki",
        "salasana": "$2a$10$4wtEm5kqOB8/vfGsjlxpf.HKPIvyr4AWb1tz66GRRm8ZIwRyRsnCK",
        "kayttajatunnus": "matti123",
        "oikeus": "ADMIN",
        "aktiivisuus": true
    },
    {
        "kayttajaId": 2,
        "etunimi": "matti2",
        "sukunimi": "esimerkki2",
        "salasana": "$2a$10$TJaqm9Bo0f97xouTCJiE0OnYIbFITHH53LlAc8R3QpcAO82p2etLq",
        "kayttajatunnus": "matti321",
        "oikeus": "USER",
        "aktiivisuus": true
    }
]
```

### Or

**Condition** : Pyynnössä ei ole mukana toimivaa JWT-tokenia tai endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`