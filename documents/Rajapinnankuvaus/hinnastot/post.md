# Uuden hinnaston luominen

Luo uuden hinnaston.

**URL** : `/api/hinnastot`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data constraints**

Hinnaston ID-generoituu automaattisesti. Bodyssa on lähetettävä tapahtumaId, hintaluokka ja hinta

```json
{
    "tapahtumaId": "[long]",
    "hintaluokka": "[varchar 30]",
    "hinta": "[dobule]"
}
```

**Data example** Kaikki kentät on täytettävä.

```json
{
    "tapahtuma": {
        "tapahtumaId": 1
    },
    "hintaluokka": "aikuinen", 
    "hinta": 20
}
```

## Success Response

**Condition** : Kaikki hinnalle tulevat tiedot on annettu ohjeistuksien mukaan.

**Code** : `200 OK`

**Content example**

```json
{
    "tapahtuma": {
        "tapahtumaId": 1,
        "nimi": null,
        "aika": null,
        "paikka": null,
        "kuvaus": null,
        "lippumaara": 0,
        "ennakkomyynti": null
    },
    "hintaluokka": "aikuinen",
    "hinta": 20.0,
    "hinnastoid": 4
}
```

## Error Responses

**Condition** : Jos tietoja puuttuu, tai annettu data on virheellisessä muodossa.

**Code** : `400 BAD REQUEST`

**Content example**: `{}`

### Or

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`
