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
     "kayttaja": {
        "kayttajaId": "[Long]"
    }
}
```

**Data example** Kaikki kentät on täytettävä.

```json
{
     "kayttaja": {
        "kayttajaId": 1
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

**Content example**:

 ```json
 {
    "message": "Käyttäjää ei löydy",
    "statusCode": 400,
    "timestamp": "2024-10-27T18:12:22.2896226"
}```

### Or

**Condition** : Endpointia käyttävällä käyttäjällä ei ole USER-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`
