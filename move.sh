#!/bin/sh

target=~/test
! [[ -z "$(ls -A $target)" ]] && rm $target/*
cp ./natwest-transactions/build/libs/* $target/
cp ./statement-api/build/libs/* $target/
cp ./statement-app/build/libs/* $target/
cp ./test.sh $target/