Réponses TP03 – JPA CRUD

1. Différence entre JPA, Hibernate et Spring Data JPA

* JPA est une spécification standard qui définit les interfaces et les annotations de persistance.
* Hibernate est une implémentation de JPA qui permet de communiquer avec la base de données.
* Spring Data JPA facilite l’utilisation de JPA et configure automatiquement les composants nécessaires.

2. Pourquoi utiliser @Transactional ?

Les opérations save, update, delete et deleteAll modifient la base de données. Elles nécessitent donc une transaction.

Les opérations findById et findAll sont des lectures simples et ne nécessitent pas @Transactional dans cet exercice.

3. Différence entre persist() et merge()

persist()	merge()
Ajoute une nouvelle entité.	Met à jour une entité existante.
Utilisé pour INSERT.	Utilisé pour UPDATE.
Ne retourne rien.	Retourne une instance gérée.

L’objet original passé à merge() reste détaché.

4. Différence entre JPQL et SQL

SQL travaille avec les tables et les colonnes de la base de données.

JPQL travaille avec les classes Java et leurs attributs.

Dans FROM Student WHERE lastName = :theData, Student représente la classe et lastName représente son attribut.

5. Pourquoi utiliser un paramètre nommé ?

Le paramètre nommé :theData permet de séparer la requête de sa valeur. Il protège contre les injections SQL et rend le code plus sûr.

6. Rôle de @Repository

@Repository déclare une classe comme composant de persistance et permet la traduction des exceptions en DataAccessException de Spring.

7. Pourquoi ne pas utiliser remove(new Student(5)) ?

remove() accepte uniquement une entité gérée par EntityManager.

Il faut d’abord récupérer l’étudiant avec find(), puis appeler remove().

8. Les valeurs de spring.jpa.hibernate.ddl-auto

Valeur	Comportement
none	Ne fait rien.
validate	Vérifie la correspondance entre les entités et les tables.
update	Met à jour le schéma sans supprimer les données existantes.
create	Supprime et recrée les tables au démarrage.
create-drop	Crée les tables au démarrage et les supprime à l’arrêt.

En production, on préfère none ou validate avec un outil de migration. On évite create car il peut entraîner la perte des données.
