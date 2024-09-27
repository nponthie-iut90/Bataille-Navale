#!/bin/bash

# Fonction pour demander et valider l'entrée de l'utilisateur
ask_for_args() {
  while true; do
    read -p "Veuillez entrer la valeur de args (0, 1 ou 2) : " ARG
    if [[ "$ARG" =~ ^[012]$ ]]; then
      break
    else
      echo "Entrée invalide. Veuillez entrer 0, 1 ou 2."
    fi
  done
}

echo "Entrez 1 pour le mode jcj, 2 pour le mode jce ou 3 pour le mode ece."
# Appel de la fonction pour demander l'entrée de l'utilisateur
ask_for_args

# Exécute la commande java avec l'argument
java -cp out Main $ARG
