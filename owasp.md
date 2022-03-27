## OWASP Analyse

## A03:2021-Injection

### Uitleg

Houdt in dat een actor sql kan uitvoeren op de database. Dit gebeurt meestal als een methode zijn paramters verwerkt in een string literal. Deze string wordt uitgevoerd als query.

### Binnen Context Lingo

Door het gebruik van een ORM die entiteiten omzet naar ddl en methodes implementeerd prepared statements is de kans klein dat SQL Injection succesvol uitgevoerd kan worden.

## A09:2021-Security Logging & Monitoring

### Uitleg

Het loggen van kriktieke onderdelen in het systeem is van groot belang. Tijdens en na een aanval is het voor (forensisch) onderzoek van grote waarde om te kunnen zien wat er precies gebeurt is.

### Binnen Context Lingo

Lingo maakt gebruik van een log library die het mogelijk maakt om makkelijk objecten te schrijven naar de standardOut. Echter is de applicatie laag van Lingo misschien te hoog om alle events te kunnen ontvangen. Connectecties en andere events worden door de kernel op andere lagen van het osi model afgehandeld. Lingo logt wel alle errors mochtend die plaats vinden.
