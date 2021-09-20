# TP1 de TAA  - Prise en main de JPA

Pour ce tp, il faut utiliser la branche *master*. Il faut au prealable lancer la base de donnee avec *run-hsqldb-server.bat* ou *run-hsqldb-server.sh* selon le systeme d'exploitation. 

Pour simuler une prise rendez-vous selon le schema decris dans *diagram.puml*, on instancie  tous les objets necessaires:
- Un lieu (Location)
- Une Entreprise (Entreprise)
- Un agenda (Agenda)
- Un professionnel a qui sera adresse le rendre-vous (Prestataire)
- Une personne avec qui le prestataire aura le rendez-vous (Client)
- Le rendez-vous en question avec les personnes concernees (Appointment).

### Scenario :

Agent K souhaite prendre rendez-vous avec Boris Lanimal, pour une duree de 30 minutes. On convient que les rendez-vous se passent au lieu de l'entreprise du prestataire, a savoir sur la lune chez Mechant Industries (inspiration debordante n'est-il pas !). 

Lancer l'application Java par la classe JpaTest. Avec la commande *show-hsqldb.bat* ou *show-hsqldb.sh*, on peut donc voir le rendez-vous dans la table *Appointments*.
