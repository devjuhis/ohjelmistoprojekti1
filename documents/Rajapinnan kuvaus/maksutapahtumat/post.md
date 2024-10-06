# Uuden maksutapahtuman luominen

Luo uuden maksutapahtuman.

**URL** : `/api/maksutapahtumat`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava USER-tason oikeudet. 

**Data constraints**

Bodyssa on lähetettävä aikaleima sekä käyttäjän ID. Hintayhteensa lasketaan erikseen lippujen lisäyksen yhteydessä.

```json
{
    "aikaleima": "[timestamp]",
    "kayttaja": "[Long]"
}
```

**Data example** Kaikki kentät on täytettävä.

```json
{
    "aikaleima": "2024-10-04T11:54:11.701583",
    "kayttaja": {
        "kayttajaid": 1
    }
}
```

## Success Response

**Condition** : Käyttäjä on olemassa sekä tieto on oikeassa muodossa 

**Code** : `200 OK`

**Content example**

```json
{
    "maksutapahtumaId": 3,
    "hintayhteensa": 0.0,
    "aikaleima": "2024-10-04T11:54:11.701583",
    "kayttaja": {
        "etunimi": null,
        "sukunimi": null,
        "salasana": null,
        "kayttajatunnus": null,
        "oikeus": null,
        "kayttajaid": 1
    }
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
