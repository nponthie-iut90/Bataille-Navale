#!/bin/bash

# Vérifie si un argument est passé
if [ "$#" -eq 1 ]; then
  ARG=$1
else
  ARG=""
fi

# Exécute la commande java avec l'argument
java --module-path lib --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing -cp out Main $ARG
