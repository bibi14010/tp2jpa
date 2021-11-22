# <font size="12"> TP2 de TAA </font>

Ce tp reprends le code du <a href="https://github.com/bibi14010/tpjpa2021">premier TP de jpa </a>

L'application dispose de toutes les fonctionnalités necessaires à la mise en place d'un scénario, de bout en bout.
Pour des raisons de pratique, il n'y a pas de portail de connexion ni de front, ce projet se voulant le plus simple possible.

## Lancement
Pour ce tp, il faut utiliser la branche *master*. Il faut au prealable lancer la base de donnee avec *run-hsqldb-server.bat* ou *run-hsqldb-server.sh* selon le systeme d'exploitation.
Il faut ensuite lancer jetty avec la commande *mvn package jetty:run* (et donc avoir maven d'installé).

## Utilisation
On a à disposition toutes les fonctionnalités necassaires à la création d'un scénario en partant d'une base vide. Sur l'accueil, toutes les url sont listées et nommée selon leut utilité.

## Exemple
Pour une prise de rendez-vous il nous faut :

- Un lieu (Location)
- Une Entreprise (Entreprise)
- Un professionnel a qui sera adresse le rendre-vous (Prestataire)
- Une personne avec qui le prestataire aura le rendez-vous (Client)
- Le rendez-vous en question avec les personnes concernees (Appointment).

Respectivement, créons les entités graces aux formulaires disponnibles:
- /addLocation
- /addEntreprise
- /addPrestataire
- /addClient

Ceci fait, on peut faire prendre rendez-vous entre les deux personnes crées à l'instant !

