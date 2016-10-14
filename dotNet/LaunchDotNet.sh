#!/bin/bash

mcs src/*.cs -pkg:wcf -out:server.exe

mono server.exe