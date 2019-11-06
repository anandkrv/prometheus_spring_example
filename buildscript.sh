#!/usr/bin/env bash

if [ "${build_target}" == "local" ];
then
  mvn clean install
  exit $?
fi
