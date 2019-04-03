#!/bin/bash
if test -z "$1"
then
  root="."
else
  root="$1"
fi
if test -z "$2"
then
  env="test"
else
  env="$2"
fi
mvn clean install -Droot=$root -Denv=$env