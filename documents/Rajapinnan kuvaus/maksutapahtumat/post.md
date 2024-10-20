# Uuden maksutapahtuman luominen

Luo uuden maksutapahtuman.

**URL** : `/api/maksutapahtumat`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava USER-tason oikeudet. 

**Data constraints**

Bodyssa on lähetettävä käyttäjän ID. Hintayhteensa lasketaan erikseen lippujen lisäyksen yhteydessä.

```json
{
    "kayttaja": "[Long]"
}
```

**Data example** Kaikki kentät on täytettävä.

```json
{
     "kayttaja": {
        "kayttajaid": 1
    }
}
```

## Success Response

**Condition** : Käyttäjä on olemassa sekä tieto on oikeassa muodossa 

**Code** : `201 CREATED`

**Content example**

```json
{
    "maksutapahtumaId": 2,
    "hintayhteensa": 0.0,
    "aikaleima": "2024-10-20T13:13:54.4530782",
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
```

## Error Responses

**Condition** : Jos tietoja puuttuu, tai annettu data on virheellisessä muodossa.

**Code** : `400 BAD REQUEST`

**Content example**: `{}`

### Or

!!Autentikointia ei ole vielä tehty!!

**Condition** : Endpointia käyttävällä käyttäjällä ei ole USER-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`
