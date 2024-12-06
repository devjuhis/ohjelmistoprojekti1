# Testisuunnitelma: Integraatiotestaus/API-testaus

## Testauksen tavoite

Testauksen tavoitteena on voimme varmistaa, että rajapinta toimii kuten pitääkin. Testit ajettiin jokaiselle REST-Controllerille.

## Testit

 **Testattava asia**                | **Testin suoritus**
 ---------------------------------- | -------------------
 POST (Luo uusi entiteetti) | MockMvc simulaatio suoritetaan luomalla uusi entiteetti POST-pyynnöllä. Testissä tarkistetaan, että statuskoodi on 201 Created ja että luotu entiteetti palautetaan oikealla tiedolla.
 PATCH (Päivitä entiteetti) | MockMvc simulaatio suoritetaan päivittämällä entiteetti PATCH-pyynnöllä. Testissä tarkistetaan, että statuskoodi on 200 OK ja että entiteetti on päivitetty odotetulla tavalla.
 DELETE (Poista entiteetti) | MockMvc simulaatio suoritetaan poistamalla entiteetti DELETE-pyynnöllä. Testissä tarkistetaan, että statuskoodi on 204 No Content ja että entiteetti poistetaan onnistuneesti.


Testiloki 20.11.2024
=======================

**Testaaja:** Tiimi7

**Ympäristö:**
- JUnit 
- Visual Studio Code + Extensions
    - Test Runner for Java
    - Extension Pack for Java