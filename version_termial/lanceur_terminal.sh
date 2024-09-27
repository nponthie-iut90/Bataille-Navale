#!/bin/bash

# Vérifie si un argument est passé
if [ "$#" -eq 1 ]; then
  ARG=$1
else
  ARG=""
fi

# Exécute la commande java avec l'argument
java -cp out Main $ARG
