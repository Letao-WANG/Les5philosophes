Les5philosophes

Nous utilisons Philosophe comme le Thread de base, un Thread Philosophe gere deux ressources partagées, Fourchette et Assiette. Et un Thread Philosophe peut gerer plusieurs Fourchettes ou plusieurs Assiettes.

Ces deux variables existent dans la mémoire du tas. En tant que pile indépendante, chaque Thread Philosophe peut accéder à ces deux variables et obtenir la même valeur, donc ces deux variables sont des ressources partagées.

Utilisation de la technologie de synchronized pour garantir que lorsque le Thread Philosophe effectue des opérations de méthode sur des ressources partagées, telles que l'obtention de valeurs et la modification de valeurs, un seul Thread Philosophe peut exécuter la méthode à la fois.
