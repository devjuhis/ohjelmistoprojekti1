# Projektidokumentaatio

### Tiimi: 
Hiltunen Ilona, Järvinen Juho, Keinänen Aleksi, Klenberg Eriika, Nevala Sanni

## Johdanto

Kyseessä on Haaga-Helia ammattikorkeakoulun Ohjelmistoprojekti 1-kurssitoteutuksen projektityö (syksy 2024), jossa luotiin kuvitteellisen asiakkaan tilaama lipunmyyntijärjestelmä.

### Järjestelmä

Asiakkaana toimii lipputoimisto, joka tarvitsee lipunmyyntijärjestelmän myyntipisteeseen. Lipputoimiston myyjä myy ja tulostaa asiakkailleen liput tapahtumiin lipunmyyntijärjestelmästä. Lipputoimisto voi lisätä ja muokata lipunmyyntijärjestelmässä tapahtumia ja myytävien lippujen tietoja sekä nähdä koostetusti myyntitapahtumat ja myyntiraportit. Lipuissa on QR-koodit, jotta liput voidaan merkitä käytetyksi asiakkaan saapuessa tapahtumaan. 

### Toteutus- ja toimintaympäristö

Projektissa tulemme käyttämään GitHubia versionhallintaan ja kehitystyöhön. Toimintaympäristömme pohjautuu Java-teknologioihin. Hyödynnämme Spring Boot-sovelluskehystä sekä palvelinpuolella toteutamme REST API-rajapinnan.
Käyttöliittymäteknologioista hyödynnämme React-kirjastoa. Järjestelmä on julkaistu Rahti2-palvelimelle.

## Järjestelmän määrittely

### Käyttäjäryhmät 

Järjestelmässä on user- ja admin-tason käyttäjiä. Lipunmyynnissä työskentelevillä myyjillä on user-käyttäjän suppeammat oikeudet, joilla voi luoda ja peruuttaa myyntitapahtumia, tulostaa sekä asiakkaan ostamat liput että ennakkomyynnin loputtua loput jäljelle jääneet liput ovimyyntiä varten. User voi tarkistaa onko lippu käyttämätön ja merkitä sen käytetyksi. Myymäläpäälliköllä sekä yrityksen johtoryhmän jäsenillä on admin-tason oikeudet, joilla voi User-käyttäjän oikeuksien lisäksi lisätä uusia tapahtumia, hallinnoida ja tarkastella tapahtuman tietoja sekä tarkastella myyntiraportteja. Admin voi hallita käyttäjiä tietokannasta käsin.

### Käyttötapauskaavio 

![Käyttötapauskaavio](documents/kayttajaroolit.png)

### Käyttötapausten kuvaus

Järjestelmän olennaisin tarkoitus on myydä sekä tulostaa asiakkaan haluama määrä lippuja oikeaan tapahtumaan. Myyntitapahtumaan liittyy olennaisesti hinnan hakeminen tietokannasta sekä mahdollisuus peruuttaa jo tehtyä maksutapahtumia. Tapahtuma ja sen hinnastot täytyy olla luotu ennen kuin lippuja aletaan myymään. Jos lippuja jää myymättä ennakkomyyntijakson aikana, loput liput tulostetaan ovimyyntiä varten.

Yritystoimintaa ylläpitäville tahoille on tärkeää saada tietoa tapahtumista esimerkiksi yrityksen talouden hoitamisen vuoksi, joten järjestelmästä tulee pystyä näkemään tapahtumien tietoja kuten myytyjen lippujen määrää. Lisäksi mahdollisissa muutostilanteissa tulee tapahtuman tietoja pystyä muokkaamaan, jotta järjestelmän tieto ei ole virheellistä. Jo järjestetyissä tapahtumissa muokkaus ei kuitenkaan ole enää mahdollista.

## Käyttöliittymä

Käyttöliittymän tärkeimmät näkymät ja niiden väliset siirtymät on esitelty käyttöliittymäkaavioina projektin käyttöliittymän repositoriossa  [op1-client](https://github.com/AbuAk1/op1-client?tab=readme-ov-file#k%C3%A4ytt%C3%B6liittym%C3%A4n-keskeiset-toiminnot)

## Tietokanta

### Luokkakaavio

![Luokkakaavio](documents/ticketguruLuokkakaavio.png)

> ### _Tapahtuma_
> _Tapahtuma-taulu sisältää myynnissä olevat tulevat tapahtumat, sekä jo järjestetyt tapahtumat. Tapahtumaan voi olla monia myytyjä lippuja, mutta lipun tapahtumia voi olla vain yksi. Admin luo uusia tapahtumia myytäväksi._
>
> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> tapahtumaId | int PK | Tapahtuman id
> nimi | varchar(60) | Tapahtuman nimi
> aika | date | Tapahtuman päivämäärä
> paikka | varchar(60) | Tapahtuman sijainti
> kuvaus | varchar(500) | Tapahtuman kuvaus
> lippumaara | int | Kuinka paljon lippuja on myynnissä
> ennakkomyynti | date | Päivämäärä, jolloin lippujen ennakkomyynti loppuu

> ### _Maksutapahtuma_
> _Maksutapahtuma-taulu sisältää maksutapahtumat. Maksutapahtumalla voi olla yksi käyttäjä. Käyttäjällä voi olla useita maksutapahtumia. Lippuja voi myydä sekä Admin- että User-rooleissa._
>
> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> maksutapahtumaId | int PK | Maksutapahtuman id
> kayttajaId | int FK | Viittaus käyttäjään [Käyttäjä](#Käyttäjä)-taulussa
> hintayhteensa | double |  Lippujen hinnat yhteensä
> aikaleima | date | Maksutapahtuman aikaleima
> removed | boolean | Maksutapahtuman soft delete, oletuksena false (true/false)

> ### _Hinnasto_
> _Hinnasto-taulu sisältää tapahtuman hinnastoluokat. Tapahtumalla voi olla useita hintaluokkia, mutta hintaluokka voi kuulua vain yhteen tapahtumaan. Hinnastoja voi luoda vain Admin-roolissa._
>
> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> hinnastoId | int PK | Hinnaston id
> tapahtumaId | int FK | Viittaus tapahtumaan [Tapahtuma](#Tapahtuma)-taulussa
> hintaluokka | varchar(30) | Hintaluokka esim. opiskelija
> hinta | double | Hinta

>  ### _Lippu_
> _Lippu-taulu sisältää lipun tiedot. Lippu taulu yhdistyy Tapahtuma- ja Hinnasto-tauluihin. Yhdellä lipulla on yksi hinta ja yksi tapahtuma. Lippuja voi myydä sekä Admin- että User-rooleissa._
>
> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> lippuId | int PK | Lipun id
> tapahtumaId | int FK | Viittaus tapahtumaan [Tapahtuma](#Tapahtuma)-taulussa
> hinnastoId | int FK | Viittaus hinnastoon [Hinnasto](#Hinnasto)-taulussa
> maksutapahtumaId | int FK | Viittaus maksutapahtumaan [Maksutapahtuma](#Maksutapahtuma)-taulussa
> kaytetty | boolean | Oletuksena false, muutetaan lipuntarkastuksessa true:ksi
> removed | boolean | Lipun soft delete, oletuksena false
> koodi | string | UUID-koodi Qr-koodia varten

>  ### _Käyttäjä_
> _Käyttäjä-taulu sisältää ohjelman käyttäjien tiedot. Ohjelman käyttäjiä ovat esimerkiksi myyjä ja manageri._
>
> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> kayttajaId | int PK | Käyttäjän id
> etunimi | varchar(30) | Ohjelmaa käyttävän henkilön etunimi
> sukunimi | varchar(30) | Ohjelmaa käyttävän henkilön sukunimi
> salasana | varchar(256) | Ohjelmaa käyttävän henkilön tunnuksen salasana
> kayttajatunnus | varchar(30) | Ohjelmaa käyttävän henkilön tunnuksen käyttäjätunnus
> oikeus | varchar(30) | Ohjelmaa käyttävän henkilön oikeudet, user tai admin
> aktiivisuus | boolean | Oletuksena true, muutetaan false, jos ei enää työskentele yrityksessä

## Tekninen kuvaus

Teknisessä kuvauksessa esitetään järjestelmän toteutuksen suunnittelussa tehdyt tekniset
ratkaisut, esim.

-   Missä mikäkin järjestelmän komponentti ajetaan (tietokone, palvelinohjelma)
    ja komponenttien väliset yhteydet (vaikkapa tähän tyyliin:
    https://security.ufl.edu/it-workers/risk-assessment/creating-an-information-systemdata-flow-diagram/)
-   Palvelintoteutuksen yleiskuvaus: teknologiat, deployment-ratkaisut yms.
-   Keskeisten rajapintojen kuvaukset, esimerkit REST-rajapinta. Tarvittaessa voidaan rajapinnan käyttöä täsmentää
    UML-sekvenssikaavioilla.
-   Toteutuksen yleisiä ratkaisuja, esim. turvallisuus.

Tämän lisäksi

-   ohjelmakoodin tulee olla kommentoitua
-   luokkien, metodien ja muuttujien tulee olla kuvaavasti nimettyjä ja noudattaa
    johdonmukaisia nimeämiskäytäntöjä
-   ohjelmiston pitää olla organisoitu komponentteihin niin, että turhalta toistolta
    vältytään

## Testaus

Tässä kohdin selvitetään, miten ohjelmiston oikea toiminta varmistetaan
testaamalla projektin aikana: millaisia testauksia tehdään ja missä vaiheessa.
Testauksen tarkemmat sisällöt ja testisuoritusten tulosten raportit kirjataan
erillisiin dokumentteihin.

Tänne kirjataan myös lopuksi järjestelmän tunnetut ongelmat, joita ei ole korjattu.

## Asennustiedot

Järjestelmän asennus on syytä dokumentoida kahdesta näkökulmasta:

-   järjestelmän kehitysympäristö: miten järjestelmän kehitysympäristön saisi
    rakennettua johonkin toiseen koneeseen

-   järjestelmän asentaminen tuotantoympäristöön: miten järjestelmän saisi
    asennettua johonkin uuteen ympäristöön.

Asennusohjeesta tulisi ainakin käydä ilmi, miten käytettävä tietokanta ja
käyttäjät tulee ohjelmistoa asentaessa määritellä (käytettävä tietokanta,
käyttäjätunnus, salasana, tietokannan luonti yms.).

## Käynnistys- ja käyttöohje

"Tyypillisesti tässä riittää kertoa ohjelman käynnistykseen tarvittava URL sekä
mahdolliset kirjautumiseen tarvittavat tunnukset. Jos järjestelmän
käynnistämiseen tai käyttöön liittyy joitain muita toimenpiteitä tai toimintajärjestykseen liittyviä asioita, nekin kerrotaan tässä yhteydessä.
Usko tai älä, tulet tarvitsemaan tätä itsekin, kun tauon jälkeen palaat
järjestelmän pariin!"

Käyttöliittymä löytyy Rahti2-palvelimelta osoitteessa [https://op1-client-front-ohjelmistoprojekti.2.rahtiapp.fi/](https://op1-client-front-ohjelmistoprojekti.2.rahtiapp.fi/)

Käyttäjätunnukset: