# Napoleons_Grav
Använder java version "1.8.0_121".
## Köra programmet
Nedan är två sätt att köra programmet på; via kommandotolken eller via IntelliJ (som jag har
använt). Beroende på vilket sätt man väljer så är sökvägen till bilderna lite olika för att det
ska kunna “hitta” rätt.
### Kommandotolken
Notera att först kolla i filen Card.java i funktionen kallad setCardImage. Se till att sökvägen
till bilderna är “../bin/cards/” så att programmet kan hitta rätt till bilderna.
För att köra programmet, kompilera med kommandot 
```
$ javac NapoleonsGrave.java
```
följt av kommandot
```
$ java NapoleonsGrave
```
### IntelliJ
Öppna hela mappen som ett nytt projekt i IntelliJ. Notera även här att först kolla i filen
Card.java i funktionen setCardImage och se till att sökvägen till bilderna istället är “../cards/”.
När det är fixat är det bara att köra programmet.
