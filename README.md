Les5philosophes

Nous utilisons Philosophe comme le Thread de base, un Thread Philosophe gere deux ressources partagées, Fourchette et Assiette. Et un Thread Philosophe peut gerer plusieurs Fourchettes ou plusieurs Assiettes.

Ces deux variables existent dans la mémoire du tas. En tant que pile indépendante, chaque Thread Philosophe peut accéder à ces deux variables et obtenir la même valeur, donc ces deux variables sont des ressources partagées.

Utilisation de la technologie de synchronized pour garantir que lorsque le Thread Philosophe effectue des opérations de méthode sur des ressources partagées, telles que l'obtention de valeurs et la modification de valeurs, un seul Thread Philosophe peut exécuter la méthode à la fois.


Par exemple, lorsqu'un Thread Philosophe exécute prendreAssiete dans la méthode manger() , le programme verrouille toutes les Assiettes dans leur ensemble, interdisant aux autres Thread Philosophe d'utiliser la méthode marquée avec synchronized pour accéder, jusqu'à ce que la méthode soit exécutée, le verrou est automatiquement déverrouillé ou déverrouillé par d'autres moyens. Ainsi, les Threads Philosophe exécuteront la méthode prendreAssiete un par un de manière ordonnée.

Lorsqu'un Thread Pilosophe arbitraire exécute la méthode prendreFourchette, la situation est légèrement différente. Par exemple, pour le Thread Philosophe A, le programme vérifie d'abord si les deux Fourchettes proches du philosophe A sont déjà occupés. Si un autre Thread Philosophe B utilise les Fourchettes, le programme exécute la commande fourchettes.wait() et déverrouille tout à en même temps Les Fourchettes sont verrouillés collectivement, et jusqu'à ce que l'autre philosophe B ait fini de manger(), la méthode fourchettes.notifyAll() débloquez tous les Thread Philosophe qui wait() Fourchette, ensuite pose les Fourchettes et n'est plus occupé, confirme que les deux Fourchettes proches ne sont pas occupés ( Il est possible que le Thread Philosophe C vient de déposer les Fourchettes, mais ces Fourchettes ne sont pas ce dont A a besoin ), puis le Thread Philosophe A peut choisisser monter, manger et déposer etc. 


