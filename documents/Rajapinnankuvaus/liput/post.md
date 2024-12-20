# Uuden lipun luominen

Luo uuden lipun.

**URL** : `/api/liput`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava USER-tason oikeudet. 

**Data constraints**

Käyttäjän ID-generoituu automaattisesti. Bodyssa on lähetettävä tapahtumaId, hinnastoId, maksutapahtumaId,
käytetty ->1 tai ei käytetty -> 0 ja määrä 1 tai -1 (palautettu).

```json
{
    "tapahtumaId": "[long]",
    "hinnastoId": "[long]",
    "maksutapahtumaId": "[long]"
}
```

**Data example** Kaikki kentät on täytettävä.

```json
{
    "tapahtuma": {
        "tapahtumaId": 1
    },
    "hinnasto": {
        "hinnastoid": 1
    },
    "maksutapahtuma": {
        "maksutapahtumaId": 1
    }
}
```

## Success Response

**Condition** : Kaikki lipulle tulevat tiedot on annettu ohjeistuksien mukaan.

**Code** : `200 OK`

**Content example**

```json
{
    "lippuId": 5,
    "tapahtuma": {
        "tapahtumaId": 1,
        "nimi": null,
        "aika": null,
        "paikka": null,
        "kuvaus": null,
        "lippumaara": 0,
        "ennakkomyynti": null
    },
    "hinnasto": {
        "tapahtuma": null,
        "hintaluokka": null,
        "hinta": 0.0,
        "hinnastoid": 1
    },
    "maksutapahtuma": {
        "maksutapahtumaId": 1,
        "hintayhteensa": 0.0,
        "aikaleima": null,
        "kayttaja": null
    },
    "kaytetty": false,
    "maara": 1,
    "removed": false
}
```

## Error Responses

**Condition** : Jos tietoja puuttuu, tai annettu data on virheellisessä muodossa.

**Code** : `400 BAD REQUEST`

**Content example**: `{Tapahtumaa ei löydy id: x}`

### OR

**Condition** : Käyttäjällä ei ole USER-tason oikeuksia

**Code** : `403 FORBIDDEN`

**Content** : `{}
