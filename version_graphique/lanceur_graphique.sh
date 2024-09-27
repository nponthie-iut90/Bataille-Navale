#!/bin/bash

# Vérifie si un argument est passé
if [ "$#" -eq 1 ]; then
  ARG=$1
else
  ARG=""
fi

if [ -d "lib" ] && [ "$(ls -A lib)" ]; then
  echo "lancement"
else
  wget https://download2.gluonhq.com/openjfx/17.0.12/openjfx-17.0.12_linux-x64_bin-sdk.zip
  unzip openjfx-17.0.12_linux-x64_bin-sdk.zip
  mv javafx-sdk-17.0.12/lib lib
  rm -rf javafx-sdk-17.0.12
  rm -rf openjfx-17.0.12_linux-x64_bin-sdk.zip
fi
# Exécute la commande java avec l'argument
java --module-path lib --add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing -cp out Main $ARG
